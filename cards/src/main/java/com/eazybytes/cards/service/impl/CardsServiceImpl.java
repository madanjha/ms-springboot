package com.eazybytes.cards.service.impl;

import com.eazybytes.cards.constant.CardsConstant;
import com.eazybytes.cards.dto.CardsDto;
import com.eazybytes.cards.entity.Cards;
import com.eazybytes.cards.exception.CardAlreadyExistsException;
import com.eazybytes.cards.exception.ResourceNotFoundException;
import com.eazybytes.cards.mapper.CardsMapper;
import com.eazybytes.cards.repository.CardsRepository;
import com.eazybytes.cards.service.ICardsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.smartcardio.Card;
import java.util.Optional;
import java.util.Random;

@Service
public class CardsServiceImpl implements ICardsService {
    @Autowired
    private CardsRepository cardsRepository;

   private Cards createNewCard(String mobileNumber) {
        Cards newcards=new Cards();
        Long randomCardnumber=100000000000L + new Random().nextInt(900000000);
        newcards.setCardNumber(String.valueOf(randomCardnumber));
        newcards.setMobileNumber(mobileNumber);
        newcards.setCardType(CardsConstant.CREDIT_CARD);
        newcards.setTotalLimit(CardsConstant.NEW_CARD_LIMIT);
        newcards.setAmountUsed(0);
        newcards.setAvailableAmount(CardsConstant.NEW_CARD_LIMIT);
    return newcards;
    }

    @Override
    public void createCard(String mobileNumber) {
        Optional<Cards>  optionalCards=cardsRepository.findByMobileNumber(mobileNumber);
        if (optionalCards.isPresent()){
            throw new CardAlreadyExistsException("Card already exist with given mobileNumber"+mobileNumber);
        }
       cardsRepository.save(createNewCard(mobileNumber));
    }

    @Override
    public CardsDto fetchCard(String mobileNumber) {
       Cards cards =cardsRepository.findByMobileNumber(mobileNumber).orElseThrow(
               ()-> new ResourceNotFoundException("Card", "mobileNumber", mobileNumber)
               );
        return CardsMapper.maptoCardsDto(cards, new  CardsDto());
    }

    @Override
    public boolean updateCard(CardsDto cardsDto) {
       Cards cards=cardsRepository.findByCardNumber(cardsDto.getCardNumber()).orElseThrow(
               ()-> new ResourceNotFoundException("Card", "CardNumber", cardsDto.getCardNumber())
       );
       CardsMapper.maptoCards(cardsDto,cards);
       cardsRepository.save(cards);
        return true;
    }

    @Override
    public boolean deleteCard(String mobileNumber) {
       Cards cards=cardsRepository.findByMobileNumber(mobileNumber).orElseThrow(
               ()-> new ResourceNotFoundException("Card", "mobileNumber",mobileNumber)
       );
       cardsRepository.deleteById(cards.getCardId());
        return true;
    }
}
