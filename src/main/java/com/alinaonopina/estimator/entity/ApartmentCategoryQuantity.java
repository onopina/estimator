package com.alinaonopina.estimator.entity;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Comparator;

@Entity
@Table(name = "apartment_category_quantity")
public class ApartmentCategoryQuantity implements Comparable<ApartmentCategoryQuantity> {

    @EmbeddedId
    private ApartmentCategoryQuantityId id;

    @ManyToOne
    @MapsId("apartmentId")
    @JoinColumn(name = "apartment_id")
    private Apartment apartment;

    @ManyToOne
    @MapsId("categoryId")
    @JoinColumn(name = "category_id")
    private Category category;

    @Column(name = "quantity")
    @NotNull(message = "Пожалуйста, заполните поле")
    @Min(value = 1, message = "Количество должно быть больше 0")
    private Integer quantity;


    public ApartmentCategoryQuantity() {
        this.id = new ApartmentCategoryQuantityId();
    }


    public ApartmentCategoryQuantity(Apartment apartment, Category category, Integer quantity) {
        this.apartment = apartment;
        this.category = category;
        this.quantity = quantity;
    }

    public ApartmentCategoryQuantityId getId() {
        return id;
    }

    public void setId(){    //для корректного отрабатывания session.saveOrUpdate()
        getId().setApartmentId(this.apartment.getId());
        getId().setCategoryId(this.category.getId());
    }

    public Apartment getApartment() {
        return apartment;
    }

    public void setApartment(Apartment apartment) {
        this.apartment = apartment;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public int compareTo(ApartmentCategoryQuantity other) {
        return Comparator.comparing((ApartmentCategoryQuantity acq) -> acq.getApartment().getId())
                .thenComparing(acq -> acq.getCategory().getId())
                .compare(this, other);
    }
}
