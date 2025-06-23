package dev.samuel.yugigo.services;

import dev.samuel.yugigo.model.Card;
import dev.samuel.yugigo.repositories.CardRepository;
import dev.samuel.yugigo.services.response.APICard;
import dev.samuel.yugigo.services.response.CardAPIResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class CardService {

    private final String BASE_URL = "https://db.ygoprodeck.com/api/v7/cardinfo.php?name=";
    private final CardRepository repository;
    private final RestTemplate restTemplate;

    public CardService(RestTemplate restTemplate, CardRepository repository) {
        this.restTemplate = restTemplate;
        this.repository = repository;
    }

    public List<Card> findAllCards() {
        return repository.findAll();
    }

    public Card findCard(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Card storeCard(String name) {
        String url = BASE_URL + name;
        Card card = getCardDataByName(url);
        if (card != null) {
            repository.save(card);
            return card;
        }
        return null;
    }

    public void deleteCard(Long id) {
        repository.deleteById(id);
    }

    // utiliza a classe CardApiResponse p/ armazenar e tratar todos os dados recebidos na array data do objecto JSON
    // da API pública. APICard é utilizada p/ filtrar apenas os dados relevantes. Por fim, Card é a classe específica
    // p/ armazenar no banco de dados.
    private Card getCardDataByName(String url) {
        CardAPIResponse response = restTemplate.getForObject(url, CardAPIResponse.class);
        if (response != null) {
            APICard apiCard = response.getData().getFirst();
            Card card = new Card();
            card.setCardId(apiCard.getId());
            card.setName(apiCard.getName());
            card.setDescription(apiCard.getDesc());
            card.setAttribute(apiCard.getAttribute());
            card.setRace(apiCard.getRace());
            card.setAtk(apiCard.getAtk());
            card.setDef(apiCard.getDef());
            card.setLvl(apiCard.getLevel());
            card.setImg(apiCard.getImageUrl());

            return card;
        }

        return null;
    }
}
