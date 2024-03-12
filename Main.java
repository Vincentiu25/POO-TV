import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.node.ArrayNode;

import java.io.File;
import java.io.IOException;

public class Main {
    /** main function*/
    public static void main(String[] args) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        inputClass inputData = objectMapper.readValue(new File(args[0]),
                inputClass.class);

        ArrayNode output = objectMapper.createArrayNode();

        ObjectWriter objectWriter = objectMapper.writerWithDefaultPrettyPrinter();
        objectWriter.writeValue(new File(args[1]), output);

        ReadActions actions = new ReadActions();
        actions.read(output, inputData);

        objectWriter.writeValue(new File("results.out"), output);
    }
}
