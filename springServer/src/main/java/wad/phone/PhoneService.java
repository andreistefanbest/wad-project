package wad.phone;

import wad.phone.entities.Brands;
import wad.phone.entities.PhoneTypes;
import wad.phone.entities.Phones;
import wad.phone.entities.Specs;

import java.util.List;

public interface PhoneService {

    List<Brands> getBrands() throws Exception;
    List<Phones> getPhones() throws Exception;
    List<PhoneTypes> getPhoneTypes() throws Exception;
    List<Specs> getSpecs() throws Exception;

}
