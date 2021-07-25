package uz.pdp.jokker.appwerehouse.service;

import org.apache.tomcat.util.threads.ResizableExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.pdp.jokker.appwerehouse.entity.Category;
import uz.pdp.jokker.appwerehouse.entity.Product;
import uz.pdp.jokker.appwerehouse.payload.ApiResponce;
import uz.pdp.jokker.appwerehouse.payload.ProductDto;
import uz.pdp.jokker.appwerehouse.repository.AttachmentRepository;
import uz.pdp.jokker.appwerehouse.repository.CategoryRepository;
import uz.pdp.jokker.appwerehouse.repository.MeasurementRepository;
import uz.pdp.jokker.appwerehouse.repository.ProductRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    AttachmentRepository attachmentRepository;
    @Autowired
    MeasurementRepository measurementRepository;


    public ApiResponce add(ProductDto productDto) {

        if (productRepository.existsByName(productDto.getName()))
            return new ApiResponce("Bu nomli product avvaldan mavjud", false);
        if (!categoryRepository.findById(productDto.getCategory_id()).isPresent())
            return new ApiResponce("Bu nomli categorya topilmadi", false);
        if (!measurementRepository.findById(productDto.getMeasurement_id()).isPresent())
            return new ApiResponce("Bu nomli measurment topilmadi", false);
        if (!attachmentRepository.findById(productDto.getPhoto_id()).isPresent())
            return new ApiResponce("Bunday Photo topilmadi", false);

        Product product = new Product(
                productDto.getName(),
                categoryRepository.findById(productDto.getCategory_id()).get(),
                attachmentRepository.findById(productDto.getPhoto_id()).get(),
                UUID.randomUUID().toString(),
                measurementRepository.findById(productDto.getMeasurement_id()).get()
        );
        Product save = productRepository.save(product);
        return new ApiResponce("Product succesfully added", true, save);

    }


    public ApiResponce getAll() {
        List<Product> all = productRepository.findAll();
        return new ApiResponce("Productlar royxati", true, all);
    }

    public ApiResponce getOne(Integer id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        return optionalProduct.map(product -> new ApiResponce("Eto Product ", true, product)).orElseGet(() -> new ApiResponce("Product not found", false));
    }

    public ApiResponce deleted(Integer id) {

        if (productRepository.findById(id).isPresent()) {
            productRepository.deleteById(id);
            return new ApiResponce("Product deleted", true);
        }
        return new ApiResponce("Product not found", false, id);

    }

    public ApiResponce edit(Integer id, ProductDto productDto) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (!optionalProduct.isPresent()) {
            return new ApiResponce("Bunday id li product topilmadi", false);
        }
        Product product = optionalProduct.get();
        if (productDto.getName() != null) {
            if (productRepository.existsByNameNot(productDto.getName()))
                return new ApiResponce("Bu nomli product avvaldan mavjud", false);
            product.setName(productDto.getName());
        }
        if (productDto.getCategory_id() != null) {
            if (!categoryRepository.findById(productDto.getCategory_id()).isPresent())
                return new ApiResponce("Bu nomli categorya topilmadi", false);
            product.setCategory(categoryRepository.findById(productDto.getCategory_id()).get());
        }
        if (productDto.getMeasurement_id() != null) {
            if (!measurementRepository.findById(productDto.getMeasurement_id()).isPresent())
                return new ApiResponce("Bu nomli measurment topilmadi", false);
            product.setMeasurement(measurementRepository.findById(productDto.getMeasurement_id()).get());
        }
        if (productDto.getPhoto_id() != null) {
            if (!attachmentRepository.findById(productDto.getPhoto_id()).isPresent())
                return new ApiResponce("Bunday Photo topilmadi", false);
            product.setPhoto_id(attachmentRepository.findById(productDto.getPhoto_id()).get());
        }
        if (productDto.isActive() != true && productDto.isActive() != false) {
            product.setActive(productDto.isActive());
        }
        Product save = productRepository.save(product);
        return new ApiResponce("Product taxrirlandi", true, save);
    }
}