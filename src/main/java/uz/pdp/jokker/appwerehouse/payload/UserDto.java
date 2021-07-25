package uz.pdp.jokker.appwerehouse.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import uz.pdp.jokker.appwerehouse.entity.Werehouse;

import javax.persistence.Column;
import javax.persistence.ManyToMany;
import java.util.Set;

@Data
public class UserDto {

    private String firstName;

    private String lastName;

    private String phoneNumber;

    private String password;

    private Set<Integer> werehousset;

    private boolean active;

    public UserDto(String firstName, String lastName, String phoneNumber, String password, Set<Integer> werehousset) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.werehousset = werehousset;
    }
    public UserDto() {
    }

    public UserDto(String firstName, String lastName, String phoneNumber, String password, Set<Integer> werehousset, boolean active) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.werehousset = werehousset;
        this.active = active;
    }
}


