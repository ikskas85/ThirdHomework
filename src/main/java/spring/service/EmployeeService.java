package spring.service;

import lombok.RequiredArgsConstructor;
import spring.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

}
