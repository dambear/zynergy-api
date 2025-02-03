package com.danbear.zynergy.organization;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OrganizationRepository extends JpaRepository<Organization, Long> {
  boolean existsByOrgEmail(String orgEmail);
}
