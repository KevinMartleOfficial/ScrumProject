package be.vdab.scrumjava202409.inkomendeleveringslijnen;

import be.vdab.scrumjava202409.artikelen.Artikel;
import be.vdab.scrumjava202409.artikelen.ArtikelService;
import be.vdab.scrumjava202409.magazijnplaatsen.ArtikelMagazijn;
import be.vdab.scrumjava202409.magazijnplaatsen.MagazijnPlaats;
import be.vdab.scrumjava202409.magazijnplaatsen.MagazijnPlaatsRepository;
import be.vdab.scrumjava202409.magazijnplaatsen.MagazijnPlaatsService;
import be.vdab.scrumjava202409.util.PadBerekening;
import be.vdab.scrumjava202409.util.PadBerekeningLevering;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class InkomendeLeveringsLijnService {

    private InkomendeLeveringsLijnRepository inkomendeLeveringsLijnRepository;
    private MagazijnPlaatsService magazijnPlaatsService;
    private ArtikelService artikelService;

    public InkomendeLeveringsLijnService(InkomendeLeveringsLijnRepository inkomendeLeveringsLijnRepository,
                                         MagazijnPlaatsService magazijnPlaatsService,
                                         ArtikelService artikelService) {
        this.inkomendeLeveringsLijnRepository = inkomendeLeveringsLijnRepository;
        this.magazijnPlaatsService = magazijnPlaatsService;
        this.artikelService = artikelService;
    }

    public void voegInkomendeLeveringsLijnenToe(List<InkomendeLeveringsLijnGeenMagazijnId> inkomendeLeveringsLijnList) {

        PadBerekeningLevering padBerekening = new PadBerekeningLevering(inkomendeLeveringsLijnList);
        List<InkomendeLeveringsLijnMetStringMagazijnplaats> mogelijkePlaatsen = new ArrayList<>();

        inkomendeLeveringsLijnList.forEach(lijn -> {
            List<ArtikelMagazijn> plaatsenVanArtikel = magazijnPlaatsService.findMagazijnplaatsByArtikelId(lijn.getArtikelId());
            int maxAantalOpPlaats = artikelService.getArtikelById(lijn.getArtikelId()).getMaxAantalInMagazijnPlaats();
            plaatsenVanArtikel.forEach(plaatsInMagazijn -> {
                mogelijkePlaatsen.add(new InkomendeLeveringsLijnMetStringMagazijnplaats(
                        lijn.getInkomendeLeveringsId(),
                        lijn.getArtikelId(),
                        lijn.getAantalGoedgekeurd(),
                        lijn.getAantalTeruggestuurd(),
                        plaatsInMagazijn.aantal(),
                        plaatsInMagazijn.plaats(),
                        maxAantalOpPlaats,
                        0));
            });
            List<ArtikelMagazijn> legePlaatsen = magazijnPlaatsService.findMagazijnplaatsByNull();
            legePlaatsen.forEach(legePlaats -> {
                mogelijkePlaatsen.add(new InkomendeLeveringsLijnMetStringMagazijnplaats(
                        lijn.getInkomendeLeveringsId(),
                        lijn.getArtikelId(),
                        lijn.getAantalGoedgekeurd(),
                        lijn.getAantalTeruggestuurd(),
                        legePlaats.aantal(),
                        legePlaats.plaats(),
                        maxAantalOpPlaats,
                        0));
            });
        });


        List<InkomendeLeveringsLijnMetStringMagazijnplaats> bestePad = padBerekening.kortstePad2(mogelijkePlaatsen, new ArrayList<>(),0, 0, inkomendeLeveringsLijnList.size());

        bestePad.stream().map(inkomendeLeveringsLijnMetStringMagazijnplaats -> new InkomendeLeveringsLijn(inkomendeLeveringsLijnMetStringMagazijnplaats.getInkomendeLeveringsId(),
                inkomendeLeveringsLijnMetStringMagazijnplaats.getArtikelId(),
                inkomendeLeveringsLijnMetStringMagazijnplaats.getHoeveelheidWeggelegd(),
                inkomendeLeveringsLijnMetStringMagazijnplaats.getAantalTeruggestuurd(),
                magazijnPlaatsService.findMagazijnpaatsIdByMagazijnplaatsString(inkomendeLeveringsLijnMetStringMagazijnplaats.getMagazijnPlaats())))
                .forEach(inkomendeLeveringsLijn -> inkomendeLeveringsLijnRepository.voegInkomendeLeveringsLijnToe(inkomendeLeveringsLijn));
    }
}
