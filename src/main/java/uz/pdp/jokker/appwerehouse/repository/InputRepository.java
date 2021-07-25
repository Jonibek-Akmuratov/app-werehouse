package uz.pdp.jokker.appwerehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.jokker.appwerehouse.entity.Input;

import java.util.Optional;


@Repository
public interface InputRepository extends JpaRepository<Input,Integer> {

    boolean existsByFactureNumber(String s);

}
