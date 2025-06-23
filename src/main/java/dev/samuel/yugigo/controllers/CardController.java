package dev.samuel.yugigo.controllers;

import dev.samuel.yugigo.model.Card;
import dev.samuel.yugigo.protobuf.CardProtos;
import dev.samuel.yugigo.services.CardService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/cards")
public class CardController {

    private final CardService service;

    public CardController(CardService service) {
        this.service = service;
    }

    // retorna o binário na rota cards/proto
    @GetMapping(value = "/proto", produces = "application/x-protobuf")
    public ResponseEntity<CardProtos.CardList> getProtoCards() {
        List<Card> cards = service.findAllCards();
        // precisa utilizar a classe já previamente compilada pelo Maven, em uma instalação nova é preciso compilar
        // inicialmente e fazer a IDE reconhecer a rota compilada como uma origem de código
        List<CardProtos.Card> protoCards = cards.stream().map(this::toProto).toList();
        CardProtos.CardList response = CardProtos.CardList.newBuilder().addAllCards(protoCards).build();

        return ResponseEntity.ok().body(response);
    }

    // retorna todas as cartas em formato JSON
    @GetMapping(produces = "application/json")
    public ResponseEntity<List<Card>> getJsonCards() {
        List<Card> cards = service.findAllCards();
        return ResponseEntity.ok().body(cards);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Card> show(@PathVariable Long id) {
        Card card = service.findCard(id);
        if (card != null) {
            return ResponseEntity.ok().body(card);
        }
        return ResponseEntity.notFound().build();
    }

    // utiliza o nome no parâmetro para buscar uma carta específica na API pública de YuGiOh
    @PostMapping("/{name}")
    public ResponseEntity<Card> store(@PathVariable String name) {
        Card card = service.storeCard(name);
        if (card != null) {
            URI uri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/{id}").buildAndExpand(card.getId()).toUri();
            return ResponseEntity.created(uri).body(card);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> destroy(@PathVariable Long id) {
        Card card = service.findCard(id);
        if (card != null) {
            service.deleteCard(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    // insere o valor no builder de um formato Proto, possui um valor vazio para construir com mais segurança caso não haja
    // valor recebido pela função.
    private CardProtos.Card toProto(Card c) {
        CardProtos.Card.Builder builder = CardProtos.Card.newBuilder()
                .setId(c.getId() != null ? c.getId() : 0)
                .setCardId(c.getCardId() != null ? c.getCardId() : 0)
                .setName(c.getName() != null ? c.getName() : "")
                .setDescription(c.getDescription() != null ? c.getDescription() : "")
                .setAttribute(c.getAttribute() != null ? c.getAttribute() : "")
                .setRace(c.getRace() != null ? c.getRace() : "")
                .setImg(c.getImg() != null ? c.getImg() : "");

        if (c.getAtk() != null) builder.setAtk(c.getAtk());
        if (c.getDef() != null) builder.setDef(c.getDef());
        if (c.getLvl() != null) builder.setLvl(c.getLvl());

        return builder.build();
    }
}
