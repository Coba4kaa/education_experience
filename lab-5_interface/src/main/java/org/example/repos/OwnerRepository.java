package org.example.repos;

import org.example.data_model.Owner;
import org.example.kafka.KafkaService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OwnerRepository {
    private final KafkaService kafkaService;

    public OwnerRepository(KafkaService kafkaService) {
        this.kafkaService = kafkaService;
    }

    public Optional<Owner> findByName(String name) {
        try {
            Object sendReply = kafkaService.kafkaRequestReply("owner_by_name_request", name);
            Owner owner = Owner.fromString(sendReply.toString());
            return Optional.of(owner);
        } catch (Exception e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    public List<Owner> getAllOwners() {
        try {
            String string = kafkaService.kafkaRequestReply("all_owners_request", "").toString();
            System.out.println(string);
            List<Owner> owners = new ArrayList<>();
            String[] ownerStrings = string.split(", Owner");
            for (String ownerString : ownerStrings) {
                Owner owner = Owner.fromString((ownerString.startsWith("Owner") ? ownerString : "Owner" + ownerString).trim());
                owners.add(owner);
            }
            return owners;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
