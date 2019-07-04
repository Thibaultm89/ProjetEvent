package be.afelio.software_academy;

import java.io.IOException;
import java.time.LocalDate;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import be.afelio.software_academy.beans.Event;

public class JsonDeserial extends StdDeserializer<LocalDate>{

	private static final long serialVersionUID = 5149531892383681980L;

	protected JsonDeserial() {
	        super(LocalDate.class);
	    }

	@Override
	public LocalDate deserialize(JsonParser parser, DeserializationContext context) throws IOException, JsonProcessingException {
		//Event event = new Event();
		return LocalDate.parse(parser.readValueAs(String.class));
	}

}
