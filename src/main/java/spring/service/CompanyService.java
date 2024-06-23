package spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.dto.CompanyDto;
import spring.repository.CompanyRepository;

import java.util.List;
import java.util.Optional;

import static spring.http.mapper.CompanyMapper.*;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class CompanyService {

    private final CompanyRepository companyRepository;

    @Transactional
    public void save(CompanyDto companyDto) {
        companyRepository.saveAndFlush(mappedDtoToCompany(companyDto));
    }

    public Optional<CompanyDto> readById(Integer id) {
        return optionalMappedCompanyToDto(companyRepository.findById(id));
    }

    public List<CompanyDto> readAll() {
        return mappedToDtoList(companyRepository.findAll());
    }

    @Transactional
    public boolean deleteById(Integer id) {
        return companyRepository.findById(id).map(entity -> {
            companyRepository.delete(entity);
            return true;
        }).orElse(false);
    }

    @Transactional
    public void update(CompanyDto companyDto) {
        companyRepository.saveAndFlush(mappedDtoToCompany(companyDto));
    }
}
