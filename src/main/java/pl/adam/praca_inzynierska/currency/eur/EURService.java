package pl.adam.praca_inzynierska.currency.eur;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.adam.praca_inzynierska.currency.abstractService;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class EURService extends abstractService {

    private final String EUR_LINK = "http://api.nbp.pl/api/exchangerates/rates/a/eur/?format=json";

    private EURRepository eurRepository;
    private EURMapper eurMapper;

    @Autowired
    public EURService(EURRepository eurRepository, EURMapper eurMapper) {
        this.eurRepository = eurRepository;
        this.eurMapper = eurMapper;
    }

    public List<EURTO> findAllEUR() {
        return eurRepository.findAll().stream()
                .map(eur -> eurMapper.eurTOMapper(eur))
                .collect(Collectors.toList());
    }

    public EURTO findEURById(Long id) {
        Optional<EUR> eur = eurRepository.findById(id);
        if (!eur.isPresent()) {
            throw new IllegalArgumentException(); // TODO accountDoesNotExistException
        }

        return eurMapper.eurTOMapper(eur.get());
    }

    public EURTO findLastRecord() {
        Long id = Long.valueOf(String.valueOf(findAllEUR().size()));

        return findEURById(id);
    }

    public EURTO eurTOObjectCreate() {
        EURTO eurTO = new EURTO();
        eurTO.setActualizationDate(super.getRatesGetter().date(EUR_LINK));
        eurTO.setRate(super.getRatesGetter().rate(EUR_LINK));

        return eurTO;
    }

    @Transactional
    public EURTO saveEUR(EURTO eurTO) {
        EUR entity = eurMapper.eurMapper(eurTO);
        entity = eurRepository.save(entity);
        return eurMapper.eurTOMapper(entity);
    }

    @Transactional
    public void deleteEUR(Long id) {
        eurRepository.deleteById(id);
    }
}