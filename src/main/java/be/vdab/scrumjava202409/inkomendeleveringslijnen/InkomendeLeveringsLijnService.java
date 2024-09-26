package be.vdab.scrumjava202409.inkomendeleveringslijnen;

import be.vdab.scrumjava202409.artikelen.ArtikelRepository;
import be.vdab.scrumjava202409.magazijnplaatsen.ArtikelMagazijn;
import be.vdab.scrumjava202409.magazijnplaatsen.MagazijnPlaats;
import be.vdab.scrumjava202409.magazijnplaatsen.MagazijnPlaatsRepository;
import be.vdab.scrumjava202409.magazijnplaatsen.MagazijnPlaatsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional // readOnly = true zetten misschien?
public class InkomendeLeveringsLijnService {

    private final ArtikelRepository artikelRepository;
    private final MagazijnPlaatsService magazijnPlaatsService;
    private final MagazijnPlaatsRepository magazijnPlaatsRepository;
    private InkomendeLeveringsLijnRepository inkomendeLeveringsLijnRepository;

    public InkomendeLeveringsLijnService(InkomendeLeveringsLijnRepository inkomendeLeveringsLijnRepository, ArtikelRepository artikelRepository, MagazijnPlaatsService magazijnPlaatsService, MagazijnPlaatsRepository magazijnPlaatsRepository) {
        this.inkomendeLeveringsLijnRepository = inkomendeLeveringsLijnRepository;
        this.artikelRepository = artikelRepository;
        this.magazijnPlaatsService = magazijnPlaatsService;
        this.magazijnPlaatsRepository = magazijnPlaatsRepository;
    }

    public void voegInkomendeLeveringsLijnenToe(List<InkomendeLeveringsLijnGeenMagazijnId> inkomendeLeveringsLijnList) {
        //TODO Hier moet algo nog toegepast worden voor juiste magazijnplaatsen
        inkomendeLeveringsLijnList.stream()
                .map(lijn ->
                        new InkomendeLeveringsLijn(lijn.inkomendeLeveringsId(), lijn.artikelId(),
                                lijn.aantalGoedgekeurd() //'e' op het einde verwijderen
                                        , lijn.aantalTeruggestuurd(), 1))
                .forEach(lijn ->
                        inkomendeLeveringsLijnRepository.voegInkomendeLeveringsLijnToe(lijn));
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
