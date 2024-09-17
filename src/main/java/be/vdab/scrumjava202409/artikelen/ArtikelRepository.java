package be.vdab.scrumjava202409.artikelen;

import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

@Repository
public class ArtikelRepository {
    private JdbcClient jdbcClient;

    public ArtikelRepository(JdbcClient jdbcClient){
        this.jdbcClient = jdbcClient;
    }

    public int getGewichtArtikel(long artikelId){
        String sql = """
                select artikelen.gewichtInGram from artikelen where artikelId = ?;
                """;

        return jdbcClient.sql(sql).param(artikelId).query(Integer.class).single();
    }
}
