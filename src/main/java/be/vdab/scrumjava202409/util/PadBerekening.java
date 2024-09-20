package be.vdab.scrumjava202409.util;

import be.vdab.scrumjava202409.bestellijnen.Bestellijn;
import be.vdab.scrumjava202409.bestellingen.BestelIdArtikelIdNaamAantalMagazijnplaats;

import java.util.*;

public class PadBerekening {

    private Map<Integer, List<BestelIdArtikelIdNaamAantalMagazijnplaats>> mogelijkHeden = new HashMap<>();
    private List<Bestellijn> artikelen;

    public PadBerekening(List<Bestellijn> artikelen){
        this.artikelen = artikelen.stream().map(Bestellijn::new).toList();
    }


    private int berekenLengtePad1Artikel(String beginPositie, String eindPositie){
        if (eindPositie.charAt(0) == beginPositie.charAt(0)) {

            return Math.abs(Integer.parseInt(eindPositie.substring(1))) - ((Integer.parseInt(beginPositie.substring(1))));
        }
        //System.out.println("????????????????????????????");
        //System.out.println(((int) (eindPositie.charAt(0)) - beginPositie.charAt(0)) + ((Integer.parseInt(eindPositie.substring(1))) + ((Integer.parseInt(beginPositie.substring(1))))));
        return ((int) (eindPositie.charAt(0)) - beginPositie.charAt(0)) + ((Integer.parseInt(eindPositie.substring(1))) + ((Integer.parseInt(beginPositie.substring(1)))));
    }

    private int berekenVolledigPad(List<BestelIdArtikelIdNaamAantalMagazijnplaats> mogelijkheid){

        if(mogelijkheid.size() > 0){

            int totLengte = berekenLengtePad1Artikel("A0", mogelijkheid.getLast().magazijnPlaats());
            totLengte += berekenLengtePad1Artikel("A0", mogelijkheid.getFirst().magazijnPlaats());

            for(int i = 0; i < mogelijkheid.size() - 1; i++){

                totLengte += berekenLengtePad1Artikel(mogelijkheid.get(i).magazijnPlaats(), mogelijkheid.get(i+1).magazijnPlaats());
            }

            return totLengte;
        }
        return Integer.MAX_VALUE;
    }

    public void kortstePad(List<BestelIdArtikelIdNaamAantalMagazijnplaats> alleArtikelenInMagazijnOpBestelling, List<BestelIdArtikelIdNaamAantalMagazijnplaats> temp, int start, int index, int r, List<Bestellijn> magazijn){


        alleArtikelenInMagazijnOpBestelling.sort(new Comparator<BestelIdArtikelIdNaamAantalMagazijnplaats>() {
            @Override
            public int compare(BestelIdArtikelIdNaamAantalMagazijnplaats o1, BestelIdArtikelIdNaamAantalMagazijnplaats o2) {

                if(o1.magazijnPlaats().charAt(0) == o2.magazijnPlaats().charAt(0)){
                    return Integer.parseInt(o1.magazijnPlaats().substring(1)) - Integer.parseInt(o2.magazijnPlaats().substring(1));
                }else{
                    return o1.magazijnPlaats().charAt(0) - o2.magazijnPlaats().charAt(0);
                }
            }
        });

        if(index == r){

                int lengteVolledigPad = berekenVolledigPad(temp);

                if(!mogelijkHeden.containsKey(lengteVolledigPad)){
                    mogelijkHeden.put(lengteVolledigPad, temp);
                }

                return;
        }
        int end = alleArtikelenInMagazijnOpBestelling.size();
        for(int i = start; (i < end) && (end - i + 1 >= r - index); i++){
            List<Bestellijn> tempMagazijn = new ArrayList<>(magazijn.stream().map(Bestellijn::new).toList());
            List<BestelIdArtikelIdNaamAantalMagazijnplaats> nieuweTemp = new ArrayList<>(temp.stream().map(BestelIdArtikelIdNaamAantalMagazijnplaats::new).toList());
            BestelIdArtikelIdNaamAantalMagazijnplaats huidigArtikel = alleArtikelenInMagazijnOpBestelling.get(i);

            Bestellijn bestellijn = tempMagazijn.stream()
                    .filter(bestellijn1 -> bestellijn1.getArtikelId() == huidigArtikel.artikelId())
                    .toList()
                    .getFirst();

            BestelIdArtikelIdNaamAantalMagazijnplaats bewerkt;

            if(!(bestellijn.getAantalBesteld() < 1)){

                if(bestellijn.getAantalBesteld() > huidigArtikel.hoeveelheidOpMagazijnplaats()){
                    r++;
                    bewerkt = new BestelIdArtikelIdNaamAantalMagazijnplaats(huidigArtikel.bestelId(), huidigArtikel.artikelId(), huidigArtikel.artikelNaam(), huidigArtikel.hoeveelheidOpMagazijnplaats(), huidigArtikel.magazijnPlaats(), huidigArtikel.hoeveelheidOpMagazijnplaats());
                }else{
                    bewerkt = new BestelIdArtikelIdNaamAantalMagazijnplaats(huidigArtikel.bestelId(), huidigArtikel.artikelId(), huidigArtikel.artikelNaam(), bestellijn.getAantalBesteld(), huidigArtikel.magazijnPlaats(), huidigArtikel.hoeveelheidOpMagazijnplaats());
                }
                bestellijn.verminderAantalBesteld(huidigArtikel.hoeveelheidOpMagazijnplaats());

                nieuweTemp.add(bewerkt);
                System.out.println(nieuweTemp);
                kortstePad(alleArtikelenInMagazijnOpBestelling, nieuweTemp, i+1, nieuweTemp.size() , r, tempMagazijn);
            }


        }

    }

    public List<BestelIdArtikelIdNaamAantalMagazijnplaats> kortstePad2(List<BestelIdArtikelIdNaamAantalMagazijnplaats> alleArtikelenInMagazijnOpBestelling, List<BestelIdArtikelIdNaamAantalMagazijnplaats> temp, int start, int index, int r){
        List<Bestellijn> magazijn = new ArrayList<>(artikelen);
        kortstePad(alleArtikelenInMagazijnOpBestelling, temp, start, index, r,magazijn );
        int kleinstePad = mogelijkHeden.keySet().stream().min(Integer::compareTo).get();
        System.out.println("================================================");
        System.out.println(mogelijkHeden);

        return mogelijkHeden.get(kleinstePad);
    }

}
