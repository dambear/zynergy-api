package com.danbear.zynergy.administrator;

import com.danbear.zynergy.administrator.dto.AdministratorDto;
import com.danbear.zynergy.common.exception.BadRequestException;
import com.danbear.zynergy.common.exception.NotFoundException;
import com.danbear.zynergy.organization.Organization;
import com.danbear.zynergy.organization.OrganizationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdministratorServiceImpl implements AdministratorService {
  
  private final AdministratorRepository administratorRepository;
  private final OrganizationRepository organizationRepository;
  
  public AdministratorServiceImpl(
      AdministratorRepository administratorRepository, OrganizationRepository organizationRepository) {
    this.administratorRepository = administratorRepository;
    this.organizationRepository = organizationRepository;
  }
  
  public List<AdministratorDto> findAllAdministrators() {
    List<Administrator> administrators = administratorRepository.findAll();
    return administrators.stream().map(AdministratorServiceImpl::mapToDto).collect(Collectors.toList());
  }
  
  public AdministratorDto findAdministratorById(Long id) {
    Administrator administrator = administratorRepository.findById(id).orElseThrow(
        () -> new NotFoundException(
            "No administrator found with administrator_id: " + id
                + ". Please verify the administrator_id and try again."
        ));
    return mapToDto(administrator);
  }
  
  public AdministratorDto createAdministrator(AdministratorDto administratorDto) {
    
    //Check if email already exist
    if (administratorRepository.existsByEmail(administratorDto.getEmail())) {
      throw new BadRequestException(
          "The administrator email '" + administratorDto.getEmail()
              + "' is already in use. Please use a different email address.");
    }
    
    Long organization_id = administratorDto.getOrganization().getOrganizationId();
    
    Organization organization = organizationRepository.findById(organization_id).orElseThrow(
        () -> new NotFoundException(
            "No organization found with organization_id: " + organization_id
                + ". Please verify the organization_id and try again."
        ));
    
    if(organization.getAdministrator() != null &&
        organization.getAdministrator().getAdministratorId() != null) {
      throw new BadRequestException(
          "The organization '" + organization.getOrgName() + "' already has an administrator. "
              + "Only one administrator can be assigned per organization. Please verify the "
              + "organization details before assigning a new one."
      );
    }
    
    
    Administrator administrator = new Administrator();
    
    return getAdministratorDto(administratorDto, organization, administrator);
  }
  
  
  public AdministratorDto updateAdministrator(AdministratorDto administratorDto, Long id) {
    
    Administrator administrator = administratorRepository.findById(id).orElseThrow(
        () -> new NotFoundException(
            "No administrator found with administrator_id: " + id
                + ". Please verify the administrator_id and try again."
        ));
    
    //Check if email already exist
    if (!administrator.getEmail().equals(administratorDto.getEmail())
        && administratorRepository.existsByEmail(administratorDto.getEmail())) {
      throw new BadRequestException(
          "The administrator email '" + administratorDto.getEmail()
              + "' is already in use. Please use a different email address.");
    }
    
    Long organization_id = administratorDto.getOrganization().getOrganizationId();
    
    Organization organization = organizationRepository.findById(organization_id).orElseThrow(
        () -> new RuntimeException(
            "No organization found with organization_id: " + organization_id
                + ". Please verify the organization_id and try again."
        ));
    
    return getAdministratorDto(administratorDto, organization, administrator);
  }
  
  public void deleteAdministrator(Long id) {
    Administrator administrator = administratorRepository.findById(id).orElseThrow(
        () -> new NotFoundException(
            "No administrator found with administrator_id: " + id
                + ". Please verify the administrator_id and try again."
        ));
    
    Long organization_id = administrator.getOrganization().getOrganizationId();
    
    if (organization_id != null) {
      Organization organization = organizationRepository.findById(organization_id).orElseThrow(
          () -> new NotFoundException(
              "No organization found with organization_id: " + id
                  + ". Please verify the organization_id and try again."
          ));
      // make organization null
      organization.setAdministrator(null);
      organizationRepository.save(organization);
      
    }
    
    
    administratorRepository.delete(administrator);
  }
  
  // >>>>>>  Use in Create and Update  <<<<<<
  private AdministratorDto getAdministratorDto(AdministratorDto administratorDto, Organization organization, Administrator administrator) {
    
    administrator.setName(administratorDto.getName());
    administrator.setEmail(administratorDto.getEmail());
    administrator.setPassword(administratorDto.getPassword());
    administrator.setOrganization(organization);
    administrator.setAdministratorType(administratorDto.getAdministratorType());
    
    Administrator savedAdministrator = administratorRepository.save(administrator);
    
    return mapToDto(savedAdministrator);
  }
  
  // >>>>>>  Mapper to Dto  <<<<<<
  public static AdministratorDto mapToDto(Administrator administrator) {
    return AdministratorDto.builder()
        .administratorId(administrator.getAdministratorId())
        .name(administrator.getName())
        .email(administrator.getEmail())
        .password(administrator.getPassword())
        .organization(administrator.getOrganization())
        .administratorType(administrator.getAdministratorType())
        .build();
  }
}
