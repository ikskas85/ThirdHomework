package integration.database.repository;

import integration.IntegrationTestBase;
import integration.annotation.IT;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import spring.entities.Company;
import spring.repository.CompanyRepository;

import java.util.concurrent.atomic.AtomicLong;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@RequiredArgsConstructor(onConstructor = @__(@Autowired))
class CompanyRepositoryIT extends IntegrationTestBase {

    private Integer testId = 21;
    private final CompanyRepository companyRepository;
    Company company;
    Company secondCompany;

    @BeforeEach
    void setUp() {
        company = Company.builder()
                .companyName("Test")
                .id(testId)
                .build();
        secondCompany = Company.builder()
                .companyName("newTest")
                .id(testId)
                .build();
    }

    @Test
    void create() {
        AtomicLong count = new AtomicLong(companyRepository.findAll().size());
        companyRepository.saveAndFlush(company);
        assertEquals(count.incrementAndGet(), companyRepository.count());
    }

    @Test
    void findById() {
        assertNotNull(companyRepository.findById(testId));
    }

    @Test
    void update() {
        companyRepository.saveAndFlush(secondCompany);
        assertNotNull(companyRepository.findById(secondCompany.getId()));
    }

    @Test
    void delete() {
        companyRepository.saveAndFlush(secondCompany);
        int size = companyRepository.findAll().size();
        companyRepository.delete(secondCompany);
        assertEquals(size - 1, companyRepository.findAll().size());
    }
}
