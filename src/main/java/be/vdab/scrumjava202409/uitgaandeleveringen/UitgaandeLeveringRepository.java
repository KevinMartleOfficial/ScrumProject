package be.vdab.scrumjava202409.uitgaandeleveringen;

import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class UitgaandeLeveringRepository {

    private JdbcClient jdbcClient;

    public UitgaandeLeveringRepository(JdbcClient jdbcClient){
        this.jdbcClient = jdbcClient;
    }



}
