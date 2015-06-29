package com.github.northfox.web.mempro.config;

public enum UriConstants {
  MEMBER;

  private static String app = "mempro";

  public String dummyPath() {
    return commonPath("dummy");
  }

  public String createPath() {
    return commonPath("create");
  }

  public String getPath() {
    return commonPath("{id}");
  }

  public String getAllPath() {
    return commonPath("", pluralName(), "%s/%s");
  }

  public String updatePath() {
    return commonPath("update");
  }

  public String deletePath() {
    return commonPath("delete");
  }

  private String pluralName() {
    return name() + "s";
  }

  private String commonPath(String command) {
    return String.format(command, name(), "%s/%s/%s");
  }

  private String commonPath(String command, String service, String format) {
    return String.format(format, app, service, command);
  }
}