package uz.pdp.jokker.appwerehouse.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponce {
    private String message;
    private boolean succes;
    private Object object;

    public ApiResponce(String message, boolean succes) {
        this.message = message;
        this.succes = succes;
    }

    public ApiResponce(boolean succes, Object object) {
        this.succes = succes;
        this.object = object;
    }
}
