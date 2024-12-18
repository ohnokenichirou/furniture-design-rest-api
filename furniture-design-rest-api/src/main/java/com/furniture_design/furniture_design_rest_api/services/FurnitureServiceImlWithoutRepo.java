package com.furniture_design.furniture_design_rest_api.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;
import com.furniture_design.furniture_design_rest_api.errors.NotFoundException;
import com.furniture_design.furniture_design_rest_api.models.FurnitureItem;

@Service
public class FurnitureServiceImlWithoutRepo implements FurnitureService {
  private final AtomicLong _counter = new AtomicLong();
  private final List<FurnitureItem> _furnitureItems = new ArrayList<>() {
    {
      add(new FurnitureItem(_counter.incrementAndGet(), "", "furniture 1", 0, new String[0], 0, 0,
          0, "", ""));
      add(new FurnitureItem(_counter.incrementAndGet(), "", "furniture 2", 0, new String[0], 0, 0,
          0, "", ""));
      add(new FurnitureItem(_counter.incrementAndGet(), "", "furniture 3", 0, new String[0], 0, 0,
          0, "", ""));
    }
  };

  @Override
  public FurnitureItem saveFurnitureItem(FurnitureItem furnitureItem) {
    furnitureItem.setId(_counter.incrementAndGet());
    _furnitureItems.add(furnitureItem);
    return furnitureItem;
  }

  @Override
  public Page<FurnitureItem> getFurnitureItems(int page, int limit) {
    return new PageImpl<>(this._furnitureItems);
  }

  @Override
  public FurnitureItem getFurnitureItemById(Long id) {
    return _getItemById(id);
  }

  @Override
  public void removeFurnitureItem(Long id) {
    FurnitureItem found = _getItemById(id);
    _furnitureItems.remove(found);
  }

  @Override
  public FurnitureItem updateFurnitureItem(Long id, FurnitureItem furnitureItem) {
    FurnitureItem found = _getItemById(id);
    _furnitureItems.remove(found);
    _furnitureItems.add(furnitureItem);
    return furnitureItem;
  }

  private FurnitureItem _getItemById(Long id) {
    Optional<FurnitureItem> found =
        _furnitureItems.stream().filter(item -> item.getId() == id).findAny();
    if (!found.isPresent()) {
      throw new NotFoundException("The furniture item is not available.");
    }
    return found.get();
  }

}
