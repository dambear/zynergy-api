package com.danbear.zynergy.administrator;

import com.danbear.zynergy.common.ResponseObject;
import com.danbear.zynergy.administrator.dto.AdministratorDto;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/administrator")
public class AdministratorController {
  
  private final AdministratorService administratorService;
  
  public AdministratorController(AdministratorService administratorService) {
    this.administratorService = administratorService;
  }
  
  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public ResponseEntity<ResponseObject> getAllOrganizations() {
    List<AdministratorDto> administrators = administratorService.findAllAdministrators();
    
    ResponseObject responseObject = new ResponseObject();
    
    responseObject.setTimestamp(new Date());
    responseObject.setStatus(HttpStatus.OK.value());
    responseObject.setMessage("Administrator Retrieved Successfully.");
    responseObject.setData(administrators);
    responseObject.setPath("/api/administrator");
    
    if (administrators.isEmpty()) {
      responseObject.setMessage("No Administrator Found.");
    }
    
    return new ResponseEntity<>(responseObject, HttpStatus.OK);
  }
  
  @GetMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public ResponseEntity<ResponseObject> getAdministratorById(@PathVariable Long id) {
    AdministratorDto administrator = administratorService.findAdministratorById(id);
    
    ResponseObject responseObject = new ResponseObject();
    
    responseObject.setTimestamp(new Date());
    responseObject.setStatus(HttpStatus.OK.value());
    responseObject.setMessage("Administrator Retrieved Successfully.");
    responseObject.setData(administrator);
    responseObject.setPath("/api/administrator/" + id);
    
    return new ResponseEntity<>(responseObject, HttpStatus.OK);
  }
  
  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public ResponseEntity<ResponseObject> createAdministrator(@Valid @RequestBody AdministratorDto administratorDto) {
    
    AdministratorDto administrator = administratorService.createAdministrator(administratorDto);
    
    ResponseObject responseObject = new ResponseObject();
    
    responseObject.setTimestamp(new Date());
    responseObject.setStatus(HttpStatus.CREATED.value());
    responseObject.setMessage("Administrator Created Successfully.");
    responseObject.setData(administrator);
    responseObject.setPath("/api/administrator");
    
    return new ResponseEntity<>(responseObject, HttpStatus.CREATED);
  }
  
  @PutMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public ResponseEntity<ResponseObject> updateAdministrator(
      @Valid
      @RequestBody AdministratorDto administratorDto,
      @PathVariable("id") Long id)
  {
    AdministratorDto administrator = administratorService.updateAdministrator(administratorDto, id);
    
    ResponseObject responseObject = new ResponseObject();
    
    responseObject.setTimestamp(new Date());
    responseObject.setStatus(HttpStatus.OK.value());
    responseObject.setMessage("Administrator Updated Successfully.");
    responseObject.setData(administrator);
    responseObject.setPath("/api/administrator/" + id);
    
    return new ResponseEntity<>(responseObject, HttpStatus.OK);
  }
  
  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public ResponseEntity<ResponseObject> deleteAdministrator(
      @PathVariable("id") Long id)
  {
    administratorService.deleteAdministrator(id);
    
    ResponseObject responseObject = new ResponseObject();
    
    responseObject.setTimestamp(new Date());
    responseObject.setStatus(HttpStatus.OK.value());
    responseObject.setMessage("Administrator Deleted Successfully.");
    responseObject.setPath("/api/administrator/" + id);
    
    return new ResponseEntity<>(responseObject, HttpStatus.OK);
  }
  
}
