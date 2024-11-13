package com.furniture_design.furniture_design_rest_api.controllers;

import java.util.List;
import java.util.ArrayList;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.furniture_design.furniture_design_rest_api.models.FurnitureItem;

@RestController
public class FurnitureController {

  private final List<FurnitureItem> _furnitureItems = new ArrayList<>() {
    {
      add(new FurnitureItem(1, "furniture 1"));
      add(new FurnitureItem(2, "furniture 2"));
      add(new FurnitureItem(3, "furniture 3"));
    }
  };

  @RequestMapping(method = RequestMethod.GET, path = "/furnitures")
  public List<FurnitureItem> getFurnitureItems() {
    return _furnitureItems;
  }

  @RequestMapping(method = RequestMethod.GET, path = "/furnitures/{id}")
  public FurnitureItem getFurnitureItem(@PathVariable int id) {
    FurnitureItem found = _getItemById(id);
    if (found == null) {
      // return 404
    }

    return found;
  }

  @RequestMapping(method = RequestMethod.POST, path = "/furnitures")
  public FurnitureItem createFurnitureItem(@RequestBody FurnitureItem furnitureItem) {
    furnitureItem.setId(4);
    _furnitureItems.add(furnitureItem);
    return furnitureItem;
  }

  @RequestMapping(method = RequestMethod.PUT, path = "/furnitures/{id}")
  public FurnitureItem updateFurnitureItem(@RequestBody FurnitureItem furnitureItem,
      @PathVariable int id) {
    FurnitureItem found = _getItemById(id);
    if (found == null) {
      // return 404
    }

    _furnitureItems.remove(found);
    _furnitureItems.add(furnitureItem);

    return furnitureItem;
  }

  @RequestMapping(method = RequestMethod.DELETE, path = "/furnitures/{id}")
  public void removeFurnitureItem(@PathVariable int id) {
    FurnitureItem found = _getItemById(id);
    if (found == null) {
      // return 404
    }
    _furnitureItems.remove(found);
  }

  private FurnitureItem _getItemById(int id) {
    return _furnitureItems.stream().filter(item -> item.getId() == id).findAny().orElse(null);
  }

}
