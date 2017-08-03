package com.jsonpoc;

import com.jsonpoc.component.ValidateComponent;
import com.jsonpoc.dto.ValidationStatus;

public class Client {

    private static String dataFilePath = "D:/js/data.json";
    private static String schemaFilePath = "D:/js/schema.json";
    private static String validationRulesFilePath = "D:/js/validations.json";

    public static void main(String[] args) {
        ValidateComponent validateComponent = new ValidateComponent();
        //Validating schema
        ValidationStatus validationScheamStatus = validateComponent.validateDataWithSchema(schemaFilePath, dataFilePath);
        System.out.println(validationScheamStatus);
        //Validating schema
        ValidationStatus validationDataStatus = validateComponent.validateDataWithValidationRules(schemaFilePath, dataFilePath, validationRulesFilePath);
        System.out.println(validationDataStatus);
    }
}
