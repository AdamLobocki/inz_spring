package pl.adam.praca_inzynierska.currency.jpy;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.adam.praca_inzynierska.currency.abstractService;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class JPYService extends abstractService {

    private final String JPY_LINK = "http://api.nbp.pl/api/exchangerates/rates/a/jpy/?format=json";

    private JPYRepository jpyRepository;
    private JPYMapper jpyMapper;

    @Autowired
    public JPYService(JPYRepository jpyRepository, JPYMapper jpyMapper) {
        this.jpyRepository = jpyRepository;
        this.jpyMapper = jpyMapper;
    }

    public List<JPYTO> findAllJPY() {
        return jpyRepository.findAll().stream()
                .map(jpy -> jpyMapper.jpyTOMapper(jpy))
                .collect(Collectors.toList());
    }

    public JPYTO findJPYById(Long id) {
        Optional<JPY> jpy = jpyRepository.findById(id);
        if (!jpy.isPresent()) {
            throw new IllegalArgumentException(); // TODO accountDoesNotExistException
        }

        return jpyMapper.jpyTOMapper(jpy.get());
    }

    public JPYTO findLastRecord() {
        Long id = Long.valueOf(String.valueOf(findAllJPY().size()));

        return findJPYById(id);
    }

    public JPYTO jpyTOObjectCreate() {
        JPYTO gbpTO = new JPYTO();
        gbpTO.setActualizationDate(super.getRatesGetter().date(JPY_LINK));
        gbpTO.setRate(super.getRatesGetter().rate(JPY_LINK));

        return gbpTO;
    }


    @Transactional
    public JPYTO saveJPY(JPYTO chfTO){
        JPY entity = jpyMapper.jpyMapper(chfTO);
        entity = jpyRepository.save(entity);
        return jpyMapper.jpyTOMapper(entity);
    }

    @Transactional
    public void deleteJPY(Long id) {
        jpyRepository.deleteById(id);
    }
}