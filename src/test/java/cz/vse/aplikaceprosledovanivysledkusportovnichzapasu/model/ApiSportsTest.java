package cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.model;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

public class ApiSportsTest {
    ApiSports apiSports = ApiSports.getInstance();

    @Test
    public void basketbalLigy() throws IOException {
        File file = new File("src/main/resources/test/basketbalLigy.json");
        FileWriter fileWriter = new FileWriter(file);
        fileWriter.write(apiSports.basketbalLigy().toString());
        fileWriter.close();
    }

    @Test
    public void basketbalZapasy() throws IOException {
        File file = new File("src/main/resources/test/basketbalZapasy.json");
        FileWriter fileWriter = new FileWriter(file);
        fileWriter.write(apiSports.basketbalZapasy(LocalDateTime.now()).toString());
        fileWriter.close();
    }

    @Test
    public void basketbalTymy() throws IOException {
        File file = new File("src/main/resources/test/basketbalTymy.json");
        FileWriter fileWriter = new FileWriter(file);
        fileWriter.write(apiSports.basketbalTymy(228, "2021-2022").toString());
        fileWriter.close();
    }

    @Test
    public void basketbalZeme() throws IOException {
        File file = new File("src/main/resources/test/basketbalZeme.json");
        FileWriter fileWriter = new FileWriter(file);
        fileWriter.write(apiSports.basketbalZeme().toString());
        fileWriter.close();
    }

    @Test
    public void basketbalLigyCZ() throws IOException {
        File file = new File("src/main/resources/test/basketbalLigy.json");
        FileWriter fileWriter = new FileWriter(file);
        fileWriter.write(apiSports.basketbalLigy(12).toString());
        fileWriter.close();
    }

    @Test
    public void fotbalLigyTest() throws IOException {
        File file = new File("src/main/resources/test/fotbalLigy.json");
        FileWriter fileWriter = new FileWriter(file);
        fileWriter.write(apiSports.fotbalLigy().toString());
        fileWriter.close();
    }

    @Test
    public void fotbalZapasyTest() throws IOException {
        File file = new File("src/main/resources/test/fotbalZapasy.json");
        FileWriter fileWriter = new FileWriter(file);
        fileWriter.write(apiSports.fotbalZapasy(LocalDateTime.now()).toString());
        fileWriter.close();
    }

    @Test
    public void fotbalTymyTest() throws IOException {
        File file = new File("src/main/resources/test/fotbalTymy.json");
        FileWriter fileWriter = new FileWriter(file);
        fileWriter.write(apiSports.fotbalTymy(338, "2022").toString());
        fileWriter.close();
    }

    @Test
    public void fotbalZemeTest() throws IOException {
        File file = new File("src/main/resources/test/fotbalZeme.json");
        FileWriter fileWriter = new FileWriter(file);
        fileWriter.write(apiSports.fotbalZeme().toString());
        fileWriter.close();
    }

    @Test
    public void volejbalLigyTest() throws IOException {
        File file = new File("src/main/resources/test/volejbalLigy.json");
        FileWriter fileWriter = new FileWriter(file);
        fileWriter.write(apiSports.volejbalLigy().toString());
        fileWriter.close();
    }

    @Test
    public void volejbalZapasyTest() throws IOException {
        File file = new File("src/main/resources/test/volejbalZapasy.json");
        FileWriter fileWriter = new FileWriter(file);
        fileWriter.write(apiSports.volejbalZapasy(LocalDateTime.now()).toString());
        fileWriter.close();
    }

    @Test
    public void volejbalTymyTest() throws IOException {
        File file = new File("src/main/resources/test/volejbalTymy.json");
        FileWriter fileWriter = new FileWriter(file);
        fileWriter.write(apiSports.volejbalTymy(1, "2021-2022").toString());
        fileWriter.close();
    }

    @Test
    public void volejbalZemeTest() throws IOException {
        File file = new File("src/main/resources/test/volejbalZeme.json");
        FileWriter fileWriter = new FileWriter(file);
        fileWriter.write(apiSports.volejbalZeme().toString());
        fileWriter.close();
    }

    @Test
    public void hokejLigyTest() throws IOException {
        File file = new File("src/main/resources/test/hokejLigy.json");
        FileWriter fileWriter = new FileWriter(file);
        fileWriter.write(apiSports.hokejLigy().toString());
        fileWriter.close();
    }

    @Test
    public void hokejZapasyTest() throws IOException {
        File file = new File("src/main/resources/test/hokejZapasy.json");
        FileWriter fileWriter = new FileWriter(file);
        fileWriter.write(apiSports.hokejZapasy(LocalDateTime.now()).toString());
        fileWriter.close();
    }

    @Test
    public void hokejTymyTest() throws IOException {
        File file = new File("src/main/resources/test/hokejTymy.json");
        FileWriter fileWriter = new FileWriter(file);
        fileWriter.write(apiSports.hokejTymy(1, "2021-2022").toString());
        fileWriter.close();
    }
}
