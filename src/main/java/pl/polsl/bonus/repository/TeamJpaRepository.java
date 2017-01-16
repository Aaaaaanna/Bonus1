package pl.polsl.bonus.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import pl.polsl.bonus.model.Employee;
import pl.polsl.bonus.model.Team;

public interface TeamJpaRepository extends JpaRepository <Team,Integer>{
	@Query("SELECT t FROM Team t WHERE t.teamManager is null")
	public Team findTopLevelTeam();
	
	@Query("SELECT t FROM Team t WHERE t.teamManager = :manager")
	public Team findTeamByManager(@Param("manager")Employee manager);
	
}
