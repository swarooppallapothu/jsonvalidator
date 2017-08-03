package com.jsonpoc;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.networknt.schema.JsonSchema;
import com.networknt.schema.JsonSchemaFactory;
import com.networknt.schema.ValidationMessage;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.Set;

public class Client {

    private String dataFilePath = "D:/js/data.json";
    private String schemaFilePath = "D:/js/schema.json";
    private String validationRulesFilePath = "D:/js/validations.json";

    public static void main(String[] args) {
        try {
            Client executor = new Client();
            JsonSchema schema = executor.getJsonSchemaFromFile(executor.schemaFilePath);
            JsonNode node = executor.getJsonNodeFromFile(executor.dataFilePath);
            Set<ValidationMessage> errors = schema.validate(node);
            if (errors.isEmpty()) {
                System.out.println("Successful!");
            } else {
                System.out.println("Errors (" + errors.size() + ")");
                for (ValidationMessage error : errors) {
                    System.out.println(error);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    protected JsonNode getJsonNodeFromStringContent(String content) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode node = mapper.readTree(content);
        return node;
    }

    protected JsonNode getJsonNodeFromUrl(String url) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode node = mapper.readTree(new URL(url));
        return node;
    }

    protected JsonNode getJsonNodeFromFile(String path) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode node = mapper.readTree(new FileInputStream(new File(path)));
        return node;
    }

    protected JsonSchema getJsonSchemaFromStringContent(String schemaContent) throws Exception {
        JsonSchemaFactory factory = new JsonSchemaFactory();
        JsonSchema schema = factory.getSchema(schemaContent);
        return schema;
    }

    protected JsonSchema getJsonSchemaFromUrl(String url) throws Exception {
        JsonSchemaFactory factory = new JsonSchemaFactory();
        JsonSchema schema = factory.getSchema(new URL(url));
        return schema;
    }

    protected JsonSchema getJsonSchemaFromFile(String path) throws Exception {
        JsonSchemaFactory factory = new JsonSchemaFactory();
        JsonSchema schema = factory.getSchema(new FileInputStream(new File(path)));
        return schema;
    }

}
