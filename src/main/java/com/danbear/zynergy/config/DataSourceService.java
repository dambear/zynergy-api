//package com.danbear.zynergy.config;
//
//import com.danbear.zynergy.organization.Organization;
//import com.danbear.zynergy.organization.OrganizationRepository;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//@Service
//public class DataSourceService {
//
//  private DataSourceRouter dataSourceRouter;
//  private OrganizationRepository organizationRepository;
//
//  public DataSourceService(DataSourceRouter dataSourceRouter, OrganizationRepository organizationRepository) {
//    this.dataSourceRouter = dataSourceRouter;
//    this.organizationRepository = organizationRepository;
//  }
//
//  /**
//   * Switch to the data source associated with the given organization ID.
//   *
//   * @param orgId The ID of the organization.
//   */
//  @Transactional(readOnly = true)
//  public void switchDataSource(Long orgId) {
//    // Fetch the organization from the default data source
//    Organization organization = organizationRepository.findById(orgId)
//        .orElseThrow(() -> new RuntimeException(
//            "No organization found with organization_id: " + orgId
//                + ". Please verify the organization_id and try again."
//        ));
//
//    // Extract the database URL
//    String orgDatabaseUrl = organization.getOrgDatabaseUrl();
//
//    // Validate the database URL
//    if (orgDatabaseUrl == null || orgDatabaseUrl.isEmpty()) {
//      throw new IllegalArgumentException("Organization does not have a valid database URL.");
//    }
//
//    // Add the new data source dynamically
//    String dataSourceKey = "org_" + orgId;
//
//    dataSourceRouter.addDataSource(dataSourceKey, orgDatabaseUrl);
//
//    // Switch to the new data source
//    dataSourceRouter.setCurrentDataSource(dataSourceKey);  // Ensure the key is set
//  }
//
//  /**
//   * Revert to the default data source.
//   */
//  public void revertToDefaultDataSource() {
//    dataSourceRouter.clearCurrentDataSource();
//  }
//}