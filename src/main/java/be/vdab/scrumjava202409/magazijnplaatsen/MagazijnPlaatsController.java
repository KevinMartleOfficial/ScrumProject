package be.vdab.scrumjava202409.magazijnplaatsen;

import org.springframework.web.bind.annotation.RestController;

@RestController
public class MagazijnPlaatsController {
    private final MagazijnPlaatsService magazijnPlaatsService;

    public MagazijnPlaatsController(MagazijnPlaatsService magazijnPlaatsService) {
        this.magazijnPlaatsService = magazijnPlaatsService;
    }
}
