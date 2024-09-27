package be.vdab.scrumjava202409.magazijnplaatsen;

import be.vdab.scrumjava202409.artikelen.ArtikelRepository;
import be.vdab.scrumjava202409.bestellijnen.Bestellijn;
import be.vdab.scrumjava202409.bestellingen.BestelIdArtikelIdNaamAantalMagazijnplaats;
import be.vdab.scrumjava202409.bestellingen.BestellingService;
import be.vdab.scrumjava202409.util.PadBerekening;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class MagazijnPlaatsService {
    private final MagazijnPlaatsRepository magazijnPlaatsRepository;
    private final BestellingService bestellingService;
    private final ArtikelRepository artikelRepository;

    public MagazijnPlaatsService(MagazijnPlaatsRepository magazijnPlaatsRepository,
                                 BestellingService bestellingService, ArtikelRepository artikelRepository) {
        this.magazijnPlaatsRepository = magazijnPlaatsRepository;
        this.bestellingService = bestellingService;
        this.artikelRepository = artikelRepository;
    }

    @Transactional
    public void haalArtikelUitMagazijnPlaats(int aantal, String plaats){
        String rij = String.valueOf(plaats.charAt(0));
        int rek = Integer.parseInt(plaats.substring(1));
        magazijnPlaatsRepository.haalArtikelUitMagazijnPlaats(aantal, rij, rek);
    }

    public List<ArtikelMagazijn> findMagazijnplaatsByArtikelId(long artikelId) {
        List<MagazijnPlaats> magazijnPlaatsen = magazijnPlaatsRepository.findMagazijnplaatsByArtikelId(artikelId);
        return magazijnPlaatsen
                .stream()
                .map(mp -> new ArtikelMagazijn(mp.getRij() + String.valueOf(mp.getRek()), mp.getAantal()))
                .collect(Collectors.toList());
    }

    public List<ArtikelMagazijn> findMagazijnplaatsByArtikelIdDieNogPlaatsHebben(long artikelId) {
        List<MagazijnPlaats> magazijnPlaatsen = magazijnPlaatsRepository.findMagazijnplaatsByArtikelIdDieNogPlaatsHebben(artikelId);
        return magazijnPlaatsen
                .stream()
                .map(mp -> new ArtikelMagazijn(mp.getRij() + String.valueOf(mp.getRek()), mp.getAantal()))
                .collect(Collectors.toList());
    }

    public List<BestelIdArtikelIdNaamAantalMagazijnplaats> findAlleBanamVanEersteBestellingInMagazijn(){
        List<Bestellijn> temp = bestellingService.findAllBestellijnenVanEersteBestelling();
        PadBerekening padBerekening = new PadBerekening(temp);

        List<BestelIdArtikelIdNaamAantalMagazijnplaats> bestellijnen = new ArrayList<>();
        temp.forEach(bestellijn -> {
                    String artikelnaam = artikelRepository.getArtikelById(bestellijn.getArtikelId()).getNaam();
                    List<MagazijnPlaats> plaatsen = magazijnPlaatsRepository.findMagazijnplaatsByArtikelId(bestellijn.getArtikelId());
                    BestelIdArtikelIdNaamAantalMagazijnplaats banam;
                    for(MagazijnPlaats magazijnPlaats : plaatsen){
                        String magazijnPlaatsString = magazijnPlaats.getRij() + Integer.toString(magazijnPlaats.getRek());
                        banam = new BestelIdArtikelIdNaamAantalMagazijnplaats(
                                bestellijn.getBestelId(), bestellijn.getArtikelId(),
                                artikelnaam,
                                bestellijn.getAantalBesteld(), magazijnPlaatsString, magazijnPlaats.getAantal());

                        bestellijnen.add(banam);
                    }
                });
        List<BestelIdArtikelIdNaamAantalMagazijnplaats> kortstePad = padBerekening.kortstePad2(bestellijnen, new ArrayList<>(),0, 0, temp.size());

        return kortstePad;
    }

    public List<ArtikelMagazijn> findNodigeMagazijnplaatsByNull(int hoeveelheidLegePlaatsen){
        List<MagazijnPlaats> magazijnPlaatsen = magazijnPlaatsRepository.findMagazijnplaatsByNull(hoeveelheidLegePlaatsen);
        return magazijnPlaatsen
                .stream()
                .map(mp -> new ArtikelMagazijn(mp.getRij() + String.valueOf(mp.getRek()), mp.getAantal()))
                .collect(Collectors.toList());
    }

    public long findMagazijnpaatsIdByMagazijnplaatsString(String magazijnplaats){
        String rij = String.valueOf(magazijnplaats.charAt(0));
        int rek = Integer.parseInt(magazijnplaats.substring(1));

        return magazijnPlaatsRepository.findMagazijnpaatsIdByMagazijnplaatsRijEnRek(rij, rek);
    }


}
