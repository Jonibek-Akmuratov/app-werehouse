package uz.pdp.jokker.appwerehouse.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.pdp.jokker.appwerehouse.entity.template.AbsEntity;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import java.util.Date;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Input_product extends AbsEntity {

    @ManyToOne
    private Product products;

    @ManyToOne
    private Input input;

    private Integer amount;

    private Integer price;

    private Date expire_date;
}
