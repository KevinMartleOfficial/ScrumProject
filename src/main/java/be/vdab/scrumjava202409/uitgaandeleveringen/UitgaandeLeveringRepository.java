package be.vdab.scrumjava202409.uitgaandeleveringen;

import be.vdab.scrumjava202409.bestellingen.Bestelling;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public class UitgaandeLeveringRepository {

    private JdbcClient jdbcClient;

    public UitgaandeLeveringRepository(JdbcClient jdbcClient){
        this.jdbcClient = jdbcClient;
    }

    public long addUitgaandeLevering(Bestelling bestelling){
        GeneratedKeyHolder generatedKeyHolder = new GeneratedKeyHolder();
        String sql = """
                insert into uitgaandeleveringen(bestelId, vertrekDatum, aankomstDatum, klantId, uitgaandeLeveringsStatusId) 
                values (?, ?, ? ,?, 1);
                """;
        jdbcClient.sql(sql)
                .params(bestelling.getBestelId(), LocalDateTime.now(),null, bestelling.getKlantId())
                .update(generatedKeyHolder);
        return generatedKeyHolder.getKey().longValue();
    }

}
