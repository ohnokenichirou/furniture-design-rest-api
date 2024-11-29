package com.furniture_design.furniture_design_rest_api.controllers;

import java.net.URI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import com.furniture_design.furniture_design_rest_api.models.FurnitureItem;
import com.furniture_design.furniture_design_rest_api.services.FurnitureService;
import jakarta.validation.Valid;

@RestController
@RequestMapping(path = FurnitureController.BASE_URL)
@CrossOrigin(origins = "http://localhost:3000")
public class FurnitureController {
  public static final String BASE_URL = "/api/v1/furnitures";

  @Autowired
  private FurnitureService _furnituteService;

  @GetMapping(path = "")
  public ResponseEntity<Page<FurnitureItem>> getFurnitureItems(
      @RequestParam(name = "page", defaultValue = "1") int page,
      @RequestParam(name = "limit", defaultValue = "10") int limit) {
    Page<FurnitureItem> furnitureItems = _furnituteService.getFurnitureItems(page, limit);
    return ResponseEntity.ok(furnitureItems);
  }

  @GetMapping(path = "/{id}")
  public ResponseEntity<FurnitureItem> getFurnitureItem(@PathVariable("id") Long id) {
    FurnitureItem found = _furnituteService.getFurnitureItemById(id);
    return ResponseEntity.ok(found);
  }

  @PostMapping(path = "")
  public ResponseEntity<FurnitureItem> createFurnitureItem(
      @Valid @RequestBody FurnitureItem newFurnitureItem) {
    FurnitureItem savedFurnitureItem = _furnituteService.saveFurnitureItem(newFurnitureItem);
    URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
        .buildAndExpand(savedFurnitureItem.getId()).toUri();
    return ResponseEntity.created(location).body(savedFurnitureItem);
  }

  @PutMapping(path = "/{id}")
  public ResponseEntity<?> updateFurnitureItem(@RequestBody FurnitureItem newfurnitureItem,
      @PathVariable("id") Long id) {
    _furnituteService.updateFurnitureItem(id, newfurnitureItem);
    return ResponseEntity.noContent().build();
  }

  @DeleteMapping(path = "/{id}")
  public ResponseEntity<?> removeFurnitureItem(@PathVariable("id") Long id) {
    _furnituteService.removeFurnitureItem(id);
    return ResponseEntity.noContent().build();
  }

}
