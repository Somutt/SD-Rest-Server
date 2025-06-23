package dev.samuel.yugigo.services;

import dev.samuel.yugigo.model.Card;
import dev.samuel.yugigo.model.Deck;
import dev.samuel.yugigo.repositories.CardRepository;
import dev.samuel.yugigo.repositories.DeckRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeckService {

    private final DeckRepository repository;
    private final CardRepository cardRepository;

    public DeckService(DeckRepository repository, CardRepository cardRepository) {
        this.repository = repository;
        this.cardRepository = cardRepository;
    }

    public List<Deck> findAll() {
        return repository.findAll();
    }

    public Deck findDeck(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Deck createDeck() {
        Deck deck = new Deck();
        return repository.save(deck);
    }

    public Deck attachCardToDeck(Long id, Long cardId) {
        Deck deck = findDeck(id);
        Card card = cardRepository.findById(cardId).orElse(null);
        deck.getCards().add(card);
        return repository.save(deck);
    }

    public Deck dettachCardFromDeck(Long id, Long cardId) {
        Deck deck = findDeck(id);
        Card card = cardRepository.findById(cardId).orElse(null);
        deck.getCards().remove(card);
        return repository.save(deck);
    }

    public void deleteDeck(Long id) {
        repository.deleteById(id);
    }
}
