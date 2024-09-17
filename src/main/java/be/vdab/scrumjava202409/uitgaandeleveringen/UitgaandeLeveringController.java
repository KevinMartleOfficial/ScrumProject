package be.vdab.scrumjava202409.uitgaandeleveringen;

import be.vdab.scrumjava202409.bestellingen.Bestelling;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
