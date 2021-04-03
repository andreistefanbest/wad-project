package wad.phone;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wad.phone.dto.PhoneDTO;
import wad.phone.entity.Brand;
import wad.phone.entity.Phone;
import wad.phone.entity.PhoneType;
import wad.phone.entity.Specs;
import wad.phone.repository.BrandsRepository;
import wad.phone.repository.PhoneTypesRepository;
import wad.phone.repository.PhonesRepository;
import wad.phone.repository.SpecsRepository;

import java.util.List;

import static java.util.stream.Collectors.toList;

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

    public List<Brand> getBrands() {
        return brandsRepository.findAll();
    }

    public List<PhoneDTO> getPhones() {
        return phonesRepository.findAll()
                .stream()
                .map(this::phoneDTOFromPhone)
                .collect(toList());
    }

    private PhoneDTO phoneDTOFromPhone(Phone phone) {
        Specs specs = specsRepository.findById(phone.getSpecsId()).orElseThrow();
        Brand brand = brandsRepository.findById(phone.getBrandId()).orElseThrow();
        PhoneType phoneType = phoneTypesRepository.findById(phone.getTypeId()).orElseThrow();

        PhoneDTO phoneDTO = new PhoneDTO();
        BeanUtils.copyProperties(phone, phoneDTO);
        phoneDTO.setSpecs(specs);
        phoneDTO.setBrand(brand);
        phoneDTO.setPhoneType(phoneType);

        return phoneDTO;
    }

    private Phone phoneFromPhoneDTO(PhoneDTO phoneDTO) {
        Phone phone = new Phone();
        BeanUtils.copyProperties(phoneDTO, phone);

        phone.setSpecsId(phoneDTO.getSpecs().getId());
        phone.setBrandId(phoneDTO.getBrand().getId());
        phone.setTypeId(phoneDTO.getPhoneType().getId());

        return phone;
    }


    public PhoneDTO add(PhoneDTO phoneDTO) {
        return addUpdate(phoneDTO);
    }

    public PhoneDTO update(PhoneDTO phoneDTO) {
        return addUpdate(phoneDTO);
    }

    public void delete(String id) {
        phonesRepository.deleteById(id);
    }

    private PhoneDTO addUpdate(PhoneDTO phoneDTO) {
        Specs newSpecs = specsRepository.save(phoneDTO.getSpecs());
        phoneDTO.setSpecs(newSpecs);

        Phone newPhone = phonesRepository.save(phoneFromPhoneDTO(phoneDTO));
        return phoneDTOFromPhone(newPhone);
    }

    public void addBrand(String brandName) {
        Brand newBrand = new Brand();
        newBrand.setName(brandName);

        brandsRepository.insert(newBrand);
    }

    public void addPhoneType(String type) {
        PhoneType phoneType = new PhoneType();
        phoneType.setName(type);

        phoneTypesRepository.insert(phoneType);
    }
}
