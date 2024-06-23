package integration.database.repository;

import integration.IntegrationTestBase;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import spring.entities.Company;
import spring.repository.CompanyRepository;

import java.util.concurrent.atomic.AtomicLong;

import static org.junit.jupiter.api.Assertions.*;


@RequiredArgsConstructor(onConstructor = @__(@Autowired))
class CompanyRepositoryIT extends IntegrationTestBase {

    private final Integer TEST_ID = 21;
    private final CompanyRepository companyRepository;
    Company company;
    Company secondCompany;

    @BeforeEach
    void setUp() {
        company = Company.builder()
                .companyName("Test")
                .id(TEST_ID)
                .build();
        secondCompany = Company.builder()
                .companyName("newTest")
                .id(TEST_ID)
                .build();
    }

    @Transactional
    @Test
    void create() {
        AtomicLong count = new AtomicLong(companyRepository.findAll().size());
        companyRepository.saveAndFlush(company);
        assertEquals(count.incrementAndGet(), companyRepository.count());
    }

    @Test
    @Transactional
    void findById() {
        companyRepository.saveAndFlush(company);
        assertTrue(companyRepository.findById(TEST_ID).isPresent());
    }

    @Transactional
    @Test
    void update() {
        companyRepository.saveAndFlush(secondCompany);
        assertNotNull(companyRepository.findById(secondCompany.getId()));
    }

    @Transactional
    @Test
    void delete() {
        companyRepository.saveAndFlush(secondCompany);
        int size = companyRepository.findAll().size();
        companyRepository.delete(secondCompany);
        assertEquals(size - 1, companyRepository.findAll().size());
    }
}
