package wad.phone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wad.phone.entities.Brands;
import wad.phone.entities.PhoneTypes;
import wad.phone.entities.Phones;
import wad.phone.entities.Specs;
import wad.phone.repositories.BrandsRepository;
import wad.phone.repositories.PhoneTypesRepository;
import wad.phone.repositories.PhonesRepository;
import wad.phone.repositories.SpecsRepository;

import java.util.List;

@Service
public class PhoneService {

    @Autowired
    private BrandsRepository brandsRepository;

    @Autowired
    private PhonesRepository phonesRepository;

    @Autowired
    private PhoneTypesRepository phoneTypesRepository;

    @Autowired
    private SpecsRepository specsRepository;

    public List<Brands> getBrands() {
        return brandsRepository.findAll();
    }

    public List<Phones> getPhones() {
        return phonesRepository.findAll();
    }

    public List<PhoneTypes> getPhoneTypes() {
        return phoneTypesRepository.findAll();
    }

    public List<Specs> getSpecs() {
        return specsRepository.findAll();
    }

    public Phones add(Phones p) {
        return addUpdate(p);
    }

    public Phones update(Phones p) {
        return addUpdate(p);
    }

    public void delete(Integer id) {
        phonesRepository.deleteById(id);
    }

    private Phones addUpdate(Phones p) {
        p.setSpecsId(specsRepository.save(p.getSpecsId()));
        return phonesRepository.save(p);
    }
}
