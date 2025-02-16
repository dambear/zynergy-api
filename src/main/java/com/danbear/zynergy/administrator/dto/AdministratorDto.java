package com.danbear.zynergy.administrator.dto;

import com.danbear.zynergy.administrator.Administrator;
import com.danbear.zynergy.organization.Organization;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AdministratorDto {
  
  private Long administratorId;
  
  @NotEmpty
  private String name;
  
  @NotEmpty @Column(unique = true)
  @Email(message = "Organization email must be a valid email address")
  private String email;
  
  @NotEmpty
  private String password;
  
  @JsonIgnoreProperties({"administrator"}) // Use a foreign key
  private Organization organization;
  
  @Enumerated(EnumType.STRING)
  private Administrator.AdministratorType administratorType;
}
