package projeto.SistemaAutoria.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.BodyInserters;

@Service
public class GoogleTranslateApiService {

    private final WebClient webClient;

    @Value("${translateApi}")
    private String translateKey;

    public GoogleTranslateApiService(WebClient webClient) {
        this.webClient = webClient;
    }

    public String traduzir(String textoOriginal) throws JsonProcessingException {
        String traducaoJson=  webClient.post()
                .uri("https://translation.googleapis.com/language/translate/v2?key="+ translateKey)
                .body(BodyInserters.fromFormData("q", textoOriginal)
                .with("source", "en")
                .with("target", "pt-br")
                .with("format", "text"))
                .retrieve()
                .bodyToMono(String.class).block();

        ObjectMapper mapper = new ObjectMapper();

        JsonNode json = mapper.readTree(traducaoJson);
        String txt = json
                .get("data")
                .get("translations")
                .get(0)
                .get("translatedText")
                .asText();
        return txt;
    }
}