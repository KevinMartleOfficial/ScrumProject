package be.vdab.scrumjava202409.bestellingen;

import be.vdab.scrumjava202409.artikelen.ArtikelRepository;
import be.vdab.scrumjava202409.bestellijnen.Bestellijn;
import be.vdab.scrumjava202409.bestellijnen.BestellijnRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BestellingService {

    private BestellingRepository bestellingRepository;
    private BestellijnRepository bestellijnRepository;
    private ArtikelRepository artikelRepository;

    public BestellingService(BestellingRepository bestellingRepository,
                             BestellijnRepository bestellijnRepository,
                             ArtikelRepository artikelRepository){
        this.bestellingRepository = bestellingRepository;
        this.bestellijnRepository = bestellijnRepository;
        this.artikelRepository = artikelRepository;
    }

    public List<BestellingTVDTO> eerste5bestellingenTV(){
        return bestellingRepository.eerste5bestellingen().stream()
                .map(bestelling -> {
                    List<Bestellijn> bestellijnen = bestellijnRepository.getAlleBestellijnenVanBestelling(bestelling);
                    int aantalProducten = 0;
                    int maxGewicht = 0;
                    for(Bestellijn bestellijn : bestellijnen){
                        aantalProducten += bestellijn.getAantalBesteld();
                        maxGewicht += artikelRepository.getGewichtArtikel(bestellijn.getArtikelId() * bestellijn.getAantalBesteld());
                    }
                    return new BestellingTVDTO(bestelling, aantalProducten, maxGewicht);
                }).toList();
    }
}
