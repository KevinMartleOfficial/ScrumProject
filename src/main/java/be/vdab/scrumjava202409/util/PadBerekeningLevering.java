package be.vdab.scrumjava202409.util;


import be.vdab.scrumjava202409.inkomendeleveringslijnen.InkomendeLeveringsLijnGeenMagazijnId;
import be.vdab.scrumjava202409.inkomendeleveringslijnen.InkomendeLeveringsLijnMetStringMagazijnplaats;

import java.util.*;

public class PadBerekeningLevering {

    //Een map maken voor alle mogelijkheden(List) met de afstand van het pad(Integer)
    //mogelijkHeden is voor de paden waar je enkel aanvult en dus geen lege plaatsen gebruikt
    //mogelijkHedenMetLegePlaats is voor de paden waar wel een leeg pad wordt gebruikt
    private Map<Integer, List<InkomendeLeveringsLijnMetStringMagazijnplaats>> mogelijkHeden = new HashMap<>();
    private Map<Integer, List<InkomendeLeveringsLijnMetStringMagazijnplaats>> mogelijkHedenMetLegePlaats = new HashMap<>();

    //Dit zijn de inkomendeLeveringslijnen
    private List<InkomendeLeveringsLijnGeenMagazijnId> leverbonLijnen;

    // Volgende 2 variabelen zijn voor kleinste afstand van een pad bij te houden.
    // Dit is voor enkele paden op voorhand al te schrappen
    // vb: Er zijn 3 artikelen die je moet plaatsen.
    // Vanuitgaan dan je op elke plaats die je passeert alle artikelen kan plaatsen
    // PAD1 a0(begin) -> a2 -> b2 -> c2 -> a0 (16 stappen) minAfstandMogelijkheden = 16
    // PAD2 a0 -> a2 -> z2 (31 stappen)
    // PAD2 heeft nu al meer stappen dan PAD1, dus we kunnen PAD2 al schrappen
    private int minAfstandMogelijkheden = Integer.MAX_VALUE;
    private int minAfstandMogelijkhedenLegePlaats = Integer.MAX_VALUE;
    private int minNieuwePlaatsenNodig = Integer.MAX_VALUE;

    // Constructor die de leverbonLijnen krijgt
    public PadBerekeningLevering(List<InkomendeLeveringsLijnGeenMagazijnId> leverbonLijnen){
        this.leverbonLijnen = leverbonLijnen.stream().map(InkomendeLeveringsLijnGeenMagazijnId::new).toList();
    }

    // Afstand berekenen tussen 2 plaatsen vb: a2 -> b2 = 5
    private int berekenLengtePad1Artikel(String beginPositie, String eindPositie){
        if (eindPositie.charAt(0) == beginPositie.charAt(0)) {

            return Math.abs(Integer.parseInt(eindPositie.substring(1))) - ((Integer.parseInt(beginPositie.substring(1))));
        }

        return ((int) (eindPositie.charAt(0)) - beginPositie.charAt(0)) + ((Integer.parseInt(eindPositie.substring(1))) + ((Integer.parseInt(beginPositie.substring(1)))));
    }

    // Afstand berekenen van een volledig pad vb: a0(begin) -> a2 -> b2 -> c2 -> a0 = 16
    private int berekenVolledigPad(List<InkomendeLeveringsLijnMetStringMagazijnplaats> mogelijkheid){

        if(!mogelijkheid.isEmpty()){

            int totLengte = berekenLengtePad1Artikel("A0", mogelijkheid.getLast().getMagazijnPlaats());
            totLengte += berekenLengtePad1Artikel("A0", mogelijkheid.getFirst().getMagazijnPlaats());

            for(int i = 0; i < mogelijkheid.size() - 1; i++){

                totLengte += berekenLengtePad1Artikel(mogelijkheid.get(i).getMagazijnPlaats(), mogelijkheid.get(i+1).getMagazijnPlaats());
            }

            return totLengte;
        }
        return Integer.MAX_VALUE;
    }

    // Hier worden alle paden berekent
    // @alleArtikelenInMagazijn = dit zijn alle plaatsen waar je de artikelen op de leverbon kan leggen
    // @huidigPad = het huidige pad dat je aan het controleren bent
    // @start = vanaf welke plaats je wilt beginnen om het pad verder te zetten
    // @end = laatste plaats die je kan controleren
    // @index = de huidige hoeveelheid plaatsen je al hebt gebruikt
    // @r = hoeveel plaatsen er wordt verwacht dat je gebruikt
    // @leverbonlijnen = de effectieve leverbonlijnen

