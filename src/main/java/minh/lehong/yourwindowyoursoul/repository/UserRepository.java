package minh.lehong.yourwindowyoursoul.repository;

import minh.lehong.yourwindowyoursoul.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface UserRepository extends JpaRepository<User, String> {
    User findByEmail(String email);

//    List<User> findAllByIsDeletedAndIsDisabled(int isDeleted, boolean isDisabled);
//
//    List<User> findAllByUserIdAndIsDeleted(String userId, int isDeleted);
}
