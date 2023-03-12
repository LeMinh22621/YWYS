package minh.lehong.yourwindowyoursoul.service;

import minh.lehong.yourwindowyoursoul.entity.User;

import java.util.Collection;
import java.util.List;

public interface UserService {
    User findByEmail(String email);

//    User save(User employee);
//
//    List<User> saveAll(Collection<User> userEntities);
//
//    User getUserById(final String userId);
}
