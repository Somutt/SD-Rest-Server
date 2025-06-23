package dev.samuel.yugigo.controllers;

import dev.samuel.yugigo.model.Deck;
import dev.samuel.yugigo.services.DeckService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/deck")
public class DeckController {
    private final DeckService service;

    public DeckController(DeckService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Deck>> index() {
        List<Deck> decks = service.findAll();
        return ResponseEntity.ok().body(decks);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Deck> show(@PathVariable Long id) {
        Deck deck = service.findDeck(id);
        if (deck != null) {
            return ResponseEntity.ok(deck);
        }
        return ResponseEntity.notFound().build();
    }

    // cria um deck vazio
    @PostMapping
    public ResponseEntity<Deck> create() {
        Deck deck = service.createDeck();
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(deck.getId()).toUri();
        return ResponseEntity.created(uri).body(deck);
    }

    // liga uma carta diretamente ao deck, /id é o id do deck e /card o id da carta
    @PostMapping("/{id}/{card}")
    public ResponseEntity<Deck> attach(@PathVariable Long id, @PathVariable Long card) {
        Deck deck = service.attachCardToDeck(id, card);
        if (deck != null) {
            return ResponseEntity.ok(deck);
        }
        return ResponseEntity.notFound().build();
    }

    // desliga uma carta diretamente do deck, /id é o id do deck e /card o id da carta
    @PutMapping("/{id}/{card}")
    public ResponseEntity<Deck> detach(@PathVariable Long id, @PathVariable Long card) {
        Deck deck = service.dettachCardFromDeck(id, card);
        if (deck != null) {
            return ResponseEntity.ok(deck);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        Deck deck = service.findDeck(id);
        if (deck != null) {
            service.deleteDeck(deck.getId());
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
