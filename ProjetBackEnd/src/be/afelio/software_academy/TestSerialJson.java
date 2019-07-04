package be.afelio.software_academy;

import java.time.LocalDate;
import java.time.Month;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import be.afelio.software_academy.beans.Event;



public class TestSerialJson {
	public static void main(String[] args) {
        Event event = new Event();
        event.setId(100);
        event.setName("Twist and Shout");
       // event.setStart(LocalDate.of(1964, Month.FEBRUARY, 3));

        ObjectMapper mapper = new ObjectMapper();
        try {
            String json = mapper.writeValueAsString(event);
            System.out.println("JSON = " + json);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

    }

}
