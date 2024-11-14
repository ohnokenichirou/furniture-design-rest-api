package com.furniture_design.furniture_design_rest_api.controllers;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import com.furniture_design.furniture_design_rest_api.models.FurnitureItem;

@RestController
@RequestMapping(path = FurnitureController.BASE_URL)
public class FurnitureController {
  public static final String BASE_URL = "/api/v1/furnitures";
  private final AtomicInteger _counter = new AtomicInteger();
  private final List<FurnitureItem> _furnitureItems = new ArrayList<>() {
    {
      add(new FurnitureItem(_counter.incrementAndGet(), "furniture 1"));
      add(new FurnitureItem(_counter.incrementAndGet(), "furniture 2"));
      add(new FurnitureItem(_counter.incrementAndGet(), "furniture 3"));
    }
  };

  @GetMapping(path = "")
  public ResponseEntity<List<FurnitureItem>> getFurnitureItems() {
    return ResponseEntity.ok(_furnitureItems);
  }

  @GetMapping(path = "/{id}")
  public ResponseEntity<FurnitureItem> getFurnitureItem(@PathVariable int id) {
    FurnitureItem found = _getItemById(id);
    return ResponseEntity.ok(found);
  }

  @PostMapping(path = "")
  public ResponseEntity<FurnitureItem> createFurnitureItem(
      @RequestBody FurnitureItem newFurnitureItem) {
    if (Objects.isNull(newFurnitureItem.getTitle())) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Title must not be null.");
    }
    newFurnitureItem.setId(_counter.incrementAndGet());
    _furnitureItems.add(newFurnitureItem);
    URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
        .buildAndExpand(newFurnitureItem.getId()).toUri();
    return ResponseEntity.created(location).body(newFurnitureItem);
  }

  @PutMapping(path = "/{id}")
  public ResponseEntity<?> updateFurnitureItem(@RequestBody FurnitureItem furnitureItem,
      @PathVariable int id) {
    FurnitureItem found = _getItemById(id);
    _furnitureItems.remove(found);
    _furnitureItems.add(furnitureItem);
    return ResponseEntity.noContent().build();
  }

  @DeleteMapping(path = "/{id}")
  public ResponseEntity<?> removeFurnitureItem(@PathVariable int id) {
    FurnitureItem found = _getItemById(id);
    _furnitureItems.remove(found);
    return ResponseEntity.noContent().build();
  }

  private FurnitureItem _getItemById(int id) {
    Optional<FurnitureItem> found =
        _furnitureItems.stream().filter(item -> item.getId() == id).findAny();
    if (!found.isPresent()) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not Found");
    }
    return found.get();
  }

}
