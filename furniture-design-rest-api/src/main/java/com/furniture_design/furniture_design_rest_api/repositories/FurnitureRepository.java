package com.furniture_design.furniture_design_rest_api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.furniture_design.furniture_design_rest_api.models.FurnitureItem;

@Repository
public interface FurnitureRepository extends JpaRepository<FurnitureItem, Integer> {

}
