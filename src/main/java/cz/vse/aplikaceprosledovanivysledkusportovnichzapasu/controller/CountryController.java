package cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.controller;

import cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.entity.Country;
import cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/country")
public class CountryController {
    @Autowired
    CountryService countryService;

    @PostMapping("/fillCountriesBasketball")
    public void fillCountries() {
        countryService.fillCountriesBasketball();
    }

    @PostMapping("/fillCountriesHockey")
    public void fillCountriesHockey() {
        countryService.fillCountriesHockey();
    }

    @GetMapping("/getCountries")
    public List<Country> getCountries(@RequestParam String sport) {
        return countryService.findAllBySport(sport);
    }

}

