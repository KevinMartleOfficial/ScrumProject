package be.vdab.scrumjava202409.uitgaandeleveringen;

import be.vdab.scrumjava202409.bestellingen.Bestelling;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("uitgaandelevering")
public class UitgaandeLeveringController {
    private UitgaandeLeveringService uitgaandeLeveringService;

    public UitgaandeLeveringController(UitgaandeLeveringService uitgaandeLeveringService){
        this.uitgaandeLeveringService = uitgaandeLeveringService;
    }

    @PostMapping("add")
    public long addUitgaandeLevering(@RequestBody long bestelId){
        return uitgaandeLeveringService.addUitgaandeLevering(bestelId);
    }
}
