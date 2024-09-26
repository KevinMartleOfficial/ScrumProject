package be.vdab.scrumjava202409.inkomendeleveringslijnen;

import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

@Repository
public class InkomendeLeveringsLijnRepository {

    private JdbcClient jdbcClient;

    public InkomendeLeveringsLijnRepository(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    public void voegInkomendeLeveringsLijnToe(InkomendeLeveringsLijn inkomendeLeveringsLijn) {
        String sql = """
                insert into
                inkomendeleveringslijnen (inkomendeLeveringsId, artikelId, aantalGoedgekeurd, aantalTeruggestuurd, magazijnPlaatsId)
                values (?, ?, ?, ?, ?)
                """;

        jdbcClient.sql(sql)
                .params(inkomendeLeveringsLijn.getInkomendeLeveringsId(), inkomendeLeveringsLijn.getArtikelId(),
                        inkomendeLeveringsLijn.getAantalGoedgekeurd(),
                        inkomendeLeveringsLijn.getAantalTeruggestuurd(),
                        inkomendeLeveringsLijn.getMagazijnPlaatsId())
                .update();
    }

}
