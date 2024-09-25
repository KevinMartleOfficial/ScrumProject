package be.vdab.scrumjava202409.inkomendeLeveringen;

import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class InkomendeLeveringRepository {
    private final JdbcClient jdbcClient;

    public InkomendeLeveringRepository(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    long create(InkomendeLevering inkomendeLevering) {
        var sql = """
                insert
                into InkomendeLeveringen
                (inkomendeLeveringsId, leveranciersId, leveringsbonNummer, leveringsbondatum, leverDatum, ontvangerPersoneelslidId)
                values(?, ?, ?, ?, ?, ?)
                """;
        var keyHolder = new GeneratedKeyHolder();
        jdbcClient.sql(sql)
                .params(inkomendeLevering.getInkomendeLeveringsId(), inkomendeLevering.getLeveranciersId(),
                        inkomendeLevering.getLeveringsbonNummer(), inkomendeLevering.getLeveringsbondatum())
                .update(keyHolder);
        return keyHolder.getKey().longValue();
    }
}
