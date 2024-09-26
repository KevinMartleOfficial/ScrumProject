package be.vdab.scrumjava202409.magazijnplaatsen;

import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MagazijnPlaatsRepository {
    private final JdbcClient jdbcClient;

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

    public List<MagazijnPlaats> findMagazijnplaatsByArtikelId(long artikelId) {
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


    //Alle lege plaatsen opvragen
    public List<MagazijnPlaats> findMagazijnplaatsByNull(){
        String sql = """
                select magazijnPlaatsId,
                if(artikelId is null, 0, artikelId) as artikelId, rij, rek, aantal
                                from MagazijnPlaatsen
                                where artikelId is null
                                order by rij, rek
                """;
        return jdbcClient.sql(sql)
                .query(MagazijnPlaats.class)
                .list();
    }

    public long findMagazijnpaatsIdByMagazijnplaatsRijEnRek(String rij, int rek) {
        String sql = """
                select magazijnplaatsen.magazijnPlaatsId from magazijnplaatsen where rij = ? and rek = ?;
                """;

        return jdbcClient.sql(sql).params(rij, rek).query(Long.class).single();
    }
    //nodig voor LEV-4.1
    public MagazijnPlaats findByMagazijnPlaatsId(long magazijnPlaatsId){
        String sql = """
                select magazijnPlaatsId, artikelId, rij, rek, aantal
                from magazijnplaatsen
                where magazijnplaatsId = ?
                """;
        return jdbcClient.sql(sql)
                .param(magazijnPlaatsId)
                .query(MagazijnPlaats.class)
                .single();

    }

    
}
