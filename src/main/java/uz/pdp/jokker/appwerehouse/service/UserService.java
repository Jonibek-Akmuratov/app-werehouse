package uz.pdp.jokker.appwerehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.jokker.appwerehouse.entity.User;
import uz.pdp.jokker.appwerehouse.entity.Werehouse;
import uz.pdp.jokker.appwerehouse.payload.ApiResponce;
import uz.pdp.jokker.appwerehouse.payload.UserDto;
import uz.pdp.jokker.appwerehouse.repository.UserRepository;
import uz.pdp.jokker.appwerehouse.repository.WerehouseRepository;

import java.util.*;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    WerehouseRepository werehouseRepository;
    @Autowired
    PhoneAuth phoneAuth;

    public ApiResponce add(UserDto userDto) {
        if (userRepository.existsByPhoneNumber(userDto.getPhoneNumber()))
            return new ApiResponce("Bunday telefon reqamli user avvaldan mavjud", false);
        ApiResponce responce = phoneAuth.phoneAuth(userDto.getPhoneNumber());
        if (responce.isSucces() == false) {
            return responce;
        }

        Set<Integer> werehousset = userDto.getWerehousset();
        Set<Werehouse> werehouses = new LinkedHashSet<>();
        List<Integer> list = new ArrayList<>();
        for (Integer werehouseInteger : werehousset) {
            if (!werehouseRepository.findById(werehouseInteger).isPresent()) {
                list.add(werehouseInteger);
            } else {
                werehouses.add(werehouseRepository.findById(werehouseInteger).get());
            }
        }

        User user = new User(
                userDto.getFirstName(),
                userDto.getLastName(),
                userDto.getPhoneNumber(),
                UUID.randomUUID().toString(),
                userDto.getPassword(),
                true,
                werehouses
        );
        User save = userRepository.save(user);
        return new ApiResponce("User muvafaqiyatli saqlandi", true, save);
    }

    public ApiResponce edit(Integer id, UserDto userDto) {
        if (!userRepository.findById(id).isPresent())
            return new ApiResponce("Bunday User topilmadi", false);
        User user = userRepository.findById(id).get();
        if (userDto.getPhoneNumber() != null) {
            if (!phoneAuth.phoneAuth(userDto.getPhoneNumber()).isSucces()) {
                return phoneAuth.phoneAuth(userDto.getPhoneNumber());
            }
            if (userRepository.existsByPhoneNumber(userDto.getPhoneNumber())) {
                return new ApiResponce("Bunday telefon reqamli user avvaldan mavjud", false);
            }
            user.setPhoneNumber(userDto.getPhoneNumber());
        }
        if (userDto.getWerehousset()!=null) {
            Set<Integer> werehousset = userDto.getWerehousset();
            Set<Werehouse> werehouses = new LinkedHashSet<>();
            List<Integer> list = new ArrayList<>();
            for (Integer werehouseInteger : werehousset) {
                if (!werehouseRepository.findById(werehouseInteger).isPresent()) {
                    list.add(werehouseInteger);
                } else {
                    werehouses.add(werehouseRepository.findById(werehouseInteger).get());
                }
            }
            user.setWerehouses(werehouses);
        }
        if (userDto.isActive() || userDto.isActive() == false) {
            user.setActive(userDto.isActive());
        }
        if (userDto.getFirstName()!=null) {
            user.setFirstName(userDto.getFirstName());
        }
        if (userDto.getLastName()!=null) {
            user.setLastName(userDto.getLastName());
        }
        if (userDto.getPassword()!=null) {
            user.setPassword(userDto.getPassword());
        }

        User save = userRepository.save(user);
        return new ApiResponce("User taxrirlandi", true, save);
    }

    public ApiResponce getOne(Integer id) {
        if (!userRepository.findById(id).isPresent()) {
            return new ApiResponce("User not found", false, null);
        }
        return new ApiResponce("User topildi", true, userRepository.findById(id).get());
    }

    public ApiResponce getAll() {
        List<User> all = userRepository.findAll();
        if (all.isEmpty()) {
            return new ApiResponce("Userlar royxati bosh", false, all);
        }
        return new ApiResponce("USerlar Royxati", true, all);
    }



    public ApiResponce deleted(Integer id) {
        if (!userRepository.findById(id).isPresent()) {
            return new ApiResponce( false,null);
        }
        userRepository.deleteById(id);
        return new ApiResponce(true,null);
    }
}
