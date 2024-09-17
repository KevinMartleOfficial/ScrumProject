package be.vdab.scrumjava202409.uitgaandeleveringen;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("uitgaandelevering")
public class UitgaandeLeveringController {
    private UitgaandeLeveringService uitgaandeLeveringService;

    public UitgaandeLeveringController(UitgaandeLeveringService uitgaandeLeveringService){
        this.uitgaandeLeveringService = uitgaandeLeveringService;
    }
}
