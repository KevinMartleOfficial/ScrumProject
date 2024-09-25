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
        //uw kolomnaam moet met hoofdletters, anders gaat het niet werken
        jdbcClient.sql(sql)
                .params(inkomendeLeveringsLijn.getInkomendeLeveringsId(), inkomendeLeveringsLijn.getArtikelId(),
                        inkomendeLeveringsLijn.getAantalGoedgekeurde() //de 'e' op het einde moet weg,
                        inkomendeLeveringsLijn.getAantalTeruggestuurd(),
                        inkomendeLeveringsLijn.getMagazijnPlaatsId())
                .update();
    }

}
