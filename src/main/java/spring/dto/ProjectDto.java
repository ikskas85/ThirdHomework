package spring.dto;

import java.time.LocalDate;
import java.util.List;

public record ProjectDto(String name, LocalDate date, List<String> employees) {
}
