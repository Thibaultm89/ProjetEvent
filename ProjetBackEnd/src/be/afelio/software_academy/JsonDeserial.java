package be.afelio.software_academy;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import be.afelio.software_academy.beans.Event;

public class JsonDeserial extends StdDeserializer<LocalDateTime>{

	private static final long serialVersionUID = 5149531892383681980L;

	protected JsonDeserial() {
	        super(LocalDateTime.class);
	    }

	@Override
	public LocalDateTime deserialize(JsonParser parser, DeserializationContext context) throws IOException, JsonProcessingException {
		return LocalDateTime.ofInstant(Instant.parse(parser.readValueAs(String.class)), ZoneOffset.of("+02:00"));
	}

}
