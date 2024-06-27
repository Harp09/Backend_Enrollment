package pe.edu.vallegrande.vg_transaction_cetpro.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document(collection = "enrollment")
public class Enrollment {
    @Id
    private String id;

    // Incluir directamente el documento relacionado
    private Profile cetproIdCetpro;

    private Integer institutionalStaffIdInstitutionalStaff;
    private Integer contactFormIdContactForm;
    private Integer enrollmentDetailIdEnrollmentDetail;

    // Incluir directamente el documento relacionado
    private AcademicPeriod academicPeriodIdAcademicPeriod;
    private char state;
}
