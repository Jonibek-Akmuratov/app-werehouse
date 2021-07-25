package uz.pdp.jokker.appwerehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.jokker.appwerehouse.entity.Currency;
import uz.pdp.jokker.appwerehouse.payload.ApiResponce;
import uz.pdp.jokker.appwerehouse.payload.CurrencyDto;
import uz.pdp.jokker.appwerehouse.repository.CurrencyRepository;

@Service
public class CurrencyService {

    @Autowired
    CurrencyRepository currencyRepository;


    public ApiResponce add(CurrencyDto currencyDto) {
        if (currencyRepository.existsByName(currencyDto.getName())) {
            return new ApiResponce("Bu nomli Valyuta avvaldan mavjud", false);
        }
        Currency currency = new Currency(
                currencyDto.getName(),
                currencyDto.isActive()
        );
        Currency save = currencyRepository.save(currency);
        return new ApiResponce("Valyuta biligi saqlandi", true, save);
    }



}
