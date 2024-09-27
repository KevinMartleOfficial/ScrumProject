package be.vdab.scrumjava202409.leveranciers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class LeverancierController {
    private final LeverancierService leverancierService;

    public LeverancierController(LeverancierService leverancierService) {
        this.leverancierService = leverancierService;
    }

    @GetMapping( value = "leveranciers", params = "stukjeNaam")
    public long findLeveranciersIdByLeveranciersStukjeNaam(String stukjeNaam){
        return leverancierService.findLeveranciersIdByLeveranciersStukjeNaam(stukjeNaam);
    }

    @GetMapping(value = "leveranciers", params = "naam")
    public long findLeverancierIdByNaam(String naam){
        return leverancierService.findLeverancierIdByNaam(naam);
    }

    @GetMapping ("leveranciers")
    public List<String> findLeverancierNaam(){
        return leverancierService.findLeverancierNaam();
    }
}
