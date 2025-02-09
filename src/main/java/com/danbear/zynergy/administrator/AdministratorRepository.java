package com.danbear.zynergy.administrator;

import com.danbear.zynergy.organization.Organization;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrganizationRepository extends JpaRepository<Organization, Long> {
  boolean existsByOrgEmail(String orgEmail);
}
