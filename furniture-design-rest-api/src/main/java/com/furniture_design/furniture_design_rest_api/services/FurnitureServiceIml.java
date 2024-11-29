package com.furniture_design.furniture_design_rest_api.services;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.furniture_design.furniture_design_rest_api.errors.NotFoundException;
import com.furniture_design.furniture_design_rest_api.models.FurnitureItem;
import com.furniture_design.furniture_design_rest_api.repositories.FurnitureRepository;

@Service
@Primary
public class FurnitureServiceIml implements FurnitureService {

  @Autowired
  private FurnitureRepository _furnitureRepository;

  @Override
  public FurnitureItem getFurnitureItemById(Long id) {
    return _findFunitureItemById(id);
  }

  @Override
  public Page<FurnitureItem> getFurnitureItems(int page, int limit) {
    Pageable pageable = PageRequest.of(page - 1, limit, Sort.by("id"));
    return _furnitureRepository.findAll(pageable);
  }

  @Override
  public void removeFurnitureItem(Long id) {
    _furnitureRepository.deleteById(id);
  }

  @Override
  public FurnitureItem saveFurnitureItem(FurnitureItem furnitureItem) {
    return _furnitureRepository.save(furnitureItem);
  }

  @Override
  public FurnitureItem updateFurnitureItem(Long id, FurnitureItem furnitureItem) {
    return _furnitureRepository.save(furnitureItem);
  }

  private FurnitureItem _findFunitureItemById(Long id) throws NotFoundException {
    Optional<FurnitureItem> found = _furnitureRepository.findById(id);
    if (!found.isPresent()) {
      throw new NotFoundException("The furniture item is not available.");
    }
    return found.get();
  }

}
