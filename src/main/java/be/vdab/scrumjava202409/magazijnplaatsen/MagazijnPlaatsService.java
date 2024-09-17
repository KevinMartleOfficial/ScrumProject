package be.vdab.scrumjava202409.magazijnplaatsen;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MagazijnPlaatsService {
    private MagazijnPlaatsRepository magazijnPlaatsRepository;

    public MagazijnPlaatsService(MagazijnPlaatsRepository magazijnPlaatsRepository){
        this.magazijnPlaatsRepository = magazijnPlaatsRepository;
    }

    public void haalArtikelUitMagazijnPlaats(int aantal, String plaats){
        String rij = String.valueOf(plaats.charAt(0));
        int rek = Integer.parseInt(plaats.substring(1));
        magazijnPlaatsRepository.haalArtikelUitMagazijnPlaats(aantal, rij, rek);
    }


}
