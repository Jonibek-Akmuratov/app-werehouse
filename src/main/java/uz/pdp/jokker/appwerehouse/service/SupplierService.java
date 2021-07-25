package uz.pdp.jokker.appwerehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.jokker.appwerehouse.entity.Supplier;
import uz.pdp.jokker.appwerehouse.payload.ApiResponce;
import uz.pdp.jokker.appwerehouse.payload.SupplierDto;
import uz.pdp.jokker.appwerehouse.repository.SupplierRepository;

@Service
public class SupplierService {
    @Autowired
    SupplierRepository supplierRepository;
    @Autowired
    PhoneAuth phoneAuth;


    public ApiResponce add(SupplierDto supplierDto) {
        ApiResponce responce = phoneAuth.phoneAuth(supplierDto.getPhoneNumber());
        if (!responce.isSucces()) {
            return responce;
        }
        if (supplierRepository.existsByPhoneNumber(supplierDto.getPhoneNumber())) {
            return new ApiResponce("Bunday nomerli Supplier avvaldan mavjud", false);
        }
        Supplier supplier = new Supplier(
                supplierDto.getName(),
                supplierDto.getPhoneNumber(),
                supplierDto.isActive()
        );
        Supplier save = supplierRepository.save(supplier);
        return new ApiResponce("Supplier saqlandi!!!", true, save);


    }
}
