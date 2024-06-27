package spring.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
@ToString(exclude = "employees")
@EqualsAndHashCode(of = "id")
@Table(name = "companies")
public class Company {

    @Id
    private Integer id;
    @Column(name = "company_name")
    private String companyName;

    @Builder.Default
    @OneToMany(mappedBy = "company")
    private List<Employee> employees = new ArrayList<>();
}