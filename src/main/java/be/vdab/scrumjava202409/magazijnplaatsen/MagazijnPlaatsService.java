package be.vdab.scrumjava202409.magazijnplaatsen;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class MagazijnPlaatsService {
    private final MagazijnPlaatsRepository magazijnPlaatsRepository;

    public MagazijnPlaatsService(MagazijnPlaatsRepository magazijnPlaatsRepository) {
        this.magazijnPlaatsRepository = magazijnPlaatsRepository;
    }

    @Transactional
    public void haalArtikelUitMagazijnPlaats(int aantal, String plaats){
        String rij = String.valueOf(plaats.charAt(0));
        int rek = Integer.parseInt(plaats.substring(1));
        magazijnPlaatsRepository.haalArtikelUitMagazijnPlaats(aantal, rij, rek);
    }

    List<ArtikelMagazijn> findMagazijnplaatsByArtikelId(long artikelId) {
        List<MagazijnPlaats> magazijnPlaatsen = magazijnPlaatsRepository.findMagazijnplaatsByArtikelId(artikelId);
        return magazijnPlaatsen
                .stream()
                .map(mp -> new ArtikelMagazijn(mp.getRij() + String.valueOf(mp.getRek()), mp.getAantal()))
                .collect(Collectors.toList());
    }


}
