package com.terahorse.fobit.controller.api;

import com.terahorse.fobit.model.Card;
import com.terahorse.fobit.service.CardService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
class CardController {

    private static final Logger LOG = LoggerFactory.getLogger(CardController.class);

    @Autowired
    private CardService cardService;

    @Cacheable("cards")
    @RequestMapping("/api/cards")
    public List<Card> getCards() {
        LOG.info("Getting cards");
        return cardService.getCards();
    }

}
