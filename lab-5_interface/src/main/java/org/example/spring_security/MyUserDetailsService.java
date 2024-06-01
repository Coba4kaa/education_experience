package org.example.spring_security;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.repos.OwnerRepository;
import org.example.data_model.Owner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Getter
@NoArgsConstructor
public class MyUserDetailsService implements UserDetailsService {

    private OwnerRepository ownerRepo;

    @Autowired
    public MyUserDetailsService(OwnerRepository ownerRepo){
        this.ownerRepo = ownerRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Owner> user = ownerRepo.findByName(username);

        return user.map(MyUserDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException(username + " There is not such user in REPO"));
    }
}
