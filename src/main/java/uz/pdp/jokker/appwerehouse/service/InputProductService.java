package uz.pdp.jokker.appwerehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.jokker.appwerehouse.entity.Input_product;
import uz.pdp.jokker.appwerehouse.payload.ApiResponce;
import uz.pdp.jokker.appwerehouse.payload.InputProductDto;
import uz.pdp.jokker.appwerehouse.repository.InputProductRepository;
import uz.pdp.jokker.appwerehouse.repository.InputRepository;
import uz.pdp.jokker.appwerehouse.repository.ProductRepository;

import java.util.Date;

@Service
public class InputProductService {

    @Autowired
    InputProductRepository inputProductRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    InputRepository inputRepository;


    public ApiResponce add(InputProductDto inputProductDto) {
        if (!productRepository.findById(inputProductDto.getProductsId()).isPresent())
            return new ApiResponce("Bunday Product topilmadi", false);
        if (!inputRepository.findById(inputProductDto.getInputId()).isPresent())
            return new ApiResponce("Bunday kirim royxatdan topilmadi", false);
        if (inputProductDto.getPrice() <= 0)
            return new ApiResponce("Price musbat bolishi kk", false);
        if (inputProductDto.getAmount() <= 0)
            return new ApiResponce("Amount musbat bolishi kk", false);

        Input_product input_product = new Input_product(
                productRepository.findById(inputProductDto.getProductsId()).get(),
                inputRepository.findById(inputProductDto.getInputId()).get(),
                inputProductDto.getAmount(),
                inputProductDto.getPrice(),
                inputProductDto.getExpire_date()
        );
        Input_product save = inputProductRepository.save(input_product);
        return new ApiResponce("Input Product saqlandi", true, save);
    }
}