    public boolean kanToevoegenAanMogelijkheden(List<InkomendeLeveringsLijnGeenMagazijnId> leverbonlijnen){
        return new ArrayList<>(leverbonlijnen.stream().map(InkomendeLeveringsLijnGeenMagazijnId::new).filter(ll -> ll.getAantalGoedgekeurd() > 0).toList()).isEmpty();
    }
    public void kortstePad(List<InkomendeLeveringsLijnMetStringMagazijnplaats> alleArtikelenInMagazijn, List<InkomendeLeveringsLijnMetStringMagazijnplaats> huidigPad, int start, int end, int index, int r, List<InkomendeLeveringsLijnGeenMagazijnId> leverbonlijnen){
        int tempEnd = end;
        int nieuwePlaatsenOpPad = 0;
        // Wanneer de hoeveelheid plaatsen dat je artikelen hebt geplaats(index) even groot is als de verwachting(r)
        // Hier heb je dus een volwaardig pad
        /*System.out.println("\u001B[41m"+r);
        System.out.println(huidigPad.size() + "\u001B[0m");*/
        if(index == r){
            System.out.println("=======TOEVOEGEN========");
            System.out.println(huidigPad);
            // Afstand berekenen van pad
            int lengteVolledigPad = berekenVolledigPad(huidigPad);



            // Als er een lege plaats is gebruikt op je pad
            if (!huidigPad.stream().filter(illmsmp -> illmsmp.getHoeveelheidOpMagazijnplaats() == 0).toList().isEmpty()){
                // Je hebt nog geen pad met dezelfde afstand
                if(!mogelijkHedenMetLegePlaats.containsKey(lengteVolledigPad)){
                    // Als dit pad een kleinere afstand nodig heeft dan het beste pad tot nu toe
                    // dan wordt dit de nieuwe norm voor andere paden mee te vergelijken
                    minAfstandMogelijkhedenLegePlaats = Math.min(minAfstandMogelijkhedenLegePlaats, lengteVolledigPad);
                    // Plaats de afstand en het pad in de mogelijkheden die een lege plaats gebruiken
                    mogelijkHedenMetLegePlaats.put(lengteVolledigPad, huidigPad);
                    minNieuwePlaatsenNodig = Math.min(minNieuwePlaatsenNodig, huidigPad.stream().filter(illmsmp -> illmsmp.getHoeveelheidOpMagazijnplaats() == 0).toList().size());

                }
            }
            // Als er geen enkele lege plaats is gebruikt op je pad
            else{
                /*System.out.println("klaar voor toevoegen");
                System.out.println(huidigPad);
                System.out.println("met lengte " + lengteVolledigPad);*/

                if(!mogelijkHeden.containsKey(lengteVolledigPad)){

                    minAfstandMogelijkheden = Math.min(minAfstandMogelijkheden, lengteVolledigPad);
                    mogelijkHeden.put(lengteVolledigPad, huidigPad);
                }
            }
            // Hier keer je een plaats terug voor een nieuw pad te proberen
            // vb: a -> b -> c is net toegevoegd als een mogelijk pad
            // Na ga ik terug naar b zodat ik a -> b -> d kan proberen
            return;
        }

        // Hier wordt het huidige pad geschrapt wanneer er al meer afstand is afgelegd dan het beste pad in mogelijkheden
        if(!huidigPad.stream().filter(illmsmp -> illmsmp.getHoeveelheidOpMagazijnplaats() == 0).toList().isEmpty()){
            if(berekenVolledigPad(huidigPad) > minAfstandMogelijkhedenLegePlaats){
                return;
            }
        }else{
            if(berekenVolledigPad(huidigPad) > minAfstandMogelijkheden){
                return;
            }
        }

        if(minNieuwePlaatsenNodig < huidigPad.stream().filter(illmsmp -> illmsmp.getHoeveelheidOpMagazijnplaats() == 0).toList().size()){
            return;
        }


        // Hier worden alle combinaties van paden gemaakt
        for(int i = start; (i < tempEnd) && (tempEnd - i + 1 >= r - index); i++){


            // Elke combinatie van een pad heeft een nieuwe verwachte hoeveelheid van plaatsen dat er gepasseerd moet worden
            // In het begin zal dit staan op hoeveel leverbon lijnen je hebt
            int tempR = r;

            // Kopie maken van de leverbonlijnen voor andere object referenties
            List<InkomendeLeveringsLijnGeenMagazijnId> tempLeverbonlijnen = new ArrayList<>(leverbonlijnen.stream().map(InkomendeLeveringsLijnGeenMagazijnId::new).toList());

            // Plaats in het magazijn dat je een artikel van je leverbon kan leggen
            InkomendeLeveringsLijnMetStringMagazijnplaats huidigArtikel = alleArtikelenInMagazijn.get(i);

            // De leverbonlijn nemen waar je op de plaats waar je nu bent een artikel kunt leggen
            InkomendeLeveringsLijnGeenMagazijnId leverlijn = tempLeverbonlijnen.stream()
                    .filter(l -> l.getArtikelId() == huidigArtikel.getArtikelId())
                    .toList()
                    .getFirst();


            // Kopie maken van de locatie waar je nu bent, om deze dan toe te voegen aan je huidig pad
            InkomendeLeveringsLijnMetStringMagazijnplaats bewerkt;

            // Als je nog een artikel hebt dat je op de huidge plaats weg kan leggen
            // En je mag er nog meer leggen op deze plaats
            if(!(leverlijn.getAantalGoedgekeurd() < 1) && huidigArtikel.getMaxAantalOpPlaats() - huidigArtikel.getHoeveelheidOpMagazijnplaats() !=0){

                /*System.out.println("\u001B[44m=========IK GA HIER TOEVOEGEN=========\u001B[0m");
                System.out.println(huidigPad);
                System.out.println("\u001B[44m=========IK GA HIER TOEVOEGEN=========\u001B[0m");*/

                // Kopie maken van het huidige pad
                // huidigPad: a2 ->
                // nieuweHuidigPad: a2 ->
                List<InkomendeLeveringsLijnMetStringMagazijnplaats> nieuweHuidigPad = new ArrayList<>(huidigPad.stream().map(InkomendeLeveringsLijnMetStringMagazijnplaats::new).toList());


                // Als je meer atikelen bij hebt dan dat je op deze plaats nog mag leggen
                if(huidigArtikel.getMaxAantalOpPlaats() - huidigArtikel.getHoeveelheidOpMagazijnplaats() < leverlijn.getAantalGoedgekeurd() - huidigArtikel.getHoeveelheidWeggelegd()){
                    // Kopie van de huidge plaats met de juiste hoeveelheid artikelen dat je hebt kunnen plaatsen
                    bewerkt = new InkomendeLeveringsLijnMetStringMagazijnplaats(huidigArtikel.getInkomendeLeveringsId(), huidigArtikel.getArtikelId(), huidigArtikel.getAantalGoedgekeurd(), huidigArtikel.getAantalTeruggestuurd(), huidigArtikel.getHoeveelheidOpMagazijnplaats(), huidigArtikel.getMagazijnPlaats(), huidigArtikel.getMaxAantalOpPlaats(), huidigArtikel.getMaxAantalOpPlaats() - huidigArtikel.getHoeveelheidOpMagazijnplaats());
                    // Verhoog de verwachting van de hoeveelheid plaatsen dat je moet passeren
                    // Aangezien je niet je volledige leverbon lijn hebt kunnen plaatsen op 1 plaats
                    tempR++;
                }else{
                    // Idem
                    bewerkt = new InkomendeLeveringsLijnMetStringMagazijnplaats(huidigArtikel.getInkomendeLeveringsId(), huidigArtikel.getArtikelId(), huidigArtikel.getAantalGoedgekeurd(), huidigArtikel.getAantalTeruggestuurd(), huidigArtikel.getHoeveelheidOpMagazijnplaats(), huidigArtikel.getMagazijnPlaats(), huidigArtikel.getMaxAantalOpPlaats(), huidigArtikel.getAantalGoedgekeurd());
                }
                // Volgende 2 lijnen is omdat elk artikel op elke lege plaats kan
                // vb: artikelen -> x, y, z
                // lege plaatsen -> l1, l2, l3
                // Wat er in alleArtikelenInMagazijn zit -> xl1, xl2, xl3, yl1, yl2, yl3, zl1, zl2, zl3
                // Het kan dus dat x op l1 komt en y ook op l1 komt

                // Om dit te voorkomen wordt er een kopie gemaakt van de mogelijke plaatsen die verder wordt gebruikt
                // op het huidige pad zonder de plaatsen waar net een artikel op geplaats is

                // @nieuwePLaatsenMagazijn wordt dus -> xl2, xl3, yl2, yl3, zl2, zl3
                //List<InkomendeLeveringsLijnMetStringMagazijnplaats> nieuwePlaatsenMagazijn = new ArrayList<>(alleArtikelenInMagazijn.stream().map(InkomendeLeveringsLijnMetStringMagazijnplaats::new).toList());
                //nieuwePlaatsenMagazijn.removeIf(plaats -> plaats.getMagazijnPlaats().equals(huidigArtikel.getMagazijnPlaats()));

                // Verander de hoeveelheid plaatsen in het magazijn, aangezien er net zijn verwijderd
                //int nieuweEnd = nieuwePlaatsenMagazijn.size();

                // Op de leverbonlijn wordt de hoeveelheid goedgekeurd verminderd
                // Zo weten we de hoeveelheid dat we nog weg moeten leggen van een artikel

                leverlijn.verminderAantalGoedgekeurd(bewerkt.getHoeveelheidWeggelegd());

                // Voeg het kopie van de huidige locatie toe aan ons huidig pad
                // huidigPad: a2 ->
                // nieuweHuidigPad: a2 -> xl1
                nieuweHuidigPad.add(bewerkt);

                // Roep deze volledige methode opnieuw op, maar met ons nieuw huidigpad
                // en hoeveelheden dat we nog weg moeten leggen
                /*System.out.println("\u001B[42m"+nieuweHuidigPad + "\u001B[0m");*/

                kortstePad(alleArtikelenInMagazijn, nieuweHuidigPad, i+1, end, nieuweHuidigPad.size() , tempR, tempLeverbonlijnen);
            }
        }
    }

