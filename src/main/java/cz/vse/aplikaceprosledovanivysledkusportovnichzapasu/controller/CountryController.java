package cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.controller;

import cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.entity.Country;
import cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Trieda CountryController - slúži pre manipuláciu s databázou obsahujúcou informácie o jednotlivých krajinách.
 *
 * @author Štepán Beran, Zuzana Hadzimová, Sabína Hrabáriková
 * @version LS 2022/2023
 */

@RestController
@CrossOrigin
@RequestMapping(value = "/country")

public class CountryController {
    @Autowired
    CountryService countryService;

    /**
     * Metóda pre naplnenie databáze krajinami pri športe basketbal.
     */

    @PostMapping("/fillCountriesBasketball")
    public void fillCountries() {
        countryService.fillCountriesBasketball();
    }

    /**
     * Metóda pre naplnenie databáze krajinami pri športe hokej.
     */

    @PostMapping("/fillCountriesHockey")
    public void fillCountriesHockey() {
        countryService.fillCountriesHockey();
    }

    /**
     * Metóda pre naplnenie databáze krajinami pri športe futbal.
     */
    
    @PostMapping("/fillCountriesFootball")
    public void fillCountriesFootball() {
        countryService.fillCountriesFootball();
    }

    /**
     * Metóda pre naplnenie databáze krajinami pri športe volejbal.
     */

    @PostMapping("/fillCountriesVolleyball")
    public void fillCountriesVolleyball(){countryService.fillCountriesVolleyball();}

    /**
     * Metóda pre získanie krajín pre daný šport.
     * @return List krajín
     */

    @GetMapping("/getCountries")
    public List<Country> getCountries(@RequestParam String sport) {
        return countryService.findAllBySport(sport);
    }



}





