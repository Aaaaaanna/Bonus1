package pl.polsl.bonus.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.prepost.PreAuthorize;

import pl.polsl.bonus.model.Employee;
import pl.polsl.bonus.model.Team;

public interface EmployeeJpaRepository extends JpaRepository<Employee, Integer>{
	@Query("SELECT e FROM Employee e WHERE e.team = :team")
	public List<Employee> findEmployeesByTeam(@Param("team") Team team);
	
	@Query("SELECT e FROM Employee e WHERE e.login = :login")
	public Employee findEmployeesByLogin(@Param("login") String login);
	
	
	@PreAuthorize("hasAuthority=('ADMIN')")
	public List<Employee> findAll();
	
}
