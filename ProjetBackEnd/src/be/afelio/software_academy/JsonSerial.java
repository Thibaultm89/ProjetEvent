package be.afelio.software_academy;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;


public class JsonSerial extends StdSerializer<LocalDateTime> {

	private static final long serialVersionUID = -7715993065947890827L;

	public JsonSerial() {
		super(LocalDateTime.class);
	}

	@Override
	public void serialize(LocalDateTime value, JsonGenerator generator, SerializerProvider provider)
			throws IOException {
		generator.writeString(value.toInstant(ZoneOffset.of("+02:00")).toString());
	}
}