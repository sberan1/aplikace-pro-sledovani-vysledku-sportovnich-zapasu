package cz.vse.aplikaceprosledovanivysledkusportovnichzapasu.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SearchBarDto {
    long id;
    String name;
    String logo;
}