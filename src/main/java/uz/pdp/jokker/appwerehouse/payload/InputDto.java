package uz.pdp.jokker.appwerehouse.payload;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class InputDto {

    private Date date;

    private Integer werehouseId;

    private Integer supplierId;

    private Integer currencyId;

    private String factureNumber;



}
