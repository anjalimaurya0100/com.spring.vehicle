package com.heycar.vehicle.model;

import org.hibernate.validator.constraints.Range;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Objects;


@Entity
@Table(name = "vehicles")
@EntityListeners(AuditingEntityListener.class)
public class Vehicle {
    @Id
    @NotBlank
    private String code;

    @NotBlank
    @Column(name = "make", nullable = false)
    private String make;

    @NotBlank
    @Column(name = "model", nullable = false)
    private String model;

    @Column(name = "kw", nullable = false)
    private Integer kw;

    @Range(min = 1999, max = 2099)
    @Column(name = "year", nullable = false)
    private Integer year;

    @NotBlank
    @Column(name = "color", nullable = false)
    private String color;

    @Column(name = "price", nullable = false)
    private Integer price;

    public Vehicle() {

    }

    public Vehicle(@NotBlank String code, @NotBlank String make, @NotBlank String model, Integer kw, @Range(min = 1999, max = 2099) Integer year, @NotBlank String color, Integer price) {
        this.code = code;
        this.make = make;
        this.model = model;
        this.kw = kw;
        this.year = year;
        this.color = color;
        this.price = price;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Integer getKw() {
        return kw;
    }

    public void setKw(Integer kw) {
        this.kw = kw;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vehicle vehicle = (Vehicle) o;
        return code.equals(vehicle.code) &&
                make.equals(vehicle.make) &&
                model.equals(vehicle.model) &&
                kw.equals(vehicle.kw) &&
                year.equals(vehicle.year) &&
                color.equals(vehicle.color) &&
                price.equals(vehicle.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, make, model, kw, year, color, price);
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "code='" + code + '\'' +
                ", make='" + make + '\'' +
                ", model='" + model + '\'' +
                ", kw=" + kw +
                ", year=" + year +
                ", color='" + color + '\'' +
                ", price=" + price +
                '}';
    }
}
