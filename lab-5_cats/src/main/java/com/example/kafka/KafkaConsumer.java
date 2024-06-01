package com.example.kafka;

import com.example.dao.interfaces.CatRepository;
import com.example.data_model.Cat;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class KafkaConsumer {
    private final CatRepository catRepository;

    public KafkaConsumer(CatRepository catRepository) {
        this.catRepository = catRepository;
    }

    @KafkaListener(topics = "cats_request", groupId = "group_id")
    @SendTo("cats_response")
    public String getCat(String message){
        Optional<Cat> optionalCat = catRepository.findById(Long.parseLong(message));
        if (optionalCat.isPresent())
            return optionalCat.toString();
        else
            return "Wrong input.";
    }

    @KafkaListener(topics = "all_cats_request", groupId = "group_id")
    @SendTo("cats_response")
    public String getAllCats(){
        List<Cat> cats = catRepository.findAll();
        if (!cats.isEmpty()) {
            StringBuilder result = new StringBuilder();
            for (Cat cat : cats) {
                result.append(cat.toString()).append(", ");
            }
            result.delete(result.length() - 2, result.length());
            return result.toString();
        } else {
            return "No cats found for input: ";
        }
    }

}
