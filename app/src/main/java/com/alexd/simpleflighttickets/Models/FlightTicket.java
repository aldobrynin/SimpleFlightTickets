package com.alexd.simpleflighttickets.Models;


import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

@Table(name = "Categories")
public class FlightTicket extends Model {
    @Column(name = "CityFrom")
    private String cityFrom;
    @Column(name = "CityTo")
    private String cityTo;
    @Column(name = "Price")
    private float price;

    public FlightTicket() {
        super();
    }

    public FlightTicket(String cityFrom, String cityTo, float price)
    {
        super();
        this.cityFrom = cityFrom;
        this.cityTo = cityTo;
        this.price = price;
    }

    @Override
    public String toString() {
        return String.format("Ticket(id:%d) from %s to %s. Price %f", getId(),cityFrom, cityTo, price);
    }

    public String getCityTo() {
        return cityTo;
    }

    public void setCityTo(String cityTo) {
        this.cityTo = cityTo;
    }

    public String getCityFrom() {
        return cityFrom;
    }

    public void setCityFrom(String cityFrom) {
        this.cityFrom = cityFrom;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
