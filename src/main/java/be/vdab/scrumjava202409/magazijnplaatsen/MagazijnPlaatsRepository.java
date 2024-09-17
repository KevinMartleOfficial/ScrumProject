package be.vdab.scrumjava202409.magazijnplaatsen;

import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

@Repository
public class MagazijnPlaatsRepository {

    private JdbcClient jdbcClient;

    public MagazijnPlaatsRepository(JdbcClient jdbcClient){
        this.jdbcClient = jdbcClient;
    }

    public void haalArtikelUitMagazijnPlaats(int aantal, String rij, int rek){
        String sql = """
                update magazijnplaatsen set
                        aantal=
                        if(aantal - ? > 0, aantal - ?, 0),
                        artikelId=if(aantal - ? > 0, artikelId, null)
                        where rij = ? and rek = ?;
                """;
        jdbcClient.sql(sql).params(aantal, aantal, aantal, rij, rek).update();
    }
}
