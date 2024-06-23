package spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import spring.dto.EmployeeDto;
import spring.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static spring.http.mapper.EmployeesMapper.*;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    @Transactional
    public void save(EmployeeDto employeeDto) {
        employeeRepository.saveAndFlush(mappedDtoToEmployee(employeeDto));
    }

    public Optional<EmployeeDto> readById(UUID uuid) {
        return optionalMappedToDto(employeeRepository.findById(uuid));
    }

    public List<EmployeeDto> readAll() {
        return mappedListToDto(employeeRepository.findAll());
    }

    @Transactional
    public boolean deleteById(UUID uuid) {
        return employeeRepository.findById(uuid).map(entity -> {
            employeeRepository.delete(entity);
            return true;
        }).orElse(false);
    }

    @Transactional
    public void update(EmployeeDto employeeDto) {
        employeeRepository.saveAndFlush(mappedDtoToEmployee(employeeDto));
    }
}
