package com.github.northfox.web.mempro.config;

public class MemberUriConstants {
  private static final String MEMBER = "/member";
  public static final String DUMMY_PATH = MEMBER + "/dummy";
  public static final String CREATE_PATH = MEMBER + "/create";
  public static final String GET_PATH = MEMBER + "/{id}";
  public static final String GET_ALL_PATH = MEMBER + "s";
  public static final String UPDATE_PATH = MEMBER + "/{id}/update";
  public static final String DELETE_PATH = MEMBER + "/{id}/delete";
}
