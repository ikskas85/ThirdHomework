package spring.dto;

import java.time.LocalDate;

public record ProjectDto(String name,
                         LocalDate date) {
}
