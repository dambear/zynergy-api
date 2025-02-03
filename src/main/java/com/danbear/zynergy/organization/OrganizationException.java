package com.danbear.zynergy.organization;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
class OrganizationNotFoundException extends RuntimeException {
  public OrganizationNotFoundException(String message) {
    super(message);
  }
}

@ResponseStatus(HttpStatus.BAD_REQUEST)
class OrganizationUniqueEmailException extends RuntimeException {
  public OrganizationUniqueEmailException(String message) {
    super(message);
  }
}
