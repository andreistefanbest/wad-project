package wad.phone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wad.phone.entities.Phones;

import java.util.List;

@RestController
@RequestMapping("/phones/")
public class PhoneController {

    @Autowired
    private PhoneService phoneService;

    @GetMapping("getPhones")
    private List<Phones> getPhones() throws Exception {
        return phoneService.getPhones();
    }

}
