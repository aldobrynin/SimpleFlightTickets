package com.alexd.simpleflighttickets.Db;

import com.activeandroid.query.Select;
import com.alexd.simpleflighttickets.Models.FlightTicket;

import java.util.List;

/**
 * Created by alexd on 2015-11-18.
 */
public class TicketsRepository implements ITicketsRepository {
    @Override
    public long addTicket(FlightTicket ticket) {
        return ticket.save();
    }

    @Override
    public List<FlightTicket> getTicketsByRoute(String cityFrom, String cityTo) {
        return new Select()
                .from(FlightTicket.class)
                .where("CityFrom = ?", cityFrom)
                .where("CityTo = ?", cityTo)
                .orderBy("Price DESC")
                .execute();
    }
}
