package com.github.northfox.web.mempro.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.DateSerializer;

@Entity
@Data
public class Member {
  
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  private String name;
  private String email;
  private String password;
  @Temporal(TemporalType.TIMESTAMP)
  private Date createdDate;
  @Temporal(TemporalType.TIMESTAMP)
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
