package be.vdab.scrumjava202409.artikelen;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class ArtikelService {
    private final ArtikelRepository artikelRepository;
    public ArtikelService(ArtikelRepository artikelRepository) {
        this.artikelRepository = artikelRepository;
    }

    public Artikel getArtikelById(long artikelId) {
        return artikelRepository.getArtikelById(artikelId);
    }

    public List<Artikel> findByPartEanNummer(String eanNummer) {
        return artikelRepository.findByPartEanNummer(eanNummer);
    }
}
