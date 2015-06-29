package com.github.northfox.web.mempro.domain;

import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.DateSerializer;

import lombok.Data;

@Data
public class Member {
  
  private long id;
  private String name;
  private String email;
  private String password;
  private Date createdDate;
  private Date updatedDate;

  @JsonSerialize(using = DateSerializer.class)
  public Date getCreatedDate() {
    return createdDate;
  }

  @JsonSerialize(using = DateSerializer.class)
  public Date getUpdatedDate() {
    return updatedDate;
  }
}
