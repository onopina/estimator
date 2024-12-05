package com.alinaonopina.estimator.entity;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ApartmentCategoryQuantityId implements Serializable {

    private Integer apartmentId;

    private Integer categoryId;

    public ApartmentCategoryQuantityId() {
    }

    public ApartmentCategoryQuantityId(Integer apartmentId) {
        this.apartmentId = apartmentId;
    }

    public ApartmentCategoryQuantityId(Integer apartmentId, Integer categoryId) {
        this.apartmentId = apartmentId;
        this.categoryId = categoryId;
    }

    public Integer getApartmentId() {
        return apartmentId;
    }

    public void setApartmentId(Integer apartmentId) {
        this.apartmentId = apartmentId;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ApartmentCategoryQuantityId that = (ApartmentCategoryQuantityId) o;
        return Objects.equals(apartmentId, that.apartmentId) && Objects.equals(categoryId, that.categoryId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(apartmentId, categoryId);
    }
}
