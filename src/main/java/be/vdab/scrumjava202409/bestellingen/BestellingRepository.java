package be.vdab.scrumjava202409.bestellingen;

import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

@Repository
public class BestellingRepository {
    private final JdbcClient jdbcClient;
    public BestellingRepository(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    public Bestelling findEersteBestellingMetStatusKlaarmaken() {
        var sql = """
                select bestelId, besteldatum, klantId, betaald, betalingscode,
                    betaalwijzeId, annulatie, annulatiedatum, terugbetalingscode,
                    bestellingsStatusId, actiecodeGebruikt, bedrijfsnaam
                from Bestellingen
                where bestellingsStatusId = 4
                order by besteldatum, bestelId
                limit 1
                """;
        return jdbcClient.sql(sql)
                .query(Bestelling.class)
                .single();
    }

}