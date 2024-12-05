package com.alinaonopina.estimator.entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "apartment")
public class Apartment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "address")
    @NotEmpty(message = "Пожалуйста, заполните поле")
    private String address;

    @Column(name = "area")
    @NotNull(message = "Пожалуйста, заполните поле")
    @Min(value = 1, message = "Площадь должна быть больше 0")
    private Integer area;

    @Column(name = "start_date")
    @NotNull(message = "Пожалуйста, заполните поле")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startDate;

    @ManyToMany(cascade = CascadeType.ALL,
            fetch = FetchType.EAGER)
    @JoinTable(
            name = "apartment_cost",
            joinColumns = @JoinColumn(name = "apartment_id"),
            inverseJoinColumns = @JoinColumn(name = "cost_id")
    )
    private List<Cost> costs;

    @OneToMany(mappedBy = "apartment", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ApartmentCategoryQuantity> apartmentCategoryQuantities;

    public Apartment() {
    }

//    public Apartment(String address) {
//        this.address = address;
//    }

    public Apartment(String address, Integer area, Date startDate, List<ApartmentCategoryQuantity> apartmentCategoryQuantities) {
        this.address = address;
        this.area = area;
        this.startDate = startDate;
        this.apartmentCategoryQuantities = apartmentCategoryQuantities;
    }

    public Apartment(String address, Integer area, Date startDate, List<Cost> costs, List<ApartmentCategoryQuantity> apartmentCategoryQuantities) {
        this.address = address;
        this.area = area;
        this.startDate = startDate;
        this.costs = costs;
        this.apartmentCategoryQuantities = apartmentCategoryQuantities;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public Integer getArea() {
        return area;
    }

    public void setArea(Integer area) {
        this.area = area;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public List<Cost> getCosts() {
        return costs;
    }

    public void setCosts(List<Cost> costs) {
        this.costs = costs;
    }

    public List<ApartmentCategoryQuantity> getApartmentCategoryQuantities() {
        return apartmentCategoryQuantities;
    }

    public void setApartmentCategoryQuantities(List<ApartmentCategoryQuantity> apartmentCategoryQuantities) {
        this.apartmentCategoryQuantities = apartmentCategoryQuantities;
    }
}
