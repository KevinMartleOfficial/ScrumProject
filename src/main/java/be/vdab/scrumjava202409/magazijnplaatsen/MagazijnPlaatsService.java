package be.vdab.scrumjava202409.magazijnplaatsen;

import be.vdab.scrumjava202409.artikelen.Artikel;
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

    List<ArtikelMagazijn> findMagazijnplaatsByArtikelId(long artikelId) {
        List<MagazijnPlaats> magazijnPlaatsen = magazijnPlaatsRepository.findMagazijnplaatsByArtikelId(artikelId);
        return magazijnPlaatsen.stream()
                .map(mp -> new ArtikelMagazijn(mp.getRij() + String.valueOf(mp.getRek()), mp.getAantal()))
                .collect(Collectors.toList());
    }
}
