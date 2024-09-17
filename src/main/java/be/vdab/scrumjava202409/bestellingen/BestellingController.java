package be.vdab.scrumjava202409.bestellingen;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("bestelling")
public class BestellingController {

    private BestellingService bestellingService;

    public BestellingController(BestellingService bestellingService){
        this.bestellingService = bestellingService;
    }

    @GetMapping("tv")
    public List<BestellingTVDTO> eerste5bestellingenTV(){
        return bestellingService.eerste5bestellingenTV();
    }
}
