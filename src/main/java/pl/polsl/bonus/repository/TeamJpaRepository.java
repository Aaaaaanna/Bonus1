package pl.polsl.bonus.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.polsl.bonus.model.Team;

public interface TeamJpaRepository extends JpaRepository <Team,Integer>{

}
