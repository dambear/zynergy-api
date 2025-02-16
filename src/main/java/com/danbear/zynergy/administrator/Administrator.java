package com.danbear.zynergy.administrator;

import com.danbear.zynergy.organization.Organization;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "administrator")
public class Administrator {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long administratorId;
  
  @NotEmpty
  private String name;
  
  @NotEmpty  @Column(unique = true)
  private String email;
  
  @NotEmpty
  private String password;
  
  @OneToOne
  @JoinColumn( referencedColumnName = "organizationId")
  @JsonIgnoreProperties({"administrator"})
  private Organization organization;
  
  @Enumerated(EnumType.STRING)
  private AdministratorType administratorType;
  
  public enum AdministratorType {
    SystemAdmin,
    OrgAdmin
  }
}
