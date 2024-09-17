package be.vdab.scrumjava202409.magazijnplaatsen;

import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

@Repository
public class MagazijnPlaatsRepository {
    private final JdbcClient jdbcClient;

    public MagazijnPlaatsRepository(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }
}
