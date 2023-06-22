package cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.service;


import cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.entity.Country;

import java.util.List;

/**
 * Interface CountryService - rozhranie definuje metódy pre prácu s krajinami.
 *
 * @author Štepán Beran, Zuzana Hadzimová, Sabína Hrabáriková
 * @version LS 2022/2023
 */

public interface CountryService {
    void fillCountriesBasketball();
    void fillCountriesHockey();
    void fillCountriesFootball();
    void fillCountriesVolleyball();
    List<Country> findAllBySport(String sport);



}
