package wad.phone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import wad.phone.dto.PhoneDTO;
import wad.phone.entity.Brand;

import java.util.List;

@RestController
@RequestMapping("/phones/")
public class PhoneController {

    @Autowired
    private PhoneService phoneService;

    @GetMapping("phones")
    private List<PhoneDTO> getPhones() {
        return phoneService.getPhones();
    }

    @PostMapping("phone")
    public PhoneDTO add(@RequestBody PhoneDTO p) {
        return phoneService.add(p);
    }

    @PostMapping("brand")
    public void addBrand(@RequestBody String brandName) {
        phoneService.addBrand(brandName);
    }

    @PostMapping("type")
    public void addPhoneType(@RequestBody String type) {
        phoneService.addPhoneType(type);
    }

    @PutMapping("phone")
    public PhoneDTO update(@RequestBody PhoneDTO p) {
        return phoneService.update(p);
    }

    @DeleteMapping("phone")
    public void delete(@RequestParam String id) {
        phoneService.delete(id);
    }

    @GetMapping("brands")
    public List<Brand> getBrands() {
        return phoneService.getBrands();
    }
}
