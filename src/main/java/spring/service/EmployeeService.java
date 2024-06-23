package spring.service;

import lombok.RequiredArgsConstructor;
import spring.dto.EmployeeDto;
import spring.entities.Company;
import spring.entities.Employee;
import spring.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    public void save(EmployeeDto employeeDto) {
        employeeRepository.saveAndFlush(createEmployee(employeeDto));
    }

    public Optional<Employee> readById(UUID uuid) {
        return employeeRepository.findById(uuid);
    }

    public List<Employee> readAllEmployees() {
        return employeeRepository.findAll();
    }

    public void deleteById(EmployeeDto employeeDto) {
        employeeRepository.delete(createEmployee(employeeDto));
    }

    public void update(EmployeeDto employeeDto) {
        employeeRepository.saveAndFlush(createEmployee(employeeDto));
    }

    private Employee createEmployee(EmployeeDto employeeDto) {
        Company company = Company.builder()
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
