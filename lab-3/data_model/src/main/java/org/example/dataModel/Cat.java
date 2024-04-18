package org.example.dataModel;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "cats")
@Getter
@Setter
@NoArgsConstructor
@Component
@Scope(value = "prototype")
public class Cat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private LocalDate birthday;
    private String breed;
    private String color;
    @Column(name = "owner_id")
    private Long ownerId;

    @ManyToMany
    @JoinTable(
            name = "cat_friends",
            joinColumns = @JoinColumn(name = "cat_id"),
            inverseJoinColumns = @JoinColumn(name = "cat_friend_id")
    )
    private List<Cat> friends;

    public Cat(String name, String breed, String color){
        this.name = name;
        this.breed = breed;
        this.color = color;
        this.birthday = LocalDate.now();
        this.friends = new ArrayList<Cat>();
    }
}
