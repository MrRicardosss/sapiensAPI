package pt.sapiens.sapiensAPI.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import pt.sapiens.sapiensAPI.entities.User;
import pt.sapiens.sapiensAPI.entities.UserDetailsImpl;
import pt.sapiens.sapiensAPI.repositories.UserRepository;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public Optional<User> getUserByUserDetails() {
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return userRepository.findByEmail(userDetails.getUsername());
    }
}
