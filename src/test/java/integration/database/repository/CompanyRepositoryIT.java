package integration.database.repository;

import integration.annotation.IT;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import spring.entities.Company;
import spring.repository.CompanyRepository;

import java.util.concurrent.atomic.AtomicLong;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@Slf4j
@IT

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
class CompanyRepositoryIT {

    private final Integer TEST_COMPANY_ID = 21;
    private final CompanyRepository companyRepository;
    private final EntityManager manager;
    Company company;
    Company secondCompany;

    @BeforeEach
    void setUp() {
        company = Company.builder()
                .companyName("Test")
                .id(TEST_COMPANY_ID)
                .build();
        secondCompany = Company.builder()
                .companyName("newTest")
                .id(TEST_COMPANY_ID)
                .build();
    }

    @Test
    void create() {
        AtomicLong count = new AtomicLong(companyRepository.count());
        companyRepository.save(company);
        manager.flush();
        assertEquals(count.incrementAndGet(), companyRepository.count());
    }

    @Test
    void findById() {
        assertNotNull(companyRepository.findById(TEST_COMPANY_ID));
    }

    @Test
    void update() {
        companyRepository.saveAndFlush(secondCompany);
        manager.flush();
        assertEquals(secondCompany.getCompanyName(), companyRepository.findById(TEST_COMPANY_ID).get().getCompanyName());
    }

    @Test
    void delete() {
        companyRepository.delete(secondCompany);
        manager.flush();
    }
}
