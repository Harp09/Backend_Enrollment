package pe.edu.vallegrande.vg_transaction_cetpro.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document(collection = "academic_period")
public class AcademicPeriod {
    @Id
    private String id;

    private String academicPeriod;
    private String startDate;
    private String endDate;
    private String shift;
    private String cluster;
    private String state; // Active or Inactive
}
