package uz.pdp.jokker.appwerehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.jokker.appwerehouse.entity.Input_product;

@Repository
public interface InputProductRepository extends JpaRepository<Input_product,Integer> {
}
