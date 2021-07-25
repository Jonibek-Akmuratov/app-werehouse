package uz.pdp.jokker.appwerehouse.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.pdp.jokker.appwerehouse.entity.template.AbsEntity;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Product extends AbsEntity {

    @Column(nullable = false,unique = true)
    private String name;

    @ManyToOne
    private Category category;

    @OneToOne
    private Attachment photo_id;

    @Column(nullable = false,unique = true)
    private String code;

    @OneToOne
    private Measurement measurement;

    private boolean active=true;

    public Product(String name, Category category, Attachment photo_id, String code, Measurement measurement) {
        this.name = name;
        this.category = category;
        this.photo_id = photo_id;
        this.code = code;
        this.measurement = measurement;
    }
}
