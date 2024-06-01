package com.example.kafka;

import com.example.dao.interfaces.OwnerRepository;
import com.example.data_model.Owner;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class KafkaConsumer {
    private final OwnerRepository ownerRepository;

    public KafkaConsumer(OwnerRepository ownerRepository) {
        this.ownerRepository = ownerRepository;
    }

    @KafkaListener(topics = "owners_request", groupId = "group_id")
    @SendTo("owners_response")
    public String getOwner(String message){
        Optional<Owner> optionalOwner = ownerRepository.findById(Long.parseLong(message));
        if (optionalOwner.isPresent())
            return optionalOwner.toString();
        else
            return "Wrong input.";
    }

    @KafkaListener(topics = "owner_by_name_request", groupId = "group_id")
    @SendTo("owners_response")
    public String getAllOwners(String message){
        Optional<Owner> optionalOwner = ownerRepository.findByName(message);
        if (optionalOwner.isPresent())
            return optionalOwner.toString();
        else
            return "Wrong input.";
    }

    @KafkaListener(topics = "all_owners_request", groupId = "group_id")
    @SendTo("owners_response")
    public String getAllOwners(){
        List<Owner> owners = ownerRepository.findAll();
        if (!owners.isEmpty()) {
            StringBuilder result = new StringBuilder();
            for (Owner owner : owners) {
                result.append(owner.toString()).append(", ");
            }
            result.delete(result.length() - 2, result.length());
            return result.toString();
        } else {
            return "No owners found for input: ";
        }
    }

}
