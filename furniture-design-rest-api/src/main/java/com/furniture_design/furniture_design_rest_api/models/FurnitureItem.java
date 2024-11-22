package com.furniture_design.furniture_design_rest_api.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "furnitures", schema = "business")
public class FurnitureItem {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id", nullable = false)
  private int id;
  @NotBlank(message = "FilePath must not be blank.")
  @Column(name = "filePath", nullable = false)
  private String filePath;
  @NotBlank(message = "Title must not be blank.")
  @Column(name = "title", nullable = false)
  private String title;
  @NotNull(message = "Price must not be blank")
  @Column(name = "price", nullable = false)
  private Integer price;
  @NotNull(message = "Texts must not be blank")
  @NotEmpty(message = "Texts must not be blank")
  @Column(name = "texts", nullable = false)
  private String[] texts;
  @NotNull(message = "Width must not be blank")
  @Column(name = "width", nullable = false)
  private Integer width;
  @NotNull(message = "Depth must not be blank")
  @Column(name = "depth", nullable = false)
  private Integer depth;
  @NotNull(message = "Height must not be blank")
  @Column(name = "height", nullable = false)
  private Integer height;
  @NotBlank(message = "Color must not be blank.")
  @Column(name = "color", nullable = false)
  private String color;
  @NotBlank(message = "Material must not be blank.")
  @Column(name = "material", nullable = false)
  private String material;
}
