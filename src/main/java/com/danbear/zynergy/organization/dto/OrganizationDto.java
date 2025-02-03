package com.danbear.zynergy.organization.dto;

import com.danbear.zynergy.organization.Organization;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class OrganizationDto {
  private Long orgId;
  
  @NotEmpty @Column(unique = true)
  @Email(message = "Organization email must be a valid email address")
  private String orgEmail;
  
  @NotEmpty
  private String orgName;
  
  @NotEmpty
  private String orgContactPerson;
  
  @NotEmpty
  private String orgAddress;
  
  private String orgPhoneNumber;
  
  private String orgMobileNumber;
  
  @NotEmpty
  private String orgLogo;
  

  private String orgDatabaseUrl;
  
  @Enumerated(EnumType.STRING)
  private Organization.SubscriptionStatus subscriptionStatus;
  
}
