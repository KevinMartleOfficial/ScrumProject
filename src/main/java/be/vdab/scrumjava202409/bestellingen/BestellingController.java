package be.vdab.scrumjava202409.bestellingen;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Stream;
import java.util.List;

@RequestMapping("bestellingen")
@RestController
class BestellingController {
    private final BestellingService bestellingService;
    public BestellingController(BestellingService bestellingService) {
        this.bestellingService = bestellingService;
    }

    private record ArtikelRekPlaatsAantal (String artikelNaam, int aantal) {
    }

    @GetMapping("bestelling/tv")
    public List<BestellingTVDTO> eerste5bestellingenTV() {
        return bestellingService.eerste5bestellingenTV();
    }
    @GetMapping("bestellingen/aantal")
    long findAantalBestellingen() {
        return bestellingService.findAantalBestellingen();
    }


}
