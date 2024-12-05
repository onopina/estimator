package com.alinaonopina.estimator.entity;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

@Entity
@Table(name = "cost")
public class Cost implements Comparable<Cost>{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "work_title")
    @NotEmpty(message = "Пожалуйста, заполните поле")
    private String workTitle;

    @Column(name = "cost_value")
    @NotNull(message = "Пожалуйста, заполните поле")
    @Min(value = 1, message = "Значение цены должно быть больше 0")
    private BigDecimal costValue;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @Column(name = "cost_date")
    private LocalDateTime costDate;

    @Column(name = "is_actual")
    private boolean isActual;

    @ManyToMany(mappedBy = "costs", cascade = {CascadeType.ALL})
    private List<Apartment> apartments;


    public Cost() {
        this.costDate = LocalDateTime.now();
    }

    public Cost(String workTitle, BigDecimal costValue, Category category) {
        this.workTitle = workTitle;
        this.costValue = costValue;
        this.costDate = LocalDateTime.now();
        this.category = category;
    }

    public Cost(String workTitle, BigDecimal costValue, Category category, List<Apartment> apartments) {
        this.workTitle = workTitle;
        this.costValue = costValue;
        this.costDate = LocalDateTime.now();
        this.category = category;
        this.apartments = apartments;
    }

    public boolean isActual() {
        return isActual;
    }

    public void setActual(boolean actual) {
        isActual = actual;
    }

    public Cost(String workTitle) {
        this.workTitle = workTitle;
        this.costDate = LocalDateTime.now();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getWorkTitle() {
        return workTitle;
    }

    public void setWorkTitle(String workTitle) {
        this.workTitle = workTitle;
    }

    public BigDecimal getCostValue() {
        return costValue;
    }

    public void setCostValue(BigDecimal costValue) {
        this.costValue = costValue;
    }

    public LocalDateTime getCostDate() {
        return costDate;
    }

    public void setCostDate(LocalDateTime costDate) {
        this.costDate = costDate;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<Apartment> getApartments() {
        return apartments;
    }

    public void setApartments(List<Apartment> apartments) {
        this.apartments = apartments;
    }

    @Override
    public int compareTo(Cost other) {
        return Comparator.comparing((Cost cost) -> cost.getCategory().getName())
                .thenComparing(Cost::getId)
                .compare(this, other);
    }
}

