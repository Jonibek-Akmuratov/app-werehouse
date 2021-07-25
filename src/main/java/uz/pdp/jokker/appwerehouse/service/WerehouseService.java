package uz.pdp.jokker.appwerehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.jokker.appwerehouse.entity.Werehouse;
import uz.pdp.jokker.appwerehouse.payload.ApiResponce;
import uz.pdp.jokker.appwerehouse.payload.WerehouseDto;
import uz.pdp.jokker.appwerehouse.repository.WerehouseRepository;

import java.util.Collections;
import java.util.List;
import java.util.Set;

@Service
public class WerehouseService {

    @Autowired
    WerehouseRepository werehouseRepository;

    public ApiResponce add(WerehouseDto werehouseDto) {
        if (werehouseRepository.existsByName(werehouseDto.getName()))
            return new ApiResponce("Bu nomli ombor avvaldan mavjud", false);
        Werehouse werehouse = new Werehouse(
                werehouseDto.getName(),
                true
        );
        Werehouse save = werehouseRepository.save(werehouse);
        return new ApiResponce("WereHouse saqlandi!!!", true, save);

    }

    public ApiResponce edit(Integer id, WerehouseDto werehouseDto) {
        if (werehouseRepository.existsByName(werehouseDto.getName()))
            return new ApiResponce("Bu nomli ombor avvaldan mavjud", false);

        if (!werehouseRepository.findById(id).isPresent())
            return new ApiResponce("Bu nomli ombor topilmadi", false);
        Werehouse werehouse = werehouseRepository.findById(id).get();
        if (werehouseDto.getName() != null) {
            werehouse.setName(werehouseDto.getName());
        }
        if (werehouseDto.isActive() || (!werehouseDto.isActive())) {
            werehouse.setActive(werehouseDto.isActive());
        }
        Werehouse save = werehouseRepository.save(werehouse);
        return new ApiResponce("WereHouse tahrirlandi!!!", true, save);
    }

    public ApiResponce delete(Integer id) {
        if (!werehouseRepository.findById(id).isPresent())
            return new ApiResponce("Bu nomli ombor topilmadi", false);
        werehouseRepository.deleteById(id);
        return new ApiResponce("WereHouse o'chirildi!!!", true);
    }

    public Werehouse getOne(Integer id) {
        if (!werehouseRepository.findById(id).isPresent())
            return null;
        return werehouseRepository.findById(id).get();
    }

    public List<Werehouse> getAll() {

        List<Werehouse> werehouseList = werehouseRepository.findAll();
        return werehouseList;
    }


}
