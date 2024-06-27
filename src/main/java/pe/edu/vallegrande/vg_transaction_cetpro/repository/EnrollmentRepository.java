package pe.edu.vallegrande.vg_transaction_cetpro.repository;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import pe.edu.vallegrande.vg_transaction_cetpro.model.Enrollment;
import reactor.core.publisher.Flux;

import java.util.List;

public interface EnrollmentRepository extends ReactiveMongoRepository<Enrollment, String> {

    // Método para buscar matrículas activas por estado, período académico y Cetpro
    @Query(value = "{ 'state': ?0 }", fields = "{ 'cetproIdCetpro': 1, 'academicPeriodIdAcademicPeriod': 1, 'state': 1 }")
    Flux<Enrollment> findByState(char state);

}