package org.example.repos;

import org.example.data_model.Cat;
import org.example.kafka.KafkaService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CatRepository {
    private final KafkaService kafkaService;

    public CatRepository(KafkaService kafkaService) {
        this.kafkaService = kafkaService;
    }

    public List<Cat> getAllCats() {
        try {
            String string = kafkaService.kafkaRequestReply("all_cats_request", "").toString();
            List<Cat> cats = new ArrayList<>();
            String[] catStrings = string.split(", Cat");
            for (String catString : catStrings) {
                Cat cat = Cat.fromString(catString.trim());
                cats.add(cat);
            }
            return cats;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
