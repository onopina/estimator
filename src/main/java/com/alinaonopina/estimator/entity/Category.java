package com.alinaonopina.estimator.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "category")
public class Category {

    public enum CategoryName {
        FLOORS("Полы", "кв.м"),
        CEILINGS("Потолки", "кв.м"),
        WALLS("Стены", "кв.м"),
        BASEBOARDS("Плинтуса", "м"),
        DOORS("Двери", "шт"),
        HEATING("Точки отопления", "шт"),
        ELECTRICS("Точки электрики", "шт"),
        PLUMBING("Точки сантехники", "шт");

        private final String name;
        private final String unit;

        CategoryName(String name, String unit) {
            this.name = name;
            this.unit = unit;
        }

        public String getName() {
            return name;
        }

        public String getUnit() {
            return unit;
        }
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(name = "name")
    private CategoryName categoryName;

    @Column(name = "unit")
    private String unit;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ApartmentCategoryQuantity> apartmentCategoryQuantities;

    public Category() {
    }

    public Category(CategoryName categoryName) {
        this.categoryName = categoryName;
        this.unit = categoryName.getUnit();
    }

    public String getName() {
        return categoryName.getName(); //название работы из enum
    }

    public String getUnit() {
        return unit;  //название единицы измерения из поля класса
    }

    public Category(CategoryName categoryName, List<ApartmentCategoryQuantity> apartmentCategoryQuantities) {
        this.categoryName = categoryName;
        this.apartmentCategoryQuantities = apartmentCategoryQuantities;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public CategoryName getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(CategoryName categoryName) {
        this.categoryName = categoryName;
        this.unit = categoryName.getUnit(); //обновляем единицу измерения при изменении категории
    }

    public List<ApartmentCategoryQuantity> getApartmentCategoryQuantities() {
        return apartmentCategoryQuantities;
    }

    public void setApartmentCategoryQuantities(List<ApartmentCategoryQuantity> apartmentCategoryQuantities) {
        this.apartmentCategoryQuantities = apartmentCategoryQuantities;
    }
}
