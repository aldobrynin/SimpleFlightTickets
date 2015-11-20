package com.alexd.simpleflighttickets.Db;

import com.activeandroid.ActiveAndroid;
import com.activeandroid.query.Select;
import com.alexd.simpleflighttickets.Models.FlightTicket;

import java.util.List;

public class DbHelper {
    public static void initializeDatabase() {
        List<FlightTicket> tickets = new Select()
                .from(FlightTicket.class)
                .execute();
        if(tickets != null && tickets.size() > 0)
            return;
        ActiveAndroid.beginTransaction();
        try {
            new FlightTicket("Москва", "Челябинск", 2400.0f).save();
            new FlightTicket("Челябинск", "Москва", 2200.0f).save();
            new FlightTicket("Казань", "Челябинск", 2400.0f).save();
            new FlightTicket("Москва", "Казань", 200.0f).save();
            new FlightTicket("Новосибирск", "Челябинск", 3500).save();
            new FlightTicket("Челябинск", "Екатеринбург", 2400.0f).save();
            new FlightTicket("Екатеринбург", "Самара", 2400.0f).save();
            new FlightTicket("Москва", "Челябинск", 2800.0f).save();
            new FlightTicket("Самара", "Челябинск", 2400.0f).save();
            new FlightTicket("Москва", "Челябинск", 2100.0f).save();
            ActiveAndroid.setTransactionSuccessful();
        }
        finally {
            ActiveAndroid.endTransaction();
        }
    }
}
