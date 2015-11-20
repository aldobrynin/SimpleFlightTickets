package com.alexd.simpleflighttickets;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.alexd.simpleflighttickets.Db.TicketsRepository;
import com.alexd.simpleflighttickets.Models.FlightTicket;

import java.util.List;

public class FlightTicketActivity extends AppCompatActivity {
    private TextView infoTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flight_ticket);

        String from = getIntent().getExtras().getString("from");
        String to = getIntent().getExtras().getString("to");

        infoTextView = (TextView)findViewById(R.id.resultText);

        List<FlightTicket> tickets = new TicketsRepository().getTicketsByRoute(from, to);

        showTicketsInfo(tickets);
    }

    private void showTicketsInfo(List<FlightTicket> tickets) {
        StringBuilder builder = new StringBuilder();

        for (FlightTicket ticket : tickets)
            builder.append(String.format("Id: %d, From: %s, To: %s. Price: %f\n",
                    ticket.getId(), ticket.getCityFrom(), ticket.getCityTo(), ticket.getPrice()));
        builder.append(String.format("Found %d tickets!", tickets.size()));

        infoTextView.setText(builder.toString());
    }
}
