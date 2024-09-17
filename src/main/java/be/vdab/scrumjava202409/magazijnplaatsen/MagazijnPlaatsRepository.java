package be.vdab.scrumjava202409.magazijnplaatsen;

import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MagazijnPlaatsRepository {
    private final JdbcClient jdbcClient;

    public MagazijnPlaatsRepository(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    List<MagazijnPlaats> findMagazijnplaatsByArtikelId(long artikelId) {
        var sql = """
                select magazijnPlaatsId, artikelId, rij, rek, aantal
                from MagazijnPlaatsen
                where artikelId = ?
                order by rij, rek
                """;
        return jdbcClient.sql(sql)
                .param(artikelId)
                .query(MagazijnPlaats.class)
                .list();
    }
}
