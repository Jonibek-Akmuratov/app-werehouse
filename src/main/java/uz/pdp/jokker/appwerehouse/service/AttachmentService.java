package uz.pdp.jokker.appwerehouse.service;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import uz.pdp.jokker.appwerehouse.entity.Attachment;
import uz.pdp.jokker.appwerehouse.entity.AttachmentContent;
import uz.pdp.jokker.appwerehouse.payload.ApiResponce;
import uz.pdp.jokker.appwerehouse.repository.AttachmentContentRepository;
import uz.pdp.jokker.appwerehouse.repository.AttachmentRepository;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Iterator;
import java.util.Optional;


@Service
public class AttachmentService {
    @Autowired
    AttachmentRepository attachmentRepository;
    @Autowired
    AttachmentContentRepository attachmentContentRepository;


    public ApiResponce upload(MultipartHttpServletRequest request) throws IOException {
        Iterator<String> fileNames = request.getFileNames();
        MultipartFile file = request.getFile(fileNames.next());
        if (file.isEmpty()) {
            return new ApiResponce("File bosh bolmasin", false);
        }
        String originalFilename = file.getOriginalFilename();
        Attachment attachment = new Attachment(
                originalFilename,
                file.getSize(),
                file.getContentType()
        );
        Attachment saveAttachment = attachmentRepository.save(attachment);

        AttachmentContent attechmentContent = new AttachmentContent(
                file.getBytes(),
                saveAttachment
        );
        AttachmentContent save = attachmentContentRepository.save(attechmentContent);
        attachmentContentRepository.save(attechmentContent);
        return new ApiResponce("File saqlandi", true, attachment);

    }

    @SneakyThrows
    public HttpServletResponse get(Integer id, HttpServletResponse response) {
        Optional<Attachment> optionalAttachment = attachmentRepository.findById(id);
        if (optionalAttachment.isPresent()) {
            Attachment attachment = optionalAttachment.get();

            Optional<AttachmentContent> optionalAttachmentContent = attachmentContentRepository.findByAttachmentId(attachment.getId());
            if (optionalAttachmentContent.isPresent()) {
                AttachmentContent attachmentContent = optionalAttachmentContent.get();

                response.setHeader("Content-Disposition",
                        "attachment; filename=\"" + attachment.getName() + "\"");
                response.setContentType(attachment.getContent_type());

                FileCopyUtils.copy(attachmentContent.getBytes(), response.getOutputStream());
                return response;
            }
        }
        return null;
    }
}
