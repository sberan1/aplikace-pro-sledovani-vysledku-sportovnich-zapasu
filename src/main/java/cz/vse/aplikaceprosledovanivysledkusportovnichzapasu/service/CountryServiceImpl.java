package cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.service;

import cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.entity.Country;
import cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.repository.CountryRepository;
import cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.model.ApiSports;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CountryServiceImpl implements CountryService{
    ApiSports apiSports = ApiSports.getInstance();
    @Autowired
    private CountryRepository countryRepository;


    public void fillCountries() {
        JSONArray zeme = apiSports.basketbalZeme().getJSONArray("response");
        zeme.forEach(o -> {
            if (!countryRepository.findByExternalIdAndSport(((JSONObject) o).getInt("id"), "Basketball").isPresent()) {
                pridatZemi((JSONObject) o);
            }
        });
    }

    private void pridatZemi(JSONObject o) {
        Country country = new Country();
        country.setExternalId(o.getInt("id"));
        country.setName(o.getString("name"));
        country.setFlag(o.get("flag").toString());
        country.setSport("Basketball");
        countryRepository.save(country);
    }
}
