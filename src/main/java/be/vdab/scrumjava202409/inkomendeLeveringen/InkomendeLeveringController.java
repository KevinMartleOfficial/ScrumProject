package be.vdab.scrumjava202409.inkomendeLeveringen;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("inkomende-leveringen")
public class InkomendeLeveringController {
    private final InkomendeLeveringService inkomendeLeveringService;

    public InkomendeLeveringController(InkomendeLeveringService inkomendeLeveringService) {
        this.inkomendeLeveringService = inkomendeLeveringService;
    }

    @PostMapping("toevoegen")
    long create(@Valid @RequestBody InkomendeLeveringMetNaam inkomendeLeveringMetNaam) {
        return inkomendeLeveringService.create(inkomendeLeveringMetNaam);
    }
}
