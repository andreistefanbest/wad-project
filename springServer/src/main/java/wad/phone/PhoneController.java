package wad.phone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import wad.phone.entities.Brands;
import wad.phone.entities.Phones;

import java.util.List;

@RestController
@RequestMapping("/phones/")
public class PhoneController {

    @Autowired
    private PhoneService phoneService;

    @GetMapping("getPhones")
    private List<Phones> getPhones() {
        return phoneService.getPhones();
    }

    @PostMapping("phone")
    public Phones add(@RequestBody Phones p) {
        return phoneService.add(p);
    }

    @PutMapping("phone")
    public Phones update(@RequestBody Phones p) {
        return phoneService.update(p);
    }

    @DeleteMapping("phone")
    public void delete(@RequestParam Integer id) {
        phoneService.delete(id);
    }

    @GetMapping("brands")
    public List<Brands> getBrands() {
        return phoneService.getBrands();
    }
}
