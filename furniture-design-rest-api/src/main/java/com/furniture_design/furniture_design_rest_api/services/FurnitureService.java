package com.furniture_design.furniture_design_rest_api.services;

import java.util.List;
import com.furniture_design.furniture_design_rest_api.models.FurnitureItem;

public interface FurnitureService {
  public FurnitureItem saveFurnitureItem(FurnitureItem furnitureItem);

  public List<FurnitureItem> getFurnitureItems();

  public FurnitureItem getFurnitureItemById(int id);

  public void removeFurnitureItem(int id);

  public FurnitureItem updateFurnitureItem(int id, FurnitureItem furnitureItem);
}
