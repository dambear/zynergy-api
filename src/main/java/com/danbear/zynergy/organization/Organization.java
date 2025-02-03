package com.danbear.zynergy.organization;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "organization")
public class Organization {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long orgId;
  
  @NotEmpty @Column(unique = true)
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
  
  // Subscription Information
  @Enumerated(EnumType.STRING)
  private SubscriptionStatus subscriptionStatus;
  
  
  // Enum for Subscription Status
  public enum SubscriptionStatus {
    Active,
    Inactive,
    Pending,
    Canceled,
    Trial,
    Expired,
    Suspended,
  }
  
}
