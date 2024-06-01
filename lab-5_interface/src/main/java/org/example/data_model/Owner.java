package org.example.data_model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@Component
@Scope(value = "prototype")
public class Owner {
    private Long id;
    private String name;
    private LocalDate birthday;
    private String password;
    private String role;

    public Owner(String name){
        this.name = name;
    }

    @Override
    public String toString() {
        return "Owner{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", birthday=" + birthday +
                ", role='" + role + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public static Owner fromString(String ownerString) {
        String[] parts = ownerString.split(", ");

        long id = Long.parseLong(parts[0].substring(parts[0].indexOf("=") + 1));
        String name = parts[1].substring(parts[1].indexOf("=") + 1).replace("'", "");
        String birthdayStr = parts[2].substring(parts[2].indexOf("=") + 1);
        LocalDate birthday = "null".equals(birthdayStr) ? null : LocalDate.parse(birthdayStr);
        String role = parts[3].substring(parts[3].indexOf("=") + 1).replace("'", "");
        String password = parts[4].substring(parts[4].indexOf("=") + 1).replace("'", "").replace("}", "");

        Owner owner = new Owner();
        owner.setId(id);
        owner.setName(name);
        owner.setBirthday(birthday);
        owner.setRole(role);
        owner.setPassword(password);

        return owner;
    }

}
