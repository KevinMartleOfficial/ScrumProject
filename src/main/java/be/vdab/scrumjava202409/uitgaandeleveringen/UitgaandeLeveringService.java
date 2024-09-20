package be.vdab.scrumjava202409.uitgaandeleveringen;

import be.vdab.scrumjava202409.artikelen.ArtikelRepository;
import be.vdab.scrumjava202409.bestellingen.BestelIdArtikelIdNaamAantalMagazijnplaats;
import be.vdab.scrumjava202409.bestellingen.BestellingRepository;
import be.vdab.scrumjava202409.magazijnplaatsen.MagazijnPlaatsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class UitgaandeLeveringService {
    private final MagazijnPlaatsService magazijnPlaatsService;
    private UitgaandeLeveringRepository uitgaandeLeveringRepository;
    private BestellingRepository bestellingRepository;
    private ArtikelRepository artikelRepository;

    private List<BestelIdArtikelIdNaamAantalMagazijnplaats> banamList;

    public UitgaandeLeveringService(UitgaandeLeveringRepository uitgaandeLeveringRepository,
                                    BestellingRepository bestellingRepository,
                                    MagazijnPlaatsService magazijnPlaatsService,
                                    ArtikelRepository artikelRepository){
        this.uitgaandeLeveringRepository = uitgaandeLeveringRepository;
        this.bestellingRepository = bestellingRepository;
        this.magazijnPlaatsService = magazijnPlaatsService;
        this.artikelRepository = artikelRepository;
        this.banamList = new ArrayList<>();
    }

    public void voegToeAanBestelIdArtikelIdNaamAantalMagazijnplaatsList(BestelIdArtikelIdNaamAantalMagazijnplaats banam){
        banamList.add(banam);
    }

    public void clearBanamLijst(){
        banamList.clear();
    }

    public long addUitgaandeLevering(long bestelId){
        for(BestelIdArtikelIdNaamAantalMagazijnplaats artikelAantal : banamList){


            magazijnPlaatsService.haalArtikelUitMagazijnPlaats(artikelAantal.aantal(), artikelAantal.magazijnPlaats());
            artikelRepository.verlaagVoorraad(artikelAantal.artikelId(), artikelAantal.aantal());
        }
        banamList.clear();
        return uitgaandeLeveringRepository.addUitgaandeLevering(bestellingRepository.findById(bestelId));
    }
}
