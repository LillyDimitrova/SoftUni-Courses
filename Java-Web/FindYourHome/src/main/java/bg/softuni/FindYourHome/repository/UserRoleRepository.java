package bg.softuni.FindYourHome.repository;

import bg.softuni.FindYourHome.model.entity.UserRoleEntity;
import bg.softuni.FindYourHome.model.enums.UserRoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRoleEntity, Long> {

    UserRoleEntity findByRole(UserRoleEnum role);
}
