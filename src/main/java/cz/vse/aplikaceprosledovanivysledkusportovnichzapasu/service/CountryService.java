package cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.service;


import cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.entity.Country;

import java.util.List;

public interface CountryService {
    void fillCountriesBasketball();
    void fillCountriesHockey();
    void fillCountriesFootball();
    void fillCountriesVolleyball();
    List<Country> findAllBySport(String sport);



}
