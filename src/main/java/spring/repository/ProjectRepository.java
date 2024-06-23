package spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.entities.Project;


public interface ProjectRepository extends JpaRepository<Project, String> {

}
