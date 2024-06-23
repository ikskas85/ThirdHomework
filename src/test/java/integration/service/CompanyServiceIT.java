package integration.service;

import integration.IntegrationTestBase;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import spring.dto.CompanyDto;
import spring.service.CompanyService;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
class CompanyServiceIT extends IntegrationTestBase {

    private final CompanyService companyService;
    private CompanyDto testDto;

    @BeforeEach
    public void setUp() {
        testDto = new CompanyDto(10000, "Test");
    }

    @Test
    void findAll() {
        List<CompanyDto> companyDtos = companyService.readAll();
        assertFalse(companyDtos.isEmpty());
        assertEquals(companyDtos.size(), 5);
    }

    @Test
    void findById() {
        Optional<CompanyDto> maybeUser = companyService.readById(1);
        assertTrue(maybeUser.isPresent());
        maybeUser.ifPresent(company -> assertEquals("Amazon", company.name()));
    }

    @Test
    void create() {
        int size = companyService.readAll().size();
        companyService.save(testDto);
        assertTrue(companyService.readAll().size() > size);
    }

    @Test
    void update() {
        companyService.save(testDto);
        companyService.update(new CompanyDto(testDto.id(), "newName"));
        assertEquals("newName", companyService.readById(testDto.id()).get().name());
    }

    @Test
    void delete() {
        companyService.save(testDto);
        int size = companyService.readAll().size();
        companyService.deleteById(testDto.id());
        assertTrue(size > companyService.readAll().size());
    }
}
