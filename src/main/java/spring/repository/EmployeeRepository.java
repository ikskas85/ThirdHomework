package spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.entities.Employee;

import java.util.UUID;

public interface EmployeeRepository extends JpaRepository<Employee, UUID> {
}
