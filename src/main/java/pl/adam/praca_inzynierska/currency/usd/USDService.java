package pl.adam.praca_inzynierska.currency.usd;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.adam.praca_inzynierska.currency.abstractService;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class USDService extends abstractService {

    private final String USD_LINK = "http://api.nbp.pl/api/exchangerates/rates/a/usd/?format=json";

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

    public USDTO findLastRecord() {
        Long id = Long.valueOf(String.valueOf(findAllUSD().size()));

        return findUSDById(id);
    }

    public USDTO usdTOObjectCreate() {
        USDTO usdTO = new USDTO();
        usdTO.setActualizationDate(super.getRatesGetter().date(USD_LINK));
        usdTO.setRate(super.getRatesGetter().rate(USD_LINK));

        return usdTO;
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