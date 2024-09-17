package be.vdab.scrumjava202409.artikelen;

import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

@Repository
public class ArtikelRepository {
    private final JdbcClient jdbcClient;
    public ArtikelRepository(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    public Artikel findArtikelByArtikelId (long artikelId) {
        var sql = """
                select artikelId, ean, naam, beschrijving, prijs, gewichtInGram, bestelpeil,
                   voorraad, minimumVoorraad, maximumVoorraad, levertijd, aantalBesteldLeverancier,
                   maxAantalInMagazijnPlaats, leveranciersId
                from Artikelen
                where artikelId = ?
                """;
        return jdbcClient.sql(sql)
                .param(artikelId)
                .query(Artikel.class)
                .single();
    }
}