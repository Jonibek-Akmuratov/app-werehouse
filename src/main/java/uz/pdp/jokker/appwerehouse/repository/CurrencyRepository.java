package uz.pdp.jokker.appwerehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.jokker.appwerehouse.entity.Currency;

@Repository
public interface CurrencyRepository extends JpaRepository<Currency,Integer> {

    boolean existsByName(String name);

}
