package be.afelio.software_academy;

import java.io.IOException;
import java.text.DateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

import javax.print.attribute.standard.MediaSize.ISO;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.util.ISO8601DateFormat;
import com.fasterxml.jackson.databind.util.ISO8601Utils;

import be.afelio.software_academy.beans.Event;

public class JsonDeserial extends StdDeserializer<LocalDateTime>{

	private static final long serialVersionUID = 5149531892383681980L;

	protected JsonDeserial() {
	        super(LocalDateTime.class);
	    }

	@Override
	public LocalDateTime deserialize(JsonParser parser, DeserializationContext context) throws IOException, JsonProcessingException {
		//Event event = new Event();
		//return LocalDateTime.parse(parser.readValueAs(String.class), DateTimeFormatter.  );
		return LocalDateTime.ofInstant(Instant.parse(parser.readValueAs(String.class)), ZoneOffset.UTC);

	
	}

}
