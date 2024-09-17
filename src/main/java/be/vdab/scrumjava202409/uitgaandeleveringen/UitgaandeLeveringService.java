package be.vdab.scrumjava202409.uitgaandeleveringen;

import org.springframework.stereotype.Service;

@Service
public class UitgaandeLeveringService {
    private UitgaandeLeveringRepository uitgaandeLeveringRepository;

    public UitgaandeLeveringService(UitgaandeLeveringRepository uitgaandeLeveringRepository){
        this.uitgaandeLeveringRepository = uitgaandeLeveringRepository;
    }
}
