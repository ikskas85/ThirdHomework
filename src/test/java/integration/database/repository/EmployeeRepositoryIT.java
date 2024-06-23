package integration.database.repository;

import integration.IntegrationTestBase;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import spring.entities.Company;
import spring.entities.Employee;
import spring.repository.EmployeeRepository;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

import static java.util.UUID.fromString;
import static java.util.UUID.randomUUID;
import static org.junit.jupiter.api.Assertions.*;

@Transactional
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
class EmployeeRepositoryIT extends IntegrationTestBase {
    @Autowired
    private final EmployeeRepository employeeRepository;
    private Integer first_id = 0;
    private Integer second_id = 1;
    Employee employee;
    Employee secondEmployee;

    @BeforeEach
    void setUp() {
        employee = Employee.builder()
                .id(randomUUID())
                .firstName("First")
                .lastName("First")
                .role("Developer")
                .email("first@mail.ru")
                .company(new Company(
                        first_id,
                        "Google",
                        null))
                .build();
        secondEmployee = Employee.builder()
                .id(randomUUID())
                .firstName("Second")
                .lastName("Second")
                .role("Developer")
                .email("second@mail.ru")
                .company(new Company(
                        second_id,
                        "Amazon",
                        null))
                .build();
    }

    @Test
    void create() {
        AtomicLong count = new AtomicLong(employeeRepository.findAll().size());
        employeeRepository.save(employee);
        assertEquals(count.incrementAndGet(), employeeRepository.count());
    }

    @Test
    void findById() {
        Optional<Employee> someEmployee = employeeRepository.findById(fromString("563dbd52-0f82-4fd5-b74d-8ac2c13e881c"));
        assertTrue(someEmployee.isPresent());
    }

    @Test
    void update() {
        employeeRepository.saveAndFlush(secondEmployee);
        assertEquals(secondEmployee.getFirstName(), employeeRepository.findById(secondEmployee.getId()).get().getFirstName());
    }

    @Test
    void delete() {
        employeeRepository.saveAndFlush(secondEmployee);
        int size = employeeRepository.findAll().size();
        employeeRepository.delete(secondEmployee);
        assertEquals(size - 1, employeeRepository.findAll().size());
    }
}