package com.danbear.zynergy.organization;

import com.danbear.zynergy.organization.dto.OrganizationDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrganizationServiceImpl implements OrganizationService {
  
  private final OrganizationRepository organizationRepository;
  
  public OrganizationServiceImpl(OrganizationRepository organizationRepository) {
    this.organizationRepository = organizationRepository;
  }
  
  public List<OrganizationDto> findAllOrganizations() {
    List<Organization> organizations = organizationRepository.findAll();
    return organizations.stream().map((organization) -> mapToDto(organization)).collect(Collectors.toList());
  }
  
  public OrganizationDto findOrganizationById(Long id) {
    Organization organization = organizationRepository.findById(id).orElseThrow(
        () -> new OrganizationNotFoundException(
            "No organization found with organization_id: " + id
                + ". Please verify the organization_id and try again."
        ));
    return mapToDto(organization);
  }
  
  public OrganizationDto createOrganization(OrganizationDto organizationDto) {
    
    //Check if email already exist
    if (organizationRepository.existsByOrgEmail(organizationDto.getOrgEmail())) {
      throw new OrganizationUniqueEmailException(
          "The organization email " + organizationDto.getOrgEmail()
              + " is already in use. Please use a different email address.");
    }
    
    Organization organization = new Organization();
    
    organization.setOrgEmail(organizationDto.getOrgEmail());
    organization.setOrgName(organizationDto.getOrgName());
    organization.setOrgContactPerson(organizationDto.getOrgContactPerson());
    organization.setOrgAddress(organizationDto.getOrgAddress());
    organization.setOrgPhoneNumber(organizationDto.getOrgPhoneNumber());
    organization.setOrgMobileNumber(organizationDto.getOrgMobileNumber());
    organization.setOrgLogo(organizationDto.getOrgLogo());
    organization.setOrgDatabaseUrl(organizationDto.getOrgDatabaseUrl());
    organization.setSubscriptionStatus(organizationDto.getSubscriptionStatus());
    
    
    Organization savedOrganization = organizationRepository.save(organization);
    
    return mapToDto(savedOrganization);
  }
  
  
  public OrganizationDto updateOrganization(OrganizationDto organizationDto, Long id) {
    
    Organization organization = organizationRepository.findById(id).orElseThrow(
        () -> new OrganizationNotFoundException(
            "No organization found with organization_id: " + id
                + ". Please verify the organization_id and try again."
        ));
    
    //Check if email already exist
    if (organizationRepository.existsByOrgEmail(organizationDto.getOrgEmail())) {
      throw new OrganizationUniqueEmailException(
          "The organization email " + organizationDto.getOrgEmail()
              + " is already in use. Please use a different email address.");
    }
    
    organization.setOrgEmail(organizationDto.getOrgEmail());
    organization.setOrgName(organizationDto.getOrgName());
    organization.setOrgContactPerson(organizationDto.getOrgContactPerson());
    organization.setOrgAddress(organizationDto.getOrgAddress());
    organization.setOrgPhoneNumber(organizationDto.getOrgPhoneNumber());
    organization.setOrgMobileNumber(organizationDto.getOrgMobileNumber());
    organization.setOrgLogo(organizationDto.getOrgLogo());
    organization.setOrgDatabaseUrl(organizationDto.getOrgDatabaseUrl());
    
    organization.setSubscriptionStatus(organizationDto.getSubscriptionStatus());
    
    
    Organization updatedOrganization = organizationRepository.save(organization);
    
    return mapToDto(updatedOrganization);
  }
  
  public void deleteOrganization(Long id) {
    Organization organization = organizationRepository.findById(id).orElseThrow(
        () -> new OrganizationNotFoundException(
            "No organization found with organization_id: " + id
                + ". Please verify the organization_id and try again."
        ));
    
    organizationRepository.delete(organization);
  }
  
  // >>>>>>>>  Mappers
  public static Organization mapToEntity(OrganizationDto organization) {
    Organization organizationDto = Organization.builder()
        .orgId(organization.getOrgId())
        .orgEmail(organization.getOrgEmail())
        .orgName(organization.getOrgName())
        .orgContactPerson(organization.getOrgContactPerson())
        .orgAddress(organization.getOrgAddress())
        .orgPhoneNumber(organization.getOrgPhoneNumber())
        .orgMobileNumber(organization.getOrgMobileNumber())
        .orgLogo(organization.getOrgLogo())
        .orgDatabaseUrl(organization.getOrgDatabaseUrl())
        .subscriptionStatus(organization.getSubscriptionStatus())
        .build();
    
    
    
    return organizationDto;
  }
  
  public static OrganizationDto mapToDto(Organization organization) {
    OrganizationDto organizationDto = OrganizationDto.builder()
        .orgId(organization.getOrgId())
        .orgEmail(organization.getOrgEmail())
        .orgName(organization.getOrgName())
        .orgContactPerson(organization.getOrgContactPerson())
        .orgAddress(organization.getOrgAddress())
        .orgPhoneNumber(organization.getOrgPhoneNumber())
        .orgMobileNumber(organization.getOrgMobileNumber())
        .orgLogo(organization.getOrgLogo())
        .orgDatabaseUrl(organization.getOrgDatabaseUrl())
        .subscriptionStatus(organization.getSubscriptionStatus())
        .build();
    return organizationDto;
  }
}
