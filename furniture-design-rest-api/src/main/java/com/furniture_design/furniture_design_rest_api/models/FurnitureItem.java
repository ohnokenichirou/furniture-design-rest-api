package com.furniture_design.furniture_design_rest_api.models;

public class FurnitureItem {
  private int id;
  private String title;

  public FurnitureItem() {}

  public FurnitureItem(int id, String title) {
    this.id = id;
    this.title = title;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }
}
