package be.vdab.scrumjava202409.inkomendeleveringslijnen;


import be.vdab.scrumjava202409.artikelen.ArtikelRepository;
import be.vdab.scrumjava202409.artikelen.ArtikelService;
import be.vdab.scrumjava202409.magazijnplaatsen.ArtikelMagazijn;
import be.vdab.scrumjava202409.magazijnplaatsen.MagazijnPlaatsRepository;
import be.vdab.scrumjava202409.magazijnplaatsen.MagazijnPlaatsService;
import be.vdab.scrumjava202409.util.PadBerekeningLevering;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@Transactional
public class InkomendeLeveringsLijnService {

    private final ArtikelRepository artikelRepository;
    private final MagazijnPlaatsService magazijnPlaatsService;
    private final MagazijnPlaatsRepository magazijnPlaatsRepository;
    private InkomendeLeveringsLijnRepository inkomendeLeveringsLijnRepository;
    private ArtikelService artikelService;

    public InkomendeLeveringsLijnService(InkomendeLeveringsLijnRepository inkomendeLeveringsLijnRepository, ArtikelRepository artikelRepository, MagazijnPlaatsService magazijnPlaatsService, MagazijnPlaatsRepository magazijnPlaatsRepository, ArtikelService artikelService) {
        this.inkomendeLeveringsLijnRepository = inkomendeLeveringsLijnRepository;
        this.artikelRepository = artikelRepository;
        this.magazijnPlaatsService = magazijnPlaatsService;
        this.magazijnPlaatsRepository = magazijnPlaatsRepository;
  this.artikelService = artikelService;

    }

    public void voegInkomendeLeveringsLijnenToe(List<InkomendeLeveringsLijnGeenMagazijnId> inkomendeLeveringsLijnList) {

        PadBerekeningLevering padBerekening = new PadBerekeningLevering(inkomendeLeveringsLijnList);
        List<InkomendeLeveringsLijnMetStringMagazijnplaats> mogelijkePlaatsen = new ArrayList<>();
        int[] hoeveelLegePlaatsenNodigPerArtikel = new int[inkomendeLeveringsLijnList.size()];
        Arrays.fill(hoeveelLegePlaatsenNodigPerArtikel, 0);
        int[] index = {0};
        inkomendeLeveringsLijnList.forEach(lijn -> {
            int[] hoeveelAanTeVullen = {lijn.getAantalGoedgekeurd()};
            List<ArtikelMagazijn> plaatsenVanArtikel = magazijnPlaatsService.findMagazijnplaatsByArtikelIdDieNogPlaatsHebben(lijn.getArtikelId());
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

                hoeveelAanTeVullen[0] -= maxAantalOpPlaats - plaatsInMagazijn.aantal();
                System.out.println(hoeveelAanTeVullen[0]);

            });
            /*System.out.println("===============");
            System.out.println(hoeveelAanTeVullen[0] / maxAantalOpPlaats);
            System.out.println(Math.max((int) Math.ceil((double)hoeveelAanTeVullen[0] / maxAantalOpPlaats), 0));
            System.out.println("===============");*/

            hoeveelLegePlaatsenNodigPerArtikel[index[0]] += Math.max((int) Math.ceil((double)hoeveelAanTeVullen[0] / maxAantalOpPlaats), 0);
            index[0]++;
        });

        index[0]=0;
        int[] huidigArtikel = {0};
        List<ArtikelMagazijn> legePlaatsen = magazijnPlaatsService.findNodigeMagazijnplaatsByNull(Arrays.stream(hoeveelLegePlaatsenNodigPerArtikel).sum() + 60);
        /*System.out.println("????????????????");
        System.out.println(legePlaatsen);*/
        inkomendeLeveringsLijnList.forEach(lijn -> {
            int maxAantalOpPlaats = artikelService.getArtikelById(lijn.getArtikelId()).getMaxAantalInMagazijnPlaats();
            while(index[0] < legePlaatsen.size()) {
                for (int i = 0; i < hoeveelLegePlaatsenNodigPerArtikel[huidigArtikel[0]] && index[0] < legePlaatsen.size(); i++) {
                    mogelijkePlaatsen.add(new InkomendeLeveringsLijnMetStringMagazijnplaats(
                            lijn.getInkomendeLeveringsId(),
                            inkomendeLeveringsLijnList.get(huidigArtikel[0]).getArtikelId(),
                            lijn.getAantalGoedgekeurd(),
                            lijn.getAantalTeruggestuurd(),
                            legePlaatsen.get(index[0]).aantal(),
                            legePlaatsen.get(index[0]).plaats(),
                            maxAantalOpPlaats,
                            0));
                    index[0]++;
                    System.out.println("huidig artikel " + huidigArtikel[0]);
                }

                huidigArtikel[0] = (huidigArtikel[0] + 1) % inkomendeLeveringsLijnList.size();
            }

        });
        /*System.out.println("=================Alle mogelijke plaatsen=================");
        System.out.println(mogelijkePlaatsen);
        System.out.println(mogelijkePlaatsen.size());*/

        List<InkomendeLeveringsLijnMetStringMagazijnplaats> bestePad = padBerekening.kortstePad2(mogelijkePlaatsen, new ArrayList<>(),0, 0, inkomendeLeveringsLijnList.size());

        bestePad.stream().map(inkomendeLeveringsLijnMetStringMagazijnplaats -> new InkomendeLeveringsLijn(inkomendeLeveringsLijnMetStringMagazijnplaats.getInkomendeLeveringsId(),
                inkomendeLeveringsLijnMetStringMagazijnplaats.getArtikelId(),
                inkomendeLeveringsLijnMetStringMagazijnplaats.getHoeveelheidWeggelegd(),
                inkomendeLeveringsLijnMetStringMagazijnplaats.getAantalTeruggestuurd(),
                magazijnPlaatsService.findMagazijnpaatsIdByMagazijnplaatsString(inkomendeLeveringsLijnMetStringMagazijnplaats.getMagazijnPlaats())))
                .forEach(inkomendeLeveringsLijn -> inkomendeLeveringsLijnRepository.voegInkomendeLeveringsLijnToe(inkomendeLeveringsLijn));
    }

    //LEV-4.1 fetch method om alle inkomendeLeveringenLijnen te krijgen op basis van inkomendeLeveringsId
    //hierbij vorm ik de lijnen om naar DTO's voor de frontend

    public List<DTOArtikelNaamInkomendeLeveringsLijnAantalGoedgekeurdEnMagazijnPlaats> findAllInkomendeLeveringsLijnenByInkomendeLeveringsId(long id){
        return inkomendeLeveringsLijnRepository.findAllInkomendeLeveringsLijnenByInkomendeLeveringsId(id)
                .stream()
                .map(lijn -> new DTOArtikelNaamInkomendeLeveringsLijnAantalGoedgekeurdEnMagazijnPlaats(
                        (artikelRepository.getArtikelById(lijn.getArtikelId()).getNaam()), //artikelnaam
                         lijn.getAantalGoedgekeurd(),//aantalStuks
                        String.valueOf(magazijnPlaatsRepository.findByMagazijnPlaatsId(lijn.getMagazijnPlaatsId()).getRek()) +
                        String.valueOf(magazijnPlaatsRepository.findByMagazijnPlaatsId(lijn.getMagazijnPlaatsId()).getRij())))//magazijnplaats wordt gecreëerd als een String (bvb 5A)
                .toList();


    }
}
