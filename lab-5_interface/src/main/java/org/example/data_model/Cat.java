package org.example.data_model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Component
@Scope(value = "prototype")
public class Cat {
    private Long id;
    private String name;
    private LocalDate birthday;
    private String breed;
    private String color;
    private Long ownerId;

    private List<Cat> friends;

    public Cat(String name, String breed, String color){
        this.name = name;
        this.breed = breed;
        this.color = color;
        this.birthday = LocalDate.now();
        this.friends = new ArrayList<Cat>();
    }

    @Override
    public String toString() {
        return "Cat{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", birthday=" + birthday +
                ", breed='" + breed + '\'' +
                ", color='" + color + '\'' +
                ", ownerId=" + ownerId +
                '}';
    }

    public static Cat fromString(String catString) {
        String[] parts = catString.split(", ");

        long id = Long.parseLong(parts[0].substring(parts[0].indexOf("=") + 1));
        String name = parts[1].substring(parts[1].indexOf("=") + 1);
        LocalDate birthday = LocalDate.parse(parts[2].substring(parts[2].indexOf("=") + 1));
        String breed = parts[3].substring(parts[3].indexOf("=") + 1);
        String color = parts[4].substring(parts[4].indexOf("=") + 1);
        long ownerId = Long.parseLong(parts[5].substring(parts[5].indexOf("=") + 1, parts[5].length() - 1));

        Cat cat = new Cat();
        cat.setId(id);
        cat.setName(name);
        cat.setBirthday(birthday);
        cat.setBreed(breed);
        cat.setColor(color);
        cat.setOwnerId(ownerId);

        return cat;
    }




}
