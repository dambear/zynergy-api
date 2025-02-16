package com.danbear.zynergy.organization;

import com.danbear.zynergy.common.ResponseObject;
import com.danbear.zynergy.organization.dto.OrganizationDto;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/organization")
public class OrganizationController {
  
  private final OrganizationService organizationService;
  
  public OrganizationController(OrganizationService organizationService) {
    this.organizationService = organizationService;
  }
  
  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public ResponseEntity<ResponseObject> getAllOrganizations() {
    List<OrganizationDto> organizations = organizationService.findAllOrganizations();
    
    ResponseObject responseObject = new ResponseObject();
    
    responseObject.setTimestamp(new Date());
    responseObject.setStatus(HttpStatus.OK.value());
    responseObject.setMessage("Organization Retrieved Successfully.");
    responseObject.setData(organizations);
    responseObject.setPath("/api/organization");
    
    if (organizations.isEmpty()) {
      responseObject.setMessage("No Organization Found.");
    }
    
    return new ResponseEntity<>(responseObject, HttpStatus.OK);
  }
  
  @GetMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public ResponseEntity<ResponseObject> getOrganizationById(@PathVariable Long id) {
    OrganizationDto organization = organizationService.findOrganizationById(id);
    
    ResponseObject responseObject = new ResponseObject();
    
    responseObject.setTimestamp(new Date());
    responseObject.setStatus(HttpStatus.OK.value());
    responseObject.setMessage("Organization Retrieved Successfully.");
    responseObject.setData(organization);
    responseObject.setPath("/api/organization/" + id);
    
    return new ResponseEntity<>(responseObject, HttpStatus.OK);
  }
  
  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public ResponseEntity<ResponseObject> createOrganization(@Valid @RequestBody OrganizationDto organizationDto) {
    
    OrganizationDto organization = organizationService.createOrganization(organizationDto);
    
    ResponseObject responseObject = new ResponseObject();
    
    responseObject.setTimestamp(new Date());
    responseObject.setStatus(HttpStatus.CREATED.value());
    responseObject.setMessage("Organization Created Successfully.");
    responseObject.setData(organization);
    responseObject.setPath("/api/organization");
    
    return new ResponseEntity<>(responseObject, HttpStatus.CREATED);
  }
  
  @PutMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public ResponseEntity<ResponseObject> updateOrganization(
      @Valid
      @RequestBody OrganizationDto organizationDto,
      @PathVariable("id") Long id)
  {
    OrganizationDto organization = organizationService.updateOrganization(organizationDto, id);
    
    ResponseObject responseObject = new ResponseObject();
    
    responseObject.setTimestamp(new Date());
    responseObject.setStatus(HttpStatus.OK.value());
    responseObject.setMessage("Organization Updated Successfully.");
    responseObject.setData(organization);
    responseObject.setPath("/api/organization/" + id);
    
    return new ResponseEntity<>(responseObject, HttpStatus.OK);
  }
  
  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public ResponseEntity<ResponseObject> deleteOrganization(
      @PathVariable("id") Long id)
  {
    organizationService.deleteOrganization(id);
    
    ResponseObject responseObject = new ResponseObject();
    
    responseObject.setTimestamp(new Date());
    responseObject.setStatus(HttpStatus.OK.value());
    responseObject.setMessage("Organization Deleted Successfully.");
    responseObject.setPath("/api/organization/" + id);
    
    return new ResponseEntity<>(responseObject, HttpStatus.OK);
  }
  
}
