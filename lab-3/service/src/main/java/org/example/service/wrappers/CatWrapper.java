package org.example.service.wrappers;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.NoArgsConstructor;
import org.example.dataModel.Cat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
@NoArgsConstructor
@Scope(value = "prototype")
public class CatWrapper {
    @JsonIgnore
    private Cat cat;

    @Autowired
    public CatWrapper(Cat cat){
        this.cat = cat;
    }

    public CatWrapper(String name, String breed, String color){
        cat = new Cat(name, breed, color);
    }

    public Cat getCat(){
        return cat;
    }

    public Long getid(){
        return cat.getId();
    }

    public String getName(){
        return cat.getName();
    }

    public void setName(String name){
        cat.setName(name);
    }

    public LocalDate getBirthday(){
        return cat.getBirthday();
    }

    public String getBreed(){
        return cat.getBreed();
    }

    public void setBreed(String breed){
        cat.setBreed(breed);
    }

    public String getColor(){
        return cat.getColor();
    }

    public void setColor(String color){
        cat.setColor(color);
    }

    public Long getOwnerId(){
        return cat.getOwnerId();
    }

    public void setOwnerId(Long ownerId){
        cat.setOwnerId(ownerId);
    }

    public List<Cat> getFriends(){
        return cat.getFriends();
    }

    public void setFriends(List<Cat> friends){
        cat.setFriends(friends);
    }
}
