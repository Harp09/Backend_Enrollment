package pe.edu.vallegrande.vg_transaction_cetpro.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document(collection = "profile")
public class Profile {
    private String id;
    private String name;
    private String modularCode;
    private String dreGre;
    private String managementType;
    private String state;
}
