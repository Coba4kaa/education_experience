package org.example.service.wrappers;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.NoArgsConstructor;
import org.example.dataModel.Owner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@NoArgsConstructor
@Scope(value = "prototype")
public class OwnerWrapper {
    @JsonIgnore
    private Owner owner;

    @Autowired
    public OwnerWrapper(Owner owner){
        this.owner = owner;
    }

    public OwnerWrapper(String name){
        owner = new Owner(name);
    }

    public Owner getOwner(){
        return owner;
    }

    public Long getId(){
        return owner.getId();
    }

    public String getName(){
        return owner.getName();
    }

    public void setName(String name){
        owner.setName(name);
    }

    public LocalDate getBirthday(){
        return owner.getBirthday();
    }

    public void setBirthday(LocalDate birthday){
        owner.setBirthday(birthday);
    }


}
