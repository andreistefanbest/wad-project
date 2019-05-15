package wad.phone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import wad.phone.entities.*;

import java.util.ArrayList;
import java.util.List;

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

    @Override
    public List<Brands> getBrands() throws Exception {
        var result = new ArrayList<Brands>();
        brandsRepository.findAll().forEach(result::add);

        return result;
    }

    @Override
    public List<Phones> getPhones() throws Exception {
        var result = new ArrayList<Phones>();
        phonesRepository.findAll().forEach(result::add);

        return result;
    }

    @Override
    public List<PhoneTypes> getPhoneTypes() throws Exception {
        var result = new ArrayList<PhoneTypes>();
        phoneTypesRepository.findAll().forEach(result::add);

        return result;
    }

    @Override
    public List<Specs> getSpecs() throws Exception {
        var result = new ArrayList<Specs>();
        specsRepository.findAll().forEach(result::add);

        return result;
    }

    @Override
    public Phones add(Phones p) throws Exception {
        p.setSpecsId(specsRepository.save(p.getSpecsId()));
        return phonesRepository.save(p);
    }

    @Override
    public Phones update(Phones p) throws Exception {
        p.setSpecsId(specsRepository.save(p.getSpecsId()));
        return phonesRepository.save(p);
    }

    @Override
    public void delete(Integer id) throws Exception {
        jdbcTemplate.update("DELETE FROM PHONES WHERE ID = " + id);
    }
}
