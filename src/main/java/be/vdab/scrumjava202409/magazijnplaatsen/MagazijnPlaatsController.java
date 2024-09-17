package be.vdab.scrumjava202409.magazijnplaatsen;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MagazijnPlaatsController {
    private final MagazijnPlaatsService magazijnPlaatsService;

    public MagazijnPlaatsController(MagazijnPlaatsService magazijnPlaatsService) {
        this.magazijnPlaatsService = magazijnPlaatsService;
    }

    @GetMapping("artikelen/{id}/magazijnplaatsen")
    List<ArtikelMagazijn> findMagazijnplaatsByArtikelId(@PathVariable long id) {
        return magazijnPlaatsService.findMagazijnplaatsByArtikelId(id);
    }
}
