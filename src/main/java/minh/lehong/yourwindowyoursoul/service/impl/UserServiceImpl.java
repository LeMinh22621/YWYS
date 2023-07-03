package minh.lehong.yourwindowyoursoul.service.impl;

import minh.lehong.yourwindowyoursoul.constant.enums.Role;
import minh.lehong.yourwindowyoursoul.converter.CommonConverter;
import minh.lehong.yourwindowyoursoul.model.entity.User;
import minh.lehong.yourwindowyoursoul.repository.UserRepository;
import minh.lehong.yourwindowyoursoul.service.JwtService;
import minh.lehong.yourwindowyoursoul.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Override
    @Cacheable(key = "#email", value = "user")
    public User findByEmail(String email) {
        return userRepository.findByEmailAndIsDeleted(email, false)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    @Override
    @Cacheable(key = "#userId", value = "user")
    public User findByUserId(UUID userId) {
        return userRepository.findUserByUserIdAndIsDeleted(userId, false)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    @Override
    @CachePut(key = "#user.email", value = "user")
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public boolean existsUserByEmail(String email) {
        return userRepository.existsUserByEmail(email);
    }

    @Override
    public List<User> findAllByRole(Role role) {
        return userRepository.findAllByRoleAndIsDeleted(role, false);
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmailAndIsDeleted(username, false).get();
    }
}
