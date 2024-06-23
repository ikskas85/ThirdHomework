package controller.mappers;

import integration.annotation.IT;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import spring.ApplicationRunner;
import spring.dto.CompanyDto;
import spring.dto.EmployeeDto;
import spring.dto.ProjectDto;
import spring.entities.Company;
import spring.entities.Employee;
import spring.entities.Project;
import spring.http.mapper.EmployeesMapper;
import spring.http.mapper.ProjectMapper;

import java.time.LocalDate;
import java.util.Optional;

import static java.util.UUID.randomUUID;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static spring.http.mapper.CompanyMapper.*;
import static spring.http.mapper.EmployeesMapper.*;
import static spring.http.mapper.ProjectMapper.mappedDtoToProject;
import static spring.http.mapper.ProjectMapper.optionalMappedToDto;

@IT
class MappersTest {

    @Test
    void testCompanyMappers() {
        CompanyDto companyDto = new CompanyDto(2, "test");
        Company company = mappedDtoToCompany(companyDto);

        assertEquals(company.getCompanyName(), companyDto.name());
        assertEquals(company.getId(), companyDto.id());

        assertEquals(mappedCompanyToDto(company), companyDto);

        assertEquals(optionalMappedCompanyToDto(
                        Optional.of(company)),
                Optional.of(companyDto));
    }

    @Test
    void testEmployeeMappers() {
        EmployeeDto employeeDto = new EmployeeDto(
                randomUUID(),
                "test",
                "test",
                "test",
                "test",
                2);
        Employee employee = mappedDtoToEmployee(employeeDto);

        assertEquals(employee.getId(), employeeDto.id());
        assertEquals(employee.getFirstName(), employeeDto.firstName());

        assertEquals(mappedToDto(employee), employeeDto);

        assertEquals(EmployeesMapper.optionalMappedToDto(
                        Optional.of(employee)),
                Optional.of(employeeDto));
    }

    @Test
    void testProjectMappers() {
        ProjectDto projectDto = new ProjectDto(
                "Test",
                LocalDate.of(2000, 1, 1));
        Project project = mappedDtoToProject(projectDto);

        assertEquals(project.getName(), projectDto.name());
        assertEquals(project.getStartDate(), projectDto.date());

        assertEquals(ProjectMapper.mappedToDto(project), projectDto);

        assertEquals(optionalMappedToDto(
                        Optional.of(project)),
                Optional.of(projectDto));
    }
}
