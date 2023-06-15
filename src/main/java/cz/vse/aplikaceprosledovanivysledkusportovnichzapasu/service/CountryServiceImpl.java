package cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.service;

import cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.entity.Country;
import cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.repository.CountryRepository;
import cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.model.ApiSports;
import lombok.Getter;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CountryServiceImpl implements CountryService{
    ApiSports apiSports = ApiSports.getInstance();
    @Autowired
    private CountryRepository countryRepository;

    @Override
    public void fillCountriesBasketball() {
        JSONArray zeme = apiSports.basketbalZeme().getJSONArray("response");
        zeme.forEach(o -> {
            if (!countryRepository.findByExternalIdAndSport(((JSONObject) o).getInt("id"), "Basketball").isPresent()) {
                pridatZemi((JSONObject) o, "Basketball");
            }
        });
    }

    @Override
    public void fillCountriesHockey() {
        JSONArray zemeHokej = apiSports.hokejZeme().getJSONArray("response");
        zemeHokej.forEach(o -> {
            if (!countryRepository.findByExternalIdAndSport(((JSONObject) o).getInt("id"), "Hockey").isPresent()) {
                pridatZemi((JSONObject) o, "Hockey");
            }
        });
    }

    @Override
    public void fillCountriesFootball() {
        JSONArray zemeFotbal = apiSports.fotbalZeme().getJSONArray("response");
        zemeFotbal.forEach(o -> {
            if (!countryRepository.findCountryByNameAndSport(((JSONObject) o).getString("name"), "Football").isPresent()); {
                pridatZemiFotbal((JSONObject) o, "Football");
            }
        });
    }

    @Override
    public List<Country> findAllBySport(String sport) {
        return countryRepository.findBySport(sport);
    }

    private void pridatZemi(JSONObject o, String sport) {
        Country country = new Country();
        country.setExternalId(o.getInt("id"));
        country.setName(o.getString("name"));
        country.setFlag(o.get("flag").toString());
        country.setSport(sport);
        countryRepository.save(country);
    }

    private void pridatZemiFotbal(JSONObject o, String sport) {
        Country country = new Country();
        country.setName(o.getString("name"));
        country.setFlag(o.get("flag").toString());
        country.setSport(sport);
        countryRepository.save(country);
    }

}



