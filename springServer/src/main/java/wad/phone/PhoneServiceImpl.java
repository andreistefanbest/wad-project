package wad.phone;

import org.springframework.beans.factory.annotation.Autowired;
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
}