    // De hoofdmethode die wordt opgeroepen in InkomendeLeveringsLijnService
    public List<InkomendeLeveringsLijnMetStringMagazijnplaats> kortstePad2(List<InkomendeLeveringsLijnMetStringMagazijnplaats> alleArtikelenInMagazijn, List<InkomendeLeveringsLijnMetStringMagazijnplaats> temp, int start, int index, int r){
        // Sorteer alle plaatsen die je kan gebruiken op rij en dan op rek
        alleArtikelenInMagazijn.sort(new Comparator<InkomendeLeveringsLijnMetStringMagazijnplaats>() {
            @Override
            public int compare(InkomendeLeveringsLijnMetStringMagazijnplaats o1, InkomendeLeveringsLijnMetStringMagazijnplaats o2) {

                if(o1.getMagazijnPlaats().charAt(0) == o2.getMagazijnPlaats().charAt(0)){
                    return Integer.parseInt(o1.getMagazijnPlaats().substring(1)) - Integer.parseInt(o2.getMagazijnPlaats().substring(1));
                }else{
                    return o1.getMagazijnPlaats().charAt(0) - o2.getMagazijnPlaats().charAt(0);
                }
            }
        });

        // Roep methode op om paden te berekenen
        kortstePad(alleArtikelenInMagazijn, temp, start,alleArtikelenInMagazijn.size() , index, r, leverbonLijnen );
        /*System.out.println("\u001B[46m=========DE ALGO DEBUG==========\u001B[0m");
        System.out.println(mogelijkHeden);
        System.out.println(mogelijkHedenMetLegePlaats);

        System.out.println("\u001B[46m=========DE ALGO DEBUG==========\u001B[0m");*/
        // Als je een pad hebt waarbij je geen lege plaatsen hebt gebruikt
        if(!mogelijkHeden.isEmpty()){
            // Vind wat de kortste afstand is van alle paden
            int kleinstePad = mogelijkHeden.keySet().stream().min(Integer::compareTo).get();
            // Geef het pad terug met de kortste afstand
            return mogelijkHeden.get(kleinstePad);

        }
        // Als er geen pad bestaat waar je enkel aanvult
        else if(!mogelijkHedenMetLegePlaats.isEmpty()){

            // Vind van elk pad hoeveel keer je hebt aangevuld, en neem daar de grootste van
            long maxAangevuldePlaatsen = mogelijkHedenMetLegePlaats.values().stream()
                    .mapToLong(list -> list.stream().filter(item -> item.getHoeveelheidOpMagazijnplaats() > 0).count())
                    .max()
                    .orElse(0);

            // Filter de opties met het meest aangevulde plaatsen
            List<Map.Entry<Integer, List<InkomendeLeveringsLijnMetStringMagazijnplaats>>> besteOpties =
                    mogelijkHedenMetLegePlaats.entrySet().stream()
                            .filter(entry -> entry.getValue().stream().filter(item -> item.getHoeveelheidOpMagazijnplaats() > 0).count() == maxAangevuldePlaatsen)
                            .toList();

            if (!besteOpties.isEmpty()) {
                // Als er meerdere opties zijn met hetzelfde aantal aanvullingen, kies de kortste
                Map.Entry<Integer, List<InkomendeLeveringsLijnMetStringMagazijnplaats>> besteOptie =
                        besteOpties.stream()
                                .min(Map.Entry.comparingByKey())
                                .orElse(null);
                return besteOptie.getValue();
            }
        }
        // Als er geen pad mogelijk is
        return new ArrayList<>();
    }
}
