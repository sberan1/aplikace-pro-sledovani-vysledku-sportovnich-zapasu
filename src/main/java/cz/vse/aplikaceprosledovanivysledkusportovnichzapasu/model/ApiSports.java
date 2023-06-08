package cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.model;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONObject;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class ApiSports {

    private static final String API_KEY = "0f96259c468ea2ffba1e545660b306d4";
    private static final String API_HOST_BASKETBAL = "https://v1.basketball.api-sports.io/";
    private static final String API_HOST_FOTBAL = "https://v3.football.api-sports.io/";
    private static final String API_HOST_HOKEJ = "https://v1.hockey.api-sports.io/";
    private static final String API_HOST_VOLEJBAL = "https://v1.volleyball.api-sports.io/";
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private ApiSports()  {
    }
    public static ApiSports getInstance() {
        return new ApiSports();
    }

    public JSONObject basketbalLigy(int idZeme){
        return new JSONObject(authentikace(API_HOST_BASKETBAL, "leagues?country_id="+idZeme).getBody());
    }
    public JSONObject basketbalLigy(){
        return new JSONObject(authentikace(API_HOST_BASKETBAL, "leagues").getBody());
    }
    public JSONObject basketbalZapasy(LocalDateTime datum){
        return new JSONObject(authentikace(API_HOST_BASKETBAL, "games?date=" + datum.format(formatter)).getBody());
    }
    public JSONObject basketbalTymy(int idLigy, String seasonString){
        return new JSONObject(authentikace(API_HOST_BASKETBAL, "teams?league="+ idLigy +"&season=" + seasonString).getBody());
    }
    public JSONObject basketbalZeme(){
        return new JSONObject(authentikace(API_HOST_BASKETBAL, "countries").getBody());
    }
    public JSONObject fotbalLigy(String idZeme){
        return new JSONObject(authentikace(API_HOST_FOTBAL, "leagues?country="+idZeme).getBody());
    }
    public JSONObject fotbalLigy(){
        return new JSONObject(authentikace(API_HOST_FOTBAL, "leagues").getBody());
    }
    public JSONObject fotbalZapasy(LocalDateTime datum){
        return new JSONObject(authentikace(API_HOST_FOTBAL, "fixtures?date=" + datum.format(formatter)).getBody());
    }
    public JSONObject fotbalTymy(int idLigy, String seasonString){
        return new JSONObject(authentikace(API_HOST_FOTBAL, "teams?league="+ idLigy +"&season=" + seasonString).getBody());
    }
    public JSONObject fotbalZeme(){
        return new JSONObject(authentikace(API_HOST_FOTBAL, "countries").getBody());
    }
    public JSONObject hokejLigy(int idZeme){
        return new JSONObject(authentikace(API_HOST_HOKEJ, "leagues?country="+idZeme).getBody());
    }
    public JSONObject hokejLigy(){
        return new JSONObject(authentikace(API_HOST_HOKEJ, "leagues").getBody());
    }
    public JSONObject hokejZapasy(LocalDateTime datum){
        return new JSONObject(authentikace(API_HOST_HOKEJ, "fixtures?date=" + datum.format(formatter)).getBody());
    }
    public JSONObject hokejTymy(int idLigy, String seasonString){
        return new JSONObject(authentikace(API_HOST_HOKEJ, "teams?league="+ idLigy +"&season=" + seasonString).getBody());
    }
    public JSONObject hokejZeme(){
        return new JSONObject(authentikace(API_HOST_HOKEJ, "countries").getBody());
    }
    public JSONObject volejbalLigy(int idZeme){
        return new JSONObject(authentikace(API_HOST_VOLEJBAL, "leagues?country="+idZeme).getBody());
    }
    public JSONObject volejbalLigy(){
        return new JSONObject(authentikace(API_HOST_VOLEJBAL, "leagues").getBody());
    }
    public JSONObject volejbalZapasy(LocalDateTime datum){
        return new JSONObject(authentikace(API_HOST_VOLEJBAL, "fixtures?date=" + datum.format(formatter)).getBody());
    }
    public JSONObject volejbalTymy(int idLigy, String seasonString){
        return new JSONObject(authentikace(API_HOST_VOLEJBAL, "teams?league="+ idLigy +"&season=" + seasonString).getBody());
    }
    public JSONObject volejbalZeme(){
        return new JSONObject(authentikace(API_HOST_VOLEJBAL, "countries").getBody());
    }













    public HttpResponse<String> authentikace(String host, String endpoint){
        Unirest.setTimeouts(0, 0);
        try {
            HttpResponse<String> response = Unirest.get(host+endpoint)
                    .header("x-rapidapi-key", API_KEY)
                    .header("x-rapidapi-host", "v1.basketball.api-sports.io")
                    .asString();
            return response;
        } catch (UnirestException e) {
            throw new RuntimeException(e);
        }
    }
}
