package wad.phone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import wad.phone.entities.*;
import wad.utils.GenericService;

import java.util.List;
import java.util.Spliterator;

import static java.util.stream.Collectors.toList;
import static java.util.stream.StreamSupport.stream;

@Service
public class PhoneServiceImpl implements PhoneService {

    @Autowired
    private BrandsRepository brandsRepository;

    @Autowired
    private PhonesRepository phonesRepository;

    @Autowired
    private PhoneTypesRepository phoneTypesRepository;

    @Autowired
    private SpecsRepository specsRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private GenericService genericService;

    @Override
    public List<Brands> getBrands() throws Exception {
        return genericService.fetchEntities(brandsRepository);
    }

    @Override
    public List<Phones> getPhones() throws Exception {
        return genericService.fetchEntities(phonesRepository);
    }

    @Override
    public List<PhoneTypes> getPhoneTypes() throws Exception {
        return genericService.fetchEntities(phoneTypesRepository);
    }

    @Override
    public List<Specs> getSpecs() throws Exception {
        return genericService.fetchEntities(specsRepository);
    }

    @Override
    public Phones add(Phones p) throws Exception {
        return addUpdate(p);
    }

    @Override
    public Phones update(Phones p) throws Exception {
        return addUpdate(p);
    }

    @Override
    public void delete(Integer id) throws Exception {
        jdbcTemplate.update("DELETE FROM PHONES WHERE ID = " + id);
    }

    private Phones addUpdate(Phones p) {
        p.setSpecsId(specsRepository.save(p.getSpecsId()));
        return phonesRepository.save(p);
    }
}
