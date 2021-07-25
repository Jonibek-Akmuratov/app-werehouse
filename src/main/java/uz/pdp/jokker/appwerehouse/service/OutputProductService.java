package uz.pdp.jokker.appwerehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.jokker.appwerehouse.entity.Output;
import uz.pdp.jokker.appwerehouse.entity.Output_product;
import uz.pdp.jokker.appwerehouse.entity.Product;
import uz.pdp.jokker.appwerehouse.payload.ApiResponce;
import uz.pdp.jokker.appwerehouse.payload.OutputProductDto;
import uz.pdp.jokker.appwerehouse.repository.OutputProductRepository;
import uz.pdp.jokker.appwerehouse.repository.OutputRepository;
import uz.pdp.jokker.appwerehouse.repository.ProductRepository;

import java.util.Optional;

@Service
public class OutputProductService {
    @Autowired
    OutputProductRepository outputProductRepository;
    @Autowired
    OutputRepository outputRepository;
    @Autowired
    ProductRepository productRepository;


    public ApiResponce add(OutputProductDto outputProductDto) {
        Optional<Output> optionalOutput = outputRepository.findById(outputProductDto.getOutputId());
        if (!optionalOutput.isPresent())
            return new ApiResponce("Bunday Chiqim topilmadi", false);
        Optional<Product> optionalProduct = productRepository.findById(outputProductDto.getProductId());
        if (!optionalProduct.isPresent()) {
            return new ApiResponce("Bunday Product topilmadi", false);
        }
        if (outputProductDto.getAmount() <= 0 || outputProductDto.getPrice() <= 0)
            return new ApiResponce("Amount va Price Musbat bolishi kerek", false);
        Output_product product = new Output_product(
                optionalProduct.get(),
                optionalOutput.get(),
                outputProductDto.getAmount(),
                outputProductDto.getPrice()
        );
        Output_product save = outputProductRepository.save(product);
        return new ApiResponce("Saqlandi", true, save);

    }
}
