package integration.service;

import integration.annotation.IT;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import spring.service.CompanyService;

@IT
@RequiredArgsConstructor
class CompanyServiceIT {

    private final CompanyService companyService;

    //private final DBProperties dbProperties;

    @Test
    void test() {
    }
}
