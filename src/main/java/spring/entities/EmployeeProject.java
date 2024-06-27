package spring.entities;

import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = {"employee", "project"})
@EqualsAndHashCode(of = "id")

@Entity
@Table(name = "employes_projects")
public class EmployeeProject {
    @Id
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_uuid")
    private Employee employee;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "projects_name")
    private Project project;
}