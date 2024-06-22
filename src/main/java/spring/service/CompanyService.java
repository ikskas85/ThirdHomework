package spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.repository.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.dto.CompanyDto;
import spring.entities.Company;
import spring.repository.CompanyRepository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Transactional
@Service
public class CompanyService {

    private final CompanyRepository companyRepository;

    public void save(CompanyDto companyDto) {
        companyRepository.saveAndFlush(createCompany(companyDto));
    }

    public Optional<Company> readById(Integer id) {
        return companyRepository.findById(id);
    }

    public List<Company> readAll() {
        return companyRepository.findAll();
    }

    public void deleteById(CompanyDto companyDto) {
        companyRepository.delete(createCompany(companyDto));
    }

    public void update(CompanyDto companyDto) {
        companyRepository.saveAndFlush(createCompany(companyDto));
    }


    private Company createCompany(CompanyDto companyDto) {
        return Company.builder()
                .companyName(companyDto.name())
                .id(companyDto.id())
                .build();
    }
}
