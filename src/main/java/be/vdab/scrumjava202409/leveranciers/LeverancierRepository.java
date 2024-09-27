package be.vdab.scrumjava202409.leveranciers;

import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class LeverancierRepository {
    private final JdbcClient jdbcClient;

    public LeverancierRepository(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    public long findLeveranciersIdByLeveranciersStukjeNaam(String stukjeNaam){
        String sql = """
                select leveranciersId
                from leveranciers
                where naam like ?
                """;
        return jdbcClient.sql(sql)
                .param("%" + stukjeNaam + "%")
                .query(Long.class)
                .single();
    }

    public long findLeverancierIdByNaam(String naam){
        String sql = """
                select leveranciersId
                from leveranciers
                where naam = ?
                """;
        return jdbcClient.sql(sql)
                .param(naam)
                .query(Long.class)
                .single();
    }

    public List <String> findLeverancierNaam(){
        String sql = """
                select naam from leveranciers""";
        return jdbcClient.sql(sql).query(String.class).list();

    }
}
