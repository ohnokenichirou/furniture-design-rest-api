package com.furniture_design.furniture_design_rest_api.services;

import org.springframework.data.domain.Page;
import com.furniture_design.furniture_design_rest_api.models.FurnitureItem;

public interface FurnitureService {
  public FurnitureItem saveFurnitureItem(FurnitureItem furnitureItem);

  public Page<FurnitureItem> getFurnitureItems(int page, int limit);

  public FurnitureItem getFurnitureItemById(Long id);

  public void removeFurnitureItem(Long id);

  public FurnitureItem updateFurnitureItem(Long id, FurnitureItem furnitureItem);
}
