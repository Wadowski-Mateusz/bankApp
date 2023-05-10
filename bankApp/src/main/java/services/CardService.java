package services;

import entities.Card;
import repositories.CardRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CardService {

    private final CardRepository cardRepository;

    public CardService(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    public List<Card> getAllCards() {
        return cardRepository.findAll();
    }

    public Card getCardById(UUID id) {
        return cardRepository.findById(id).orElse(null);
    }

    public Card createCard(Card card) {
        return cardRepository.save(card);
    }

    public Card updateCard(UUID id, Card card) {
        Card existingCard = cardRepository.findById(id).orElse(null);
        if (existingCard != null) {
            existingCard.setNumber(card.getNumber());
            existingCard.setUserId(card.getUserId());
            existingCard.setExpiryDate(card.getExpiryDate());
            existingCard.setCvv(card.getCvv());
            return cardRepository.save(existingCard);
        } else {
            return null;
        }
    }

    public boolean deleteCard(UUID id) {
        Card existingCard = cardRepository.findById(id).orElse(null);
        if (existingCard != null) {
            cardRepository.delete(existingCard);
            return true;
        } else {
            return false;
        }
    }

}
