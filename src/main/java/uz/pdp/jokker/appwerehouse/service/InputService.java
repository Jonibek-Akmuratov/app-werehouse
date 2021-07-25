package uz.pdp.jokker.appwerehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.jokker.appwerehouse.entity.Input;
import uz.pdp.jokker.appwerehouse.payload.ApiResponce;
import uz.pdp.jokker.appwerehouse.payload.InputDto;
import uz.pdp.jokker.appwerehouse.repository.CurrencyRepository;
import uz.pdp.jokker.appwerehouse.repository.InputRepository;
import uz.pdp.jokker.appwerehouse.repository.SupplierRepository;
import uz.pdp.jokker.appwerehouse.repository.WerehouseRepository;

import java.util.UUID;

@Service
public class InputService {
    @Autowired
    InputRepository inputRepository;
    @Autowired
    WerehouseRepository werehouseRepository;
    @Autowired
    SupplierRepository supplierRepository;
    @Autowired
    CurrencyRepository currencyRepository;


    public ApiResponce addInput(InputDto inputDto) {

        if (inputRepository.existsByFactureNumber(inputDto.getFactureNumber())) {
            return new ApiResponce("Bunday Facture Numberli Kirim avval qayt qilingan", false);
        }
        if (!werehouseRepository.findById(inputDto.getWerehouseId()).isPresent()) {
            return new ApiResponce("Bunday Werehouse topilmadi", false);
        }
        if (!supplierRepository.findById(inputDto.getSupplierId()).isPresent())
            return new ApiResponce("Bunday Supplier topilmadi", false);
        if (!currencyRepository.findById(inputDto.getCurrencyId()).isPresent())
            return new ApiResponce("Bunday Valyuta turi topilmadi", false);
        Input input = new Input(
                inputDto.getDate(),
                werehouseRepository.findById(inputDto.getWerehouseId()).get(),
                supplierRepository.findById(inputDto.getSupplierId()).get(),
                currencyRepository.findById(inputDto.getCurrencyId()).get(),
                inputDto.getFactureNumber(),
                UUID.randomUUID().toString()
        );
        Input save = inputRepository.save(input);
        return new ApiResponce("Kirim xotiraga saqlandi", true, save);
    }
}
