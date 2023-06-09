package cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.service;

import cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.entity.Country;
import cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.repository.CountryRepository;
import cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.model.ApiSports;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryServiceImpl implements CountryService{
    ApiSports apiSports = ApiSports.getInstance();
    private final CountryRepository countryRepository;

    public CountryServiceImpl(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

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
}
