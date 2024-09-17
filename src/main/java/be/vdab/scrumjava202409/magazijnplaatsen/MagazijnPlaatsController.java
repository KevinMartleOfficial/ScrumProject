package be.vdab.scrumjava202409.magazijnplaatsen;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("magazijnplaatsen")
public class MagazijnPlaatsController {
    private final MagazijnPlaatsService magazijnPlaatsService;

    public MagazijnPlaatsController(MagazijnPlaatsService magazijnPlaatsService) {
        this.magazijnPlaatsService = magazijnPlaatsService;
    }

    @GetMapping("{id}")
    List<MagazijnPlaats> findMagazijnplaatsByArtikelId(@PathVariable long id) {
        return magazijnPlaatsService.findMagazijnplaatsByArtikelId(id);
    }
}
