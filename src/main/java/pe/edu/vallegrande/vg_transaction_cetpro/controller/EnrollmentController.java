package pe.edu.vallegrande.vg_transaction_cetpro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.vallegrande.vg_transaction_cetpro.model.Enrollment;
import pe.edu.vallegrande.vg_transaction_cetpro.service.EnrollmentService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT})
@RestController
@RequestMapping("/enrollment")
public class EnrollmentController {

    @Autowired
    private EnrollmentService enrollmentService;

    @GetMapping("/list/active")
    public ResponseEntity<Flux<Enrollment>> listActive() {
        return ResponseEntity.ok(enrollmentService.getByState('A'));
    }

    @GetMapping("/list/inactive")
    public ResponseEntity<Flux<Enrollment>> listInactive() {
        return ResponseEntity.ok(enrollmentService.getByState('I'));
    }

    @PostMapping("/create")
    public ResponseEntity<Mono<Enrollment>> create(@RequestBody Enrollment enrollment) {
        return ResponseEntity.ok(enrollmentService.create(enrollment));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Mono<Enrollment>> update(@PathVariable String id, @RequestBody Enrollment enrollment) {
        return ResponseEntity.ok(enrollmentService.update(id, enrollment));
    }

    @PutMapping("/activate/{id}")
    public ResponseEntity<Mono<Enrollment>> activate(@PathVariable String id) {
        return ResponseEntity.ok(enrollmentService.changeState(id, 'A'));
    }

    @PutMapping("/deactivate/{id}")
    public ResponseEntity<Mono<Enrollment>> deactivate(@PathVariable String id) {
        return ResponseEntity.ok(enrollmentService.changeState(id, 'I'));
    }
}