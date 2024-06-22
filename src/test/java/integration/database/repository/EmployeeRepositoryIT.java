package integration.database.repository;

import integration.annotation.IT;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import spring.repository.EmployeeRepository;

import java.util.UUID;


@IT
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
class EmployeeRepositoryIT {
    @Autowired
    private final EmployeeRepository employeeRepository;

    @Test
    void check() {
        var first = employeeRepository.findById(UUID.fromString("ced8665f-535e-4382-a289-fb92e18c7c86"));
        log.error(first.toString());
    }
}