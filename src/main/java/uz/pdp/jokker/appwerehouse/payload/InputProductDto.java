package uz.pdp.jokker.appwerehouse.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import uz.pdp.jokker.appwerehouse.entity.Input;
import uz.pdp.jokker.appwerehouse.entity.Product;

import javax.persistence.ManyToOne;
import java.util.Date;

@Data
@AllArgsConstructor
public class InputProductDto {


    private Integer productsId;


    private Integer inputId;

    private Integer amount;

    private Integer price;

    private Date expire_date;
}
