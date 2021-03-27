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

    @GetMapping("getPhones")
    private List<PhoneDTO> getPhones() {
        return phoneService.getPhones();
    }

    @PostMapping("phone")
    public PhoneDTO add(@RequestBody PhoneDTO p) {
        return phoneService.add(p);
    }

    @PutMapping("phone")
    public PhoneDTO update(@RequestBody PhoneDTO p) {
        return phoneService.update(p);
    }

    @DeleteMapping("phone")
    public void delete(@RequestParam Integer id) {
        phoneService.delete(id);
    }

    @GetMapping("brands")
    public List<Brand> getBrands() {
        return phoneService.getBrands();
    }
}
