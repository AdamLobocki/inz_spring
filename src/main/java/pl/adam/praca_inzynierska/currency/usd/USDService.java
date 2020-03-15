package pl.adam.praca_inzynierska.currency.usd;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class USDService {
    private USDRepository usdRepository;
    private USDMapper usdMapper;

    @Autowired
    public USDService(USDRepository usdRepository, USDMapper usdMapper) {
        this.usdRepository = usdRepository;
        this.usdMapper = usdMapper;
    }

    public List<USDTO> findAllUSD() {
        return usdRepository.findAll().stream()
                .map(chf -> usdMapper.usdTOMapper(chf))
                .collect(Collectors.toList());
    }

    public USDTO findUSDById(Long id) {
        Optional<USD> usd = usdRepository.findById(id);
        if (!usd.isPresent()) {
            throw new IllegalArgumentException(); // TODO accountDoesNotExistException
        }

        return usdMapper.usdTOMapper(usd.get());
    }

    @Transactional
    public USDTO saveUSD(USDTO usdTO){
        USD entity = usdMapper.usdMapper(usdTO);
        entity = usdRepository.save(entity);
        return usdMapper.usdTOMapper(entity);
    }

    @Transactional
    public void deleteUSD(Long id) {
        usdRepository.deleteById(id);
    }

}
