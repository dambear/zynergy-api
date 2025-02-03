package com.danbear.zynergy.organization;

import com.danbear.zynergy.organization.dto.OrganizationDto;

import java.util.List;


public interface OrganizationService {
  List<OrganizationDto> findAllOrganizations();
  OrganizationDto findOrganizationById(Long id);
  OrganizationDto createOrganization(OrganizationDto organizationDto);
  OrganizationDto updateOrganization(OrganizationDto organizationDto, Long id);
  void deleteOrganization(Long id);
}