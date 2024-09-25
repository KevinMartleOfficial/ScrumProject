package be.vdab.scrumjava202409.artikelen;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Stream;

@RestController
public class ArtikelController {
    private final ArtikelService artikelService;

    private record IdNaamEan(long id, String naam, String ean) {
        IdNaamEan(Artikel artikel) {
            this(artikel.getArtikelId(), artikel.getNaam(), artikel.getEan());
        }
    }

    ArtikelController(ArtikelService artikelService) {
        this.artikelService = artikelService;
    }

    @GetMapping(value = "artikelen", params = "naamBevat")
    Stream<IdNaamEan> findByPartEanNummer(String naamBevat) {
        return artikelService.findByPartEanNummer(naamBevat).stream().map(IdNaamEan::new);
    }
}
