package uz.pdp.jokker.appwerehouse.service;

import org.springframework.stereotype.Service;
import uz.pdp.jokker.appwerehouse.payload.ApiResponce;

@Service
public class PhoneAuth {

    public ApiResponce phoneAuth(String phoneNumber) {
        if (phoneNumber.length() != 13 || !phoneNumber.startsWith("+998"))
            return new ApiResponce("Phone Number '+998xx xxx xx xx' ko'rinishida bolishi kk", false);
        for (int i = 0; i < phoneNumber.length(); i++) {
            char c = phoneNumber.charAt(i);
            if (c=='+'||c == '0' || c == '1' || c == '2' || c == '3' || c == '4' || c == '5' || c == '6' || c == '7' || c == '8' || c == '9') {
            } else {
                return new ApiResponce("Phone Numberda faqat sonlar qatnashishi kk." +
                        " Bunday belgila qatnashmasin => " + c + " ;", false);
            }
        }
        return new ApiResponce("Phone Number yaxshi otdi tekshiruvdan", true, phoneNumber);

    }
}
