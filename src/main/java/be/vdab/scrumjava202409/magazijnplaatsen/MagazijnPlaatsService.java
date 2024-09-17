package be.vdab.scrumjava202409.magazijnplaatsen;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class MagazijnPlaatsService {
    private final MagazijnPlaatsRepository magazijnPlaatsRepository;

    public MagazijnPlaatsService(MagazijnPlaatsRepository magazijnPlaatsRepository) {
        this.magazijnPlaatsRepository = magazijnPlaatsRepository;
    }
}
