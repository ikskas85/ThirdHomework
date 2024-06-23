package spring.http.mapper;

import spring.dto.CompanyDto;
import spring.entities.Company;

import java.util.List;
import java.util.Optional;

public interface CompanyMapper {

    static Company mappedDtoToCompany(CompanyDto dto) {
        return Company.builder()
                .id(dto.id())
                .companyName(dto.name())
                .build();
    }

    static CompanyDto mappedCompanyToDto(Company company) {
        return new CompanyDto(
                company.getId(),
                company.getCompanyName()
        );
    }

    static Optional<CompanyDto> optionalMappedCompanyToDto(Optional<Company> company) {
        return company.stream()
                .map(CompanyMapper::mappedCompanyToDto)
                .findFirst();
    }

    static List<CompanyDto> mappedToDtoList(List<Company> companyList) {
        return companyList.stream()
                .map(CompanyMapper::mappedCompanyToDto)
                .toList();
    }
}
