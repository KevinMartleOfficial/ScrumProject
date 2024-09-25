package be.vdab.scrumjava202409.inkomendeLeveringen;

import be.vdab.scrumjava202409.leveranciers.LeverancierRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class InkomendeLeveringService {
    private final InkomendeLeveringRepository inkomendeLeveringRepository;
    private final LeverancierRepository leverancierRepository;

    public InkomendeLeveringService(InkomendeLeveringRepository inkomendeLeveringRepository,
                                    LeverancierRepository leverancierRepository)
    {
        this.inkomendeLeveringRepository = inkomendeLeveringRepository;
        this.leverancierRepository = leverancierRepository;
    }

    @Transactional
    long create(InkomendeLeveringMetNaam inkomendeLeveringMetNaam) {
        var leveranciersId = leverancierRepository.findLeverancierIdByNaam(inkomendeLeveringMetNaam.naam());
        var inkomendeLevering = new InkomendeLevering(0, leveranciersId,
                inkomendeLeveringMetNaam.leveringsbonNummer(), inkomendeLeveringMetNaam.leveringsbondatum());
        return inkomendeLeveringRepository.create(inkomendeLevering);
    }
}
