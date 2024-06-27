package spring.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"company", "employeeProjects"})
@EqualsAndHashCode(of = "id")
@Entity
@Builder
@Table(name = "employs")
public class Employee {
    @Id
    private UUID id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    private String role;
    private String email;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private Company company;

    @Builder.Default
    @OneToMany(mappedBy = "employee")
    private final List<EmployeeProject> employeeProjects = new ArrayList<>();

}