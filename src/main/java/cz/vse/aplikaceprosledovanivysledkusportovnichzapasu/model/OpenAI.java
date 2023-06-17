package cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.model;

import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.mashape.unirest.http.HttpResponse;


public class OpenAI{

    private static final String API_KEY = "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";
    private static final String API_URL = "https://api.openai.com/v1/chat/completions";


    public static HttpResponse<String> authentikace(String oblibeneTymy, String oblibeneLigy){
        Unirest.setTimeouts(0, 0);
        try {
            HttpResponse<String> response =
                    Unirest.post(API_URL)
                    .header("Content-Type", "application/json")
                    .header("Authorization", "Bearer " + API_KEY)
                    .body("{\"messages/\": [{\"role\": \"system\", \"content\": \"You need to format your answer with no additional text just list the items and separate them only by ,\"}, {\"role\": \"user\", \"content\": \"Could zou show me other teams I could fllow when I follow "+ oblibeneTymy +" and " + oblibeneLigy + " ligy}],\"model\": \"gpt-3.5-turbo\"}")
                    .asString();
            return response;
        } catch (UnirestException e) {
            throw new RuntimeException(e);
        }
    }
}
