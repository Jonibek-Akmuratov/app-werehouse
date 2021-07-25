package uz.pdp.jokker.appwerehouse.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import uz.pdp.jokker.appwerehouse.entity.Category;

import javax.persistence.Column;
import javax.persistence.ManyToOne;

@Data
@AllArgsConstructor
public class CategoryDto {

    private String name;

    private Integer parentCategory;
}
