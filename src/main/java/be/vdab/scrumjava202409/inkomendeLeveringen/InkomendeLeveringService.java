package be.vdab.scrumjava202409.inkomendeLeveringen;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class InkomendeLeveringService {
    private final InkomendeLeveringRepository inkomendeLeveringRepository;

    public InkomendeLeveringService(InkomendeLeveringRepository inkomendeLeveringRepository) {
        this.inkomendeLeveringRepository = inkomendeLeveringRepository;
    }

    long create(InkomendeLeveringMetNaam inkomendeLeveringMetNaam) {
        var leveranciersnaam = findbyId
        var inkomendeLevering =

    }
}
