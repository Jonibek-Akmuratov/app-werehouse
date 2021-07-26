package uz.pdp.jokker.appwerehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.jokker.appwerehouse.entity.Input;
import uz.pdp.jokker.appwerehouse.entity.Input_product;
import uz.pdp.jokker.appwerehouse.payload.ApiResponce;
import uz.pdp.jokker.appwerehouse.payload.DateDto;
import uz.pdp.jokker.appwerehouse.repository.InputProductRepository;
import uz.pdp.jokker.appwerehouse.repository.InputRepository;

import java.util.LinkedList;
import java.util.List;


@Service
public class DashboardService {
    @Autowired
    InputRepository inputRepository;
    @Autowired
    InputProductRepository inputProductRepository;


    public ApiResponce method(DateDto dateDto) {
        List<Input_product> result = new LinkedList<>();
        List<Input> allByDateInput = inputRepository.findAllByDateEquals(dateDto.getDate());
        for (Input input : allByDateInput) {
            List<Input_product> allByInputId = inputProductRepository.findAllByInputId(input.getId());
            for (Input_product input_product : allByInputId) {
                result.add(input_product);
            }
        }
        int summa = 0;
        for (Input_product input_product : result) {
            summa = summa + input_product.getPrice() * input_product.getAmount();
        }
        return new ApiResponce("Jami summa => " + summa, true, result);
    }
}

