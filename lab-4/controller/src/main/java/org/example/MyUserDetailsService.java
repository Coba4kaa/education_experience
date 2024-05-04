package org.example;

import lombok.Getter;
import org.example.dao.interfaces.OwnerRepository;
import org.example.dataModel.Owner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Getter
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private OwnerRepository userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Owner> user = userRepo.findByName(username);

        return user.map(MyUserDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException(username + "There is not such user in REPO"));

    }
}