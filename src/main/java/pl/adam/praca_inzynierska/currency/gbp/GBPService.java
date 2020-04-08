package pl.adam.praca_inzynierska.currency.gbp;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.adam.praca_inzynierska.currency.abstractService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class GBPService extends abstractService {

    private final String GBP_LINK = "http://api.nbp.pl/api/exchangerates/rates/a/gbp/?format=json";

    private GBPRepository gbpRepository;
    private GBPMapper gbpMapper;

    @Autowired
    public GBPService(GBPRepository gbpRepository, GBPMapper gbpMapper) {
        this.gbpRepository = gbpRepository;
        this.gbpMapper = gbpMapper;
    }

    public List<GBPTO> findAllGBP() {
        return gbpRepository.findAll().stream()
                .map(gbp -> gbpMapper.gbpTOMapper(gbp))
                .collect(Collectors.toList());
    }

    public GBPTO findGBPById(Long id) {
        Optional<GBP> gbp = gbpRepository.findById(id);
        if (!gbp.isPresent()) {
            throw new IllegalArgumentException(); // TODO accountDoesNotExistException
        }

        return gbpMapper.gbpTOMapper(gbp.get());
    }

    public GBPTO findLastRecord() {
        Long id = Long.valueOf(String.valueOf(findAllGBP().size()));

        return findGBPById(id);
    }

    public GBPTO gbpTOObjectCreate() {
        GBPTO gbpTO = new GBPTO();
        gbpTO.setActualizationDate(super.getRatesGetter().date(GBP_LINK));
        gbpTO.setRate(super.getRatesGetter().rate(GBP_LINK));

        return gbpTO;
    }

    @Transactional
    public GBPTO saveGBP(GBPTO gbpTO){
        GBP entity = gbpMapper.gbpMapper(gbpTO);
        entity = gbpRepository.save(entity);
        return gbpMapper.gbpTOMapper(entity);
    }

    @Transactional
    public void deleteGBP(Long id) {
        gbpRepository.deleteById(id);
    }
}