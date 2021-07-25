package uz.pdp.jokker.appwerehouse.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.jokker.appwerehouse.entity.template.AbsEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "attachment")
@Entity
public class Attachment extends AbsEntity {

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false )
    private Long size;

    @Column(nullable = false )
    private String content_type;
}