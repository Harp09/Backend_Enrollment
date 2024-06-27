package pe.edu.vallegrande.vg_transaction_cetpro.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import pe.edu.vallegrande.vg_transaction_cetpro.model.Profile;

public interface ProfileRepository extends ReactiveMongoRepository<Profile, String> {
}
