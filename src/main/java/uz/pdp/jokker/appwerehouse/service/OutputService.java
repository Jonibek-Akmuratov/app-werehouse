package uz.pdp.jokker.appwerehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.jokker.appwerehouse.entity.Client;
import uz.pdp.jokker.appwerehouse.entity.Currency;
import uz.pdp.jokker.appwerehouse.entity.Output;
import uz.pdp.jokker.appwerehouse.entity.Werehouse;
import uz.pdp.jokker.appwerehouse.payload.ApiResponce;
import uz.pdp.jokker.appwerehouse.payload.OutputDto;
import uz.pdp.jokker.appwerehouse.repository.*;

import java.util.Optional;
import java.util.UUID;

@Service
public class OutputService {
    @Autowired
    OutputRepository outputRepository;
    @Autowired
    WerehouseRepository werehouseRepository;
    @Autowired
    ClientRepository clientRepository;
    @Autowired
    CurrencyRepository currencyRepository;

    public ApiResponce addService(OutputDto outputDto) {
        Optional<Currency> optionalCurrency = currencyRepository.findById(outputDto.getCurrencyId());
        if (!optionalCurrency.isPresent())
            return new ApiResponce("Bunday valyua turi topilmadi", false);
        Optional<Werehouse> optionalWerehouse = werehouseRepository.findById(outputDto.getWerehouseId());
        if (!optionalWerehouse.isPresent())
            return new ApiResponce("Bunday ombor topilmadi", false);
        Optional<Client> optionalClient = clientRepository.findById(outputDto.getClientId());
        if (!optionalClient.isPresent())
            return new ApiResponce("Bundan client topilmadi", false);
        Client client = optionalClient.get();
        if (!client.isActive())
            return new ApiResponce("Bu Client bloklangan.Iltimos avval Clientni activlashtiring", false);
        Currency currency = optionalCurrency.get();
        if (!currency.isActive())
            return new ApiResponce("Bu Valyuta turi bloklangan.Iltimos avval Uni activlashtiring", false);
        Werehouse werehouse = optionalWerehouse.get();
        if (!werehouse.isActive())
            return new ApiResponce("Bu Ombor bloklangan.Iltimos avval Ombornini activlashtiring", false);
        if (outputRepository.existsByFactureNumber(outputDto.getFacture_number())) {
            return new ApiResponce("Facture Numberni almashtring", false);
        }
        Output output = new Output(
                outputDto.getDate(),
                werehouse,
                client,
                currency,
                outputDto.getFacture_number(),
                UUID.randomUUID().toString()
        );
        Output save = outputRepository.save(output);
        return new ApiResponce("Chiqim saqlqandi", true, save);

    }
}












