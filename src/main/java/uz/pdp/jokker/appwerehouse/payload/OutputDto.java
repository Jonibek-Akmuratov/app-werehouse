package uz.pdp.jokker.appwerehouse.payload;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class OutputDto {

    private Date date;

    private Integer werehouseId;

    private Integer clientId;

    private Integer currencyId;

    private String facture_number;

}
