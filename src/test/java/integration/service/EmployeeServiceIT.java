package integration.service;

import integration.IntegrationTestBase;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import spring.dto.EmployeeDto;
import spring.service.EmployeeService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static java.util.UUID.fromString;
import static org.junit.jupiter.api.Assertions.*;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
class EmployeeServiceIT extends IntegrationTestBase {
    private final EmployeeService employeeService;
    private EmployeeDto employeeDto;

    @BeforeEach
    public void setUp() {
        UUID uuid = UUID.randomUUID();
        employeeDto = new EmployeeDto(
                uuid,
                "Test",
                "Test",
                "SomeRole",
                "test@email.ru",
                2);
    }

    @Test
    void findAll() {
        List<EmployeeDto> employeeDtos = employeeService.readAll();
        assertFalse(employeeDtos.isEmpty());
        assertEquals(employeeDtos.size(), 10);
    }

    @Test
    void findById() {
        Optional<EmployeeDto> maybeEmployee = employeeService.readById(fromString("57af8054-11ed-471b-b03e-b97d6ff32215"));
        assertTrue(maybeEmployee.isPresent());
        maybeEmployee.ifPresent(employee -> assertEquals("Ives", employee.firstName()));
    }

    @Transactional
    @Test
    void create() {
        int size = employeeService.readAll().size();
        employeeService.save(employeeDto);
        assertTrue(employeeService.readAll().size() > size);
    }

    @Transactional
    @Test
    void update() {
        employeeService.save(employeeDto);
        employeeService.update(new EmployeeDto(employeeDto.id(), "Alex", "some", "some", "some", 2));
        assertEquals("Alex", employeeService.readById(employeeDto.id()).get().firstName());
    }

    @Transactional
    @Test
    void delete() {
        employeeService.save(employeeDto);
        int size = employeeService.readAll().size();
        employeeService.deleteById(employeeDto.id());
        assertTrue(size > employeeService.readAll().size());
    }
}
