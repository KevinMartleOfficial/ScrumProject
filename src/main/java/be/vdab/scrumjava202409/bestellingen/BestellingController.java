package be.vdab.scrumjava202409.bestellingen;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Stream;

@RequestMapping("bestellingen")
@RestController
class BestellingController {
    private final BestellingService bestellingService;
    public BestellingController(BestellingService bestellingService) {
        this.bestellingService = bestellingService;
    }

    private record ArtikelRekPlaatsAantal (String artikelNaam, int aantal) {
    }


}
