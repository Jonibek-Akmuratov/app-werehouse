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
public class Werehouse extends AbsEntity {

    @Column(nullable = false,unique = true)
    private String name;

    @Column(nullable = false)
    private boolean active=true;

    public Werehouse(String name) {
        this.name = name;
    }
}
