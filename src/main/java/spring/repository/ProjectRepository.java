package spring.repository;

import org.springframework.data.repository.CrudRepository;
import spring.entities.Project;


public interface ProjectRepository extends CrudRepository<Project, String> {

}
