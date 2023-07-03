package minh.lehong.yourwindowyoursoul.repository;

import minh.lehong.yourwindowyoursoul.constant.enums.Role;
import minh.lehong.yourwindowyoursoul.model.entity.User;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID>{

    Optional<User> findByEmailAndIsDeleted(String email, boolean isDeleted);
    Optional<User> findByEmailAndPasswordAndIsDeleted(String email, String password, boolean isDeleted);

    boolean existsUserByEmail(String email);

    Optional<User> findUserByUserIdAndIsDeleted(UUID userId, boolean isDeleted);

    List<User> findAllByRoleAndIsDeleted(Role role, boolean isDeleted);
}
