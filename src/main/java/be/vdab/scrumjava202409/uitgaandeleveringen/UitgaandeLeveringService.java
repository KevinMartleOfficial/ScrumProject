package be.vdab.scrumjava202409.uitgaandeleveringen;

import be.vdab.scrumjava202409.bestellingen.Bestelling;
import be.vdab.scrumjava202409.bestellingen.BestellingRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UitgaandeLeveringService {
    private UitgaandeLeveringRepository uitgaandeLeveringRepository;
    private BestellingRepository bestellingRepository;

    public UitgaandeLeveringService(UitgaandeLeveringRepository uitgaandeLeveringRepository, BestellingRepository bestellingRepository){
        this.uitgaandeLeveringRepository = uitgaandeLeveringRepository;
        this.bestellingRepository = bestellingRepository;
    }

    public long addUitgaandeLevering(long bestelId){

        return uitgaandeLeveringRepository.addUitgaandeLevering(bestellingRepository.findById(bestelId));
    }
}
