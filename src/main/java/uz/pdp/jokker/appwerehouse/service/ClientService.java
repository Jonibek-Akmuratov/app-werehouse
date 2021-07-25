package uz.pdp.jokker.appwerehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.jokker.appwerehouse.entity.Client;
import uz.pdp.jokker.appwerehouse.payload.ApiResponce;
import uz.pdp.jokker.appwerehouse.payload.ClientDto;
import uz.pdp.jokker.appwerehouse.repository.ClientRepository;

@Service
public class ClientService {
    @Autowired
    ClientRepository clientRepository;
    @Autowired
    PhoneAuth phoneAuth;

    public ApiResponce add(ClientDto clientDto) {
        if (clientRepository.existsByPhoneNumber(clientDto.getPhoneNumber())) {
            return new ApiResponce("Bu nomerli client avvaldan mavjud", false);
        }
        ApiResponce responce = phoneAuth.phoneAuth(clientDto.getPhoneNumber());
        if (responce.isSucces() == false)
            return responce;
        Client client = new Client(
                clientDto.getName(),
                clientDto.getPhoneNumber(),
                clientDto.isActive()
        );
        Client save = clientRepository.save(client);
        return new ApiResponce("Client saqlandi", true, save);

    }
}
