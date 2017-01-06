package pl.polsl.bonus.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.polsl.bonus.model.BonusList;

public interface BonusListJpaRepository extends JpaRepository<BonusList, Integer >{

}
