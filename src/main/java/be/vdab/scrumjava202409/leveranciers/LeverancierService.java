package be.vdab.scrumjava202409.leveranciers;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class LeverancierService {
    private final LeverancierRepository leverancierRepository;

    public LeverancierService(LeverancierRepository leverancierRepository) {
        this.leverancierRepository = leverancierRepository;
    }

    public long findLeveranciersIdByLeveranciersStukjeNaam(String stukjeNaam) {
        return leverancierRepository.findLeveranciersIdByLeveranciersStukjeNaam(stukjeNaam);
    }

    public long findLeverancierIdByNaam(String naam){
        return leverancierRepository.findLeverancierIdByNaam(naam);
    }


}
