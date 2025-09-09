package stream;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.async.ByteArrayFeeder;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class ParseStream {

    public static void main(String[] args) throws IOException, InterruptedException {
        byte[] jsonBytes1 = """
                { "name": "Alice", "a
                """.trim().getBytes(StandardCharsets.UTF_8);

        byte[] jsonBytes2 ="""
                ge": 20 }
                """.trim().getBytes(StandardCharsets.UTF_8);

        JsonParser parser = new ObjectMapper().getFactory()
                .createNonBlockingByteArrayParser();
        ByteArrayFeeder feeder = (ByteArrayFeeder) parser.getNonBlockingInputFeeder();
        feeder.feedInput(jsonBytes1, 0, jsonBytes1.length);

        parse(parser);

        Thread.sleep(2000);

        feeder.feedInput(jsonBytes2, 0, jsonBytes2.length);

        parse(parser);

        feeder.endOfInput();
    }

    private static void parse(JsonParser parser) throws IOException {
        while (parser.nextToken() != JsonToken.NOT_AVAILABLE) {
            JsonToken token = parser.getCurrentToken();

            if (token == JsonToken.FIELD_NAME) {
                String fieldName = parser.getText();
                token = parser.nextToken();

                if (token == JsonToken.VALUE_STRING
                        || token == JsonToken.VALUE_NUMBER_INT) {

                    System.out.println(fieldName + ": " + parser.getText());
                }
            }
        }
    }
}
