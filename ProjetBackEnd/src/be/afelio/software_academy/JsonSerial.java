package be.afelio.software_academy;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class JsonSerial extends StdSerializer<LocalDate> {

	private static final long serialVersionUID = -7715993065947890827L;

	public JsonSerial() {
	        super(LocalDate.class);
	    }

	@Override
	    public void serialize(LocalDate value, JsonGenerator generator, SerializerProvider provider) throws IOException {
	        generator.writeString(value.format(DateTimeFormatter.ISO_LOCAL_DATE));
	    }
	
	

}
