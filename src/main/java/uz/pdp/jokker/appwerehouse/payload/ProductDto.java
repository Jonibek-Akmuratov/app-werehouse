package uz.pdp.jokker.appwerehouse.payload;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductDto {

    private String name;
    private Integer category_id;
    private Integer photo_id;
    private Integer measurement_id;
    private boolean active;



}
