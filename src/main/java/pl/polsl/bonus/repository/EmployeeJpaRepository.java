package pl.polsl.bonus.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.polsl.bonus.model.Employee;

public interface EmployeeJpaRepository extends JpaRepository<Employee, Integer>{

}
