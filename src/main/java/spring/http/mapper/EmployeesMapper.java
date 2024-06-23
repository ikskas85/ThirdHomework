package spring.http.mapper;

import spring.dto.EmployeeDto;
import spring.entities.Company;
import spring.entities.Employee;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface EmployeesMapper {

    static EmployeeDto mappedToDto(Employee employee) {
        return new EmployeeDto(employee.getId(),
                employee.getFirstName(),
                employee.getLastName(),
                employee.getRole(),
                employee.getEmail(),
                employee.getCompany().getId());
    }

    static List<EmployeeDto> mappedListToDto(List<Employee> employees) {
        return employees.stream()
                .map(EmployeesMapper::mappedToDto)
                .toList();
    }

    static Optional<EmployeeDto> optionalMappedToDto(Optional<Employee> employee) {
        return employee.stream()
                .map(EmployeesMapper::mappedToDto)
                .findFirst();
    }


    static Employee mappedDtoToEmployee(EmployeeDto employeeDto) {
        var company = Company.builder()
                .id(employeeDto.companyId())
                .build();
        return Employee.builder()
                .id(employeeDto.id())
                .firstName(employeeDto.firstName())
                .lastName(employeeDto.lastName())
                .role(employeeDto.role())
                .email(employeeDto.email())
                .company(company)
                .build();
    }
}
