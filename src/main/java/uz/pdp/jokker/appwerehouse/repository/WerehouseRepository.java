package uz.pdp.jokker.appwerehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.jokker.appwerehouse.entity.Werehouse;

@Repository
public interface WerehouseRepository extends JpaRepository<Werehouse,Integer> {

boolean existsByName(String name);

boolean existsByNameNot(String name);
}
