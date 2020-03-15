package pl.adam.praca_inzynierska.currency.chf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.adam.praca_inzynierska.currency.apiGetter.RatesGetter;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class CHFService {

    private RatesGetter ratesGetter;
    private final String CHF = "http://api.nbp.pl/api/exchangerates/rates/a/chf/?format=json";

    private CHFRepository chfRepository;
    private CHFMapper chfMapper;

    @Autowired
    public CHFService(CHFRepository chfRepository, CHFMapper chfMapper) {
        this.chfRepository = chfRepository;
        this.chfMapper = chfMapper;
    }

    public List<CHFTO> findAllCHF() {
        return chfRepository.findAll().stream()
                .map(chf -> chfMapper.chfTOMapper(chf))
                .collect(Collectors.toList());
    }

    public CHFTO findCHFById(Long id) {
        Optional<CHF> chf = chfRepository.findById(id);
        if (!chf.isPresent()) {
            throw new IllegalArgumentException(); // TODO accountDoesNotExistException
        }

        return chfMapper.chfTOMapper(chf.get());
    }

    @Transactional
    public CHFTO saveCHF(CHFTO chfTO){
        chfTO.setActualizationDate(ratesGetter.date(CHF));
        chfTO.setRate(ratesGetter.rate(CHF));
        CHF entity = chfMapper.chfMapper(chfTO);
        entity = chfRepository.save(entity);
        return chfMapper.chfTOMapper(entity);
    }

    @Transactional
    public void deleteCHF(Long id) {
        chfRepository.deleteById(id);
    }


}
