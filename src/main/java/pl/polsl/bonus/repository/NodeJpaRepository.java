package pl.polsl.bonus.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.polsl.bonus.model.Node;

public interface NodeJpaRepository extends JpaRepository <Node, Integer>{

}
