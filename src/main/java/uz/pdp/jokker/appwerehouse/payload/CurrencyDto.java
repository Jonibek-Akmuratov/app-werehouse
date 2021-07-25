package uz.pdp.jokker.appwerehouse.payload;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CurrencyDto {

    private String name;
private boolean active;
}
