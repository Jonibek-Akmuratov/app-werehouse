package uz.pdp.jokker.appwerehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.jokker.appwerehouse.entity.Output;

@Repository
public interface OutputRepository extends JpaRepository<Output,Integer> {

    boolean existsByFactureNumber(String s);

}
