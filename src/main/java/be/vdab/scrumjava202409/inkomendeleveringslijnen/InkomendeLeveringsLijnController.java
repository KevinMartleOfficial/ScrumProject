package be.vdab.scrumjava202409.inkomendeleveringslijnen;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("inkomendeleveringslijn")
public class InkomendeLeveringsLijnController {

    private InkomendeLeveringsLijnService inkomendeLeveringsLijnService;

    public InkomendeLeveringsLijnController(InkomendeLeveringsLijnService inkomendeLeveringsLijnService){
        this.inkomendeLeveringsLijnService = inkomendeLeveringsLijnService;
    }

    @PostMapping("add")
    public void voegInkomendeLeveringsLijnenToe(@RequestBody List<InkomendeLeveringsLijnGeenMagazijnId> inkomendeLeveringsLijnList){
        inkomendeLeveringsLijnService.voegInkomendeLeveringsLijnenToe(inkomendeLeveringsLijnList);
    }
}
