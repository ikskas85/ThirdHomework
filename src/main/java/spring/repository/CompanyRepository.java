package spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.entities.Company;


public interface CompanyRepository extends JpaRepository<Company, Integer> {
}