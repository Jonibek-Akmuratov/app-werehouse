package uz.pdp.jokker.appwerehouse.payload;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class OutputProductDto {

    private Integer productId;

    private Integer outputId;

    private Integer amount;

    private Integer price;

}
