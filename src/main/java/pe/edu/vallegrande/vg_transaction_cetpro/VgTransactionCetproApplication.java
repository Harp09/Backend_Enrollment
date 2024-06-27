package pe.edu.vallegrande.vg_transaction_cetpro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

@SpringBootApplication(scanBasePackages = "pe.edu.vallegrande.vg_transaction_cetpro")
@EnableReactiveMongoRepositories(basePackages = "pe.edu.vallegrande.vg_transaction_cetpro.repository")
public class VgTransactionCetproApplication {


    public static void main(String[] args) {
        SpringApplication.run(VgTransactionCetproApplication.class,args); }
}