package com.danbear.zynergy.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.Date;

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ResponseObject {
  private Date timestamp;
  private Integer status;
  private String message;
  private Object data;
  private String path;
}
