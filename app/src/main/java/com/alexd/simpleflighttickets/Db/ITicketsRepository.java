package com.alexd.simpleflighttickets.Db;

import com.alexd.simpleflighttickets.Models.FlightTicket;

import java.util.List;

/**
 * Created by alexd on 2015-11-18.
 */
public interface ITicketsRepository{
    long addTicket(FlightTicket ticket);
    List<FlightTicket> getTicketsByRoute(String cityFrom, String cityTo);
}
