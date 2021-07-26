package uz.pdp.jokker.appwerehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.jokker.appwerehouse.entity.Input;
import uz.pdp.jokker.appwerehouse.payload.DateDto;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@Repository
public interface InputRepository extends JpaRepository<Input,Integer> {

    boolean existsByFactureNumber(String s);

List<Input> findAllByDateEquals(DateFormat date);

}
