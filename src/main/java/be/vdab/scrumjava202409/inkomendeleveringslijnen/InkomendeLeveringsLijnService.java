package be.vdab.scrumjava202409.inkomendeleveringslijnen;


import be.vdab.scrumjava202409.artikelen.ArtikelRepository;
import be.vdab.scrumjava202409.artikelen.ArtikelService;
import be.vdab.scrumjava202409.magazijnplaatsen.ArtikelMagazijn;
import be.vdab.scrumjava202409.magazijnplaatsen.MagazijnPlaats;
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
                System.out.println(lijn.getAantalGoedgekeurd());
                hoeveelAanTeVullen[0] -= maxAantalOpPlaats - plaatsInMagazijn.aantal();
                System.out.println(hoeveelAanTeVullen[0]);
                System.out.println(maxAantalOpPlaats);System.out.println("????????????????");

            });
            System.out.println("===============");
            System.out.println(Math.max((int) Math.ceil((double)hoeveelAanTeVullen[0] / maxAantalOpPlaats), 0));
            System.out.println("===============");

            hoeveelLegePlaatsenNodigPerArtikel[index[0]] += Math.max((int) Math.ceil((double)hoeveelAanTeVullen[0] / maxAantalOpPlaats), 0);
            index[0]++;
        });

        index[0]=0;
        int[] huidigArtikel = {0};
        System.out.println(Arrays.stream(hoeveelLegePlaatsenNodigPerArtikel).sum() + 60);
        List<ArtikelMagazijn> legePlaatsen = magazijnPlaatsService.findNodigeMagazijnplaatsByNull(Arrays.stream(hoeveelLegePlaatsenNodigPerArtikel).sum() + 60);
        /*System.out.println("????????????????");
        System.out.println(legePlaatsen);*/
        inkomendeLeveringsLijnList.forEach(lijn -> {

            while(index[0] < legePlaatsen.size() && legePlaatsen.size() > 60) {
                int maxAantalOpPlaats = artikelService.getArtikelById(inkomendeLeveringsLijnList.get(huidigArtikel[0]).getArtikelId()).getMaxAantalInMagazijnPlaats();
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
        bestePad.forEach(System.out::println);
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
                        String.valueOf(String.valueOf(magazijnPlaatsRepository.findByMagazijnPlaatsId(lijn.getMagazijnPlaatsId()).getRij()) + magazijnPlaatsRepository.findByMagazijnPlaatsId(lijn.getMagazijnPlaatsId()).getRek())
                        ))//magazijnplaats wordt gecreëerd als een String (bvb 5A)
                .toList();
    }
    //LEV-5.3 updaten voorraad in de tabel artikelen, en bij de tabel magazijnplaatsen
    public void verhoogVoorraden(long inkomendeLeveringsId){
        List<InkomendeLeveringsLijn>alleLijnen = inkomendeLeveringsLijnRepository.findAllInkomendeLeveringsLijnenByInkomendeLeveringsId(inkomendeLeveringsId);
        for(InkomendeLeveringsLijn lijn : alleLijnen){
            artikelRepository.verhoogVoorraad(lijn.getArtikelId(), lijn.getAantalGoedgekeurd());
            MagazijnPlaats magazijn = magazijnPlaatsRepository.findByMagazijnPlaatsId(lijn.getMagazijnPlaatsId());
            magazijnPlaatsRepository.plaatsArtikelOpMagazijnPlaats(
                    lijn.getAantalGoedgekeurd(),//aantal
                    String.valueOf(magazijn.getRij()),//rij
                    magazijn.getRek(),//rek
                    lijn.getArtikelId());//artikelId
        }
    }
}
