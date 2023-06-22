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

/**
 * Trieda CountryServiceImpl - implementuje rozhranie CountryService a poskytuje konkrétnu implementáciu metód pre prácu s krajinami.
 *
 * @author Štěpán Beran, Zuzana Hadzimová, Sabína Hrabáriková, Julie Sanetrníková, Adam Škarvada
 * @version LS 2022/2023
 */

@Service
public class CountryServiceImpl implements CountryService {
    ApiSports apiSports = ApiSports.getInstance();
    @Autowired
    private CountryRepository countryRepository;

    /**
     * Implementácia metódy z CountryService pre plnenie krajín pre basketbal pomocou API volania.
     */

    @Override
    public void fillCountriesBasketball() {
        JSONArray zeme = apiSports.basketbalZeme().getJSONArray("response");
        zeme.forEach(o -> {
            if (!countryRepository.findByExternalIdAndSport(((JSONObject) o).getInt("id"), "Basketball").isPresent()) {
                pridatZemi((JSONObject) o, "Basketball");
            }
        });
    }

    /**
     * Implementácia metódy z CountryService pre plnenie krajín pre hokej pomocou API volania.
     */

    @Override
    public void fillCountriesHockey() {
        JSONArray zemeHokej = apiSports.hokejZeme().getJSONArray("response");
        zemeHokej.forEach(o -> {
            if (!countryRepository.findByExternalIdAndSport(((JSONObject) o).getInt("id"), "Hockey").isPresent()) {
                pridatZemi((JSONObject) o, "Hockey");
            }
        });
    }

    /**
     * Implementácia metódy z CountryService pre plnenie krajín pre futbal pomocou API volania.
     */

    @Override
    public void fillCountriesFootball() {
        JSONArray zemeFotbal = apiSports.fotbalZeme().getJSONArray("response");
        zemeFotbal.forEach(o -> {
            if (!countryRepository.findCountryByNameAndSport(((JSONObject) o).getString("name"), "Football").isPresent())
                ;
            {
                pridatZemiFotbal((JSONObject) o, "Football");
            }
        });
    }

    /**
     * Implementácia metódy z CountryService pre plnenie krajín pre volejbal pomocou API volania.
     */

    @Override
    public void fillCountriesVolleyball() {
        JSONArray zemeVolleyball = apiSports.volejbalZeme().getJSONArray("response");
        zemeVolleyball.forEach(o -> {
            if (!countryRepository.findByExternalIdAndSport(((JSONObject) o).getInt("id"), "Volleyball").isPresent()) {
                pridatZemi((JSONObject) o, "Volleyball");
            }
        });
    }

    /**
     * Implementácia listu z CountryService pre získanie všetkých krajín pre zvolený šport.
     * @param sport
     * @return list krajín
     */

    @Override
    public List<Country> findAllBySport(String sport) {
        return countryRepository.findBySport(sport);
}

    /**
     * Metóda pre pridanie novej krajiny do databázy.
     * @param o
     * @param sport
     */


    private void pridatZemi(JSONObject o, String sport) {
        Country country = new Country();
        country.setExternalId(o.getInt("id"));
        country.setName(o.getString("name"));
        country.setFlag(o.get("flag").toString());
        country.setSport(sport);
        countryRepository.save(country);
    }

    /**
     * Metóda pre pridanie novej krajiny do databázy pri futbale.
     * @param o
     * @param sport
     */

    private void pridatZemiFotbal(JSONObject o, String sport) {
        Country country = new Country();
        country.setName(o.getString("name"));
        country.setFlag(o.get("flag").toString());
        country.setSport(sport);
        countryRepository.save(country);
    }

}



