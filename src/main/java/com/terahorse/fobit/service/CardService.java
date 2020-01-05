package com.terahorse.fobit.service;

import com.terahorse.fobit.model.Card;
import com.terahorse.fobit.model.items.ItemEnum;
import com.terahorse.fobit.util.ResourceUtil;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Component
public class CardService extends BaseService {

    private static final String CARDS_FILE = "csv/cards.txt";

    @Autowired
    private ResourceUtil resourceUtil = new ResourceUtil();

    private static final Logger LOG = LoggerFactory.getLogger(CardService.class);

    public List<Card> getCards() {
        List<Card> cards = new ArrayList<>();
        Iterable<CSVRecord> lines = readCSV();
        if (lines == null) {
            LOG.error("Not able to load cards");
            return cards;
        }

        for (CSVRecord line : lines) {
            String id = line.get(0);
            if (id.equals("id")) { // skip header line
                continue;
            }

            String group = line.get(1);
            String name = line.get(2);
            String shortName = line.get(3);
            String desc = replaceVars(line.get(4), id);
            String good = replaceVars(line.get(5), id);
            String bad = replaceVars(line.get(6), id);
            String level = line.get(7);

            Card card = new Card(Integer.valueOf(id), name, shortName, good, bad);
            card.setGroup(group);
            card.setDesc(desc);
            card.setLevel(Integer.valueOf(level));
            cards.add(card);
        }

        return cards;
    }

    private String replaceVars(String original, String id) {
        ItemEnum itemEnum = ItemEnum.findItem(Integer.valueOf(id));
        if (itemEnum == null) {
            return original;
        }
        return original.replace("{BENEFIT}", itemEnum.BENEFIT.toString()).replace("{COST}", itemEnum.COST.toString());
    }

    private Iterable<CSVRecord> readCSV() {
        InputStream resource = resourceUtil.getResource(CARDS_FILE);
        if (resource == null) {
            return null;
        }
        try {
            InputStreamReader in = new InputStreamReader(resource, "UTF-8");
            return CSVFormat.EXCEL.parse(in);
        } catch (IOException e) {
            LOG.error("Error loading cards", e);
        }
        return null;
    }

}
