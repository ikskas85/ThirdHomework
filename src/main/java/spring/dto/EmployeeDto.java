package spring.dto;

import java.util.UUID;

public record EmployeeDto(UUID id,
                          String firstName,
                          String lastName,
                          String role,
                          String email,
                          Integer companyId) {
}
