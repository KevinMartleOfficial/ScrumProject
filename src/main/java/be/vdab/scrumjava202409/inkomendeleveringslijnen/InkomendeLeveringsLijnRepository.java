package be.vdab.scrumjava202409.inkomendeleveringslijnen;

import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import java.util.List;

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

    //LEV-4.1 fetch method om alle inkomendeLeveringenLijnen te krijgen op basis van inkomendeLeveringsId
    public List<InkomendeLeveringsLijn> findAllInkomendeLeveringsLijnenByInkomendeLeveringsId(long id){
        String sql = """
                select inkomendeLeveringsId, artikelId, aantalGoedgekeurd, aantalTeruggestuurd, magazijnPlaatsId
                from inkomendeleveringslijnen
                where inkomendeLeveringsId = ?
                order by magazijnPlaatsId
                """;
        return jdbcClient.sql(sql)
                .param(id)
                .query(InkomendeLeveringsLijn.class)
                .list();
    }

}
