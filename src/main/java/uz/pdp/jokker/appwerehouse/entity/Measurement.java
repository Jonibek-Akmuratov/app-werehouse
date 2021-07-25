package uz.pdp.jokker.appwerehouse.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.pdp.jokker.appwerehouse.entity.template.AbsEntity;

import javax.persistence.Column;
import javax.persistence.Entity;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Measurement extends AbsEntity {// o'lchov birligi

    @Column(nullable = false,unique = true)
    private String name;

    private boolean active=true;

    public Measurement(String name) {
        this.name = name;
    }
}
