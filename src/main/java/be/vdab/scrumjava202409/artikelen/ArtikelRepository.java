package be.vdab.scrumjava202409.artikelen;

import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ArtikelRepository {
    private JdbcClient jdbcClient;

    public ArtikelRepository(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    public int getGewichtArtikel(long artikelId) {
        String sql = """
                select artikelen.gewichtInGram from artikelen where artikelId = ?;
                """;

        return jdbcClient.sql(sql).param(artikelId).query(Integer.class).single();
    }

    public Artikel getArtikelById(long artikelId) {
        var sql = """
                select artikelId, ean, naam, beschrijving, prijs, gewichtInGram, bestelpeil, voorraad, minimumVoorraad,
                maximumVoorraad, levertijd, aantalBesteldLeverancier, maxAantalInMagazijnPlaats, leveranciersId
                from Artikelen
                where artikelId = ?
                """;
        return jdbcClient.sql(sql).param(artikelId).query(Artikel.class).single();
    }

    public void verlaagVoorraad(long artikelId, int aantal){
        String sql = """
                update artikelen set voorraad = voorraad - ? where artikelId = ?;
                """;
        jdbcClient.sql(sql).params(aantal, artikelId).update();
    }


    //LEV-5.3 verhogen voorraad van een artikel
    public void verhoogVoorraad(long artikelId, int aantal){
        String sql = """
                update artikelen
                set voorraad = voorraad + ?
                where artikelId = ?
                """;
        jdbcClient.sql(sql)
                .params(aantal, artikelId)
                .update();
    }

    //Deze methode is voor testen van het aanpassen van de voorraad in artikelen en magazijnplaatsen
    //Wordt gebruikt in uitgaandeLeveringService totdat ArtikelAantal wordt aangepast dat die een artikelId heeft
    //TLDR: geen nuttige functie
    public Artikel getArtikelByName(String naam){
        //TODO verwijder limit 1

        String sql = """
                
                select * from artikelen where naam = ? limit 1;
                """;
        return jdbcClient
                .sql(sql)
                .param(naam)
                .query(Artikel.class).single();
    }

    public List<Artikel> findByPartEanNummer(String eanNummer) {
        String sql = """
                select * from artikelen where ean like ?
                """;
        return jdbcClient.sql(sql).param("%" + eanNummer + "%").query(Artikel.class).list();
    }
}
