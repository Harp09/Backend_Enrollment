package pe.edu.vallegrande.vg_transaction_cetpro.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.vallegrande.vg_transaction_cetpro.model.Enrollment;
import pe.edu.vallegrande.vg_transaction_cetpro.model.Profile;
import pe.edu.vallegrande.vg_transaction_cetpro.model.AcademicPeriod;
import pe.edu.vallegrande.vg_transaction_cetpro.repository.EnrollmentRepository;
import pe.edu.vallegrande.vg_transaction_cetpro.repository.ProfileRepository;
import pe.edu.vallegrande.vg_transaction_cetpro.repository.AcademicPeriodRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class EnrollmentService {

    @Autowired
    private EnrollmentRepository enrollmentRepository;

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private AcademicPeriodRepository academicPeriodRepository;

    public Flux<Enrollment> getByState(char state) {
        return enrollmentRepository.findByState(state);
    }

    public Mono<Enrollment> create(Enrollment enrollment) {
        return Mono.zip(
                        profileRepository.findById(enrollment.getCetproIdCetpro().getId()), // Busca el perfil (CETPRO)
                        academicPeriodRepository.findById(enrollment.getAcademicPeriodIdAcademicPeriod().getId()) // Busca el período académico
                )
                .flatMap(tuple -> {
                    Profile profile = tuple.getT1();
                    AcademicPeriod academicPeriod = tuple.getT2();

                    enrollment.setCetproIdCetpro(profile);
                    enrollment.setAcademicPeriodIdAcademicPeriod(academicPeriod);

                    return enrollmentRepository.save(enrollment)
                            .map(savedEnrollment -> {
                                savedEnrollment.getCetproIdCetpro().setName(profile.getName());
                                savedEnrollment.getAcademicPeriodIdAcademicPeriod().setAcademicPeriod(academicPeriod.getAcademicPeriod());
                                return savedEnrollment;
                            });
                });
    }

    public Mono<Enrollment> update(String id, Enrollment enrollment) {
        return enrollmentRepository.findById(id)
                .switchIfEmpty(Mono.error(new RuntimeException("Enrollment not found with id: " + id)))
                .flatMap(existingEnrollment -> {
                    existingEnrollment.setInstitutionalStaffIdInstitutionalStaff(enrollment.getInstitutionalStaffIdInstitutionalStaff());
                    existingEnrollment.setContactFormIdContactForm(enrollment.getContactFormIdContactForm());
                    existingEnrollment.setEnrollmentDetailIdEnrollmentDetail(enrollment.getEnrollmentDetailIdEnrollmentDetail());
                    existingEnrollment.setState(enrollment.getState());

                    if (enrollment.getCetproIdCetpro() != null && enrollment.getCetproIdCetpro().getId() != null) {
                        return profileRepository.findById(enrollment.getCetproIdCetpro().getId())
                                .switchIfEmpty(Mono.error(new RuntimeException("Profile not found with id: " + enrollment.getCetproIdCetpro().getId())))
                                .flatMap(profile -> {
                                    existingEnrollment.setCetproIdCetpro(profile);
                                    return enrollmentRepository.save(existingEnrollment);
                                });
                    } else {
                        return Mono.error(new RuntimeException("CETPRO ID not provided or invalid."));
                    }
                });
    }

    public Mono<Enrollment> changeState(String id, char state) {
        return enrollmentRepository.findById(id)
                .flatMap(enrollment -> {
                    enrollment.setState(state);
                    return enrollmentRepository.save(enrollment);
                });
    }
}