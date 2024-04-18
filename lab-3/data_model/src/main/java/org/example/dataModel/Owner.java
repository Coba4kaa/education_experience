package org.example.dataModel;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Entity
@Table(name = "owners")
@Getter
@Setter
@NoArgsConstructor
@Component
@Scope(value = "prototype")
public class Owner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private LocalDate birthday;

    public Owner(String name){
        this.name = name;
    }
}
