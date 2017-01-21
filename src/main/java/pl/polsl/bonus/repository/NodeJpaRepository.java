package pl.polsl.bonus.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import pl.polsl.bonus.model.Employee;
import pl.polsl.bonus.model.Node;

public interface NodeJpaRepository extends JpaRepository <Node, Integer>{

	@Query("SELECT n FROM Node n WHERE n.parentNode = :parentNode")
	public List<Node> findNodesByParentNode(@Param("parentNode")Node parentNode);
	@Query ("SELECT n FROM Node n WHERE n.parentNode = :parentNode and n.employee= :employee" )
	public Node findNodeByEmployeeIdAndParentNode(@Param("parentNode")Node parentNode, @Param("employee") Employee employee);

	
}
