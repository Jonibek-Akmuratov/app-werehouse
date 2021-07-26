package uz.pdp.jokker.appwerehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.jokker.appwerehouse.entity.Input_product;

import java.util.List;
import java.util.Optional;

@Repository
public interface InputProductRepository extends JpaRepository<Input_product,Integer> {

    List<Input_product> findAllByInputId(Integer input_id);


}
