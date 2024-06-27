package spring.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = "employeeProjects")
@EqualsAndHashCode(of = "name")
@Entity
@Table(name = "projects")
public class Project {
    @Id
    private String name;
    @Column(name = "startdate")
    private LocalDate startDate;


    @Builder.Default
    @OneToMany(mappedBy = "project")
    private List<EmployeeProject> employeeProjects = new ArrayList<>();
}