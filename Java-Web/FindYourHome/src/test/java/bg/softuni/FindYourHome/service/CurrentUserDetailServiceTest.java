package bg.softuni.FindYourHome.service;

import bg.softuni.FindYourHome.model.entity.RoleEntity;
import bg.softuni.FindYourHome.model.entity.UserEntity;
import bg.softuni.FindYourHome.model.enums.RoleEnum;
import bg.softuni.FindYourHome.model.user.CurrentUserDetails;
import bg.softuni.FindYourHome.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CurrentUserDetailServiceTest {

    @Mock
    private UserRepository mockUserRepo;

    private CurrentUserDetailService toTest;

    @BeforeEach
    void  setUp() {
        toTest = new CurrentUserDetailService(mockUserRepo);
    }


    @Test
    void testLoadUserByUsername_UserExist() {
        UserEntity testUserEntity = new UserEntity().
                setEmail("test@example.com")
                .setPassword("12345")
                .setFirstName("Pesho")
                .setLastName("Ivanov")
                .setRoles(
                        List.of(new RoleEntity().setRole(RoleEnum.USER))
                );
       when(mockUserRepo.findByUsername(testUserEntity.getUsername())).thenReturn(Optional.of(testUserEntity));

      CurrentUserDetails userDetails = (CurrentUserDetails) toTest.loadUserByUsername(testUserEntity.getUsername());


        Assertions.assertEquals(testUserEntity.getEmail(), userDetails.getUsername());
        Assertions.assertEquals(testUserEntity.getFirstName(), userDetails.getFirstName());
        Assertions.assertEquals(testUserEntity.getLastName(), userDetails.getLastName());
        Assertions.assertEquals(testUserEntity.getPassword(), userDetails.getPassword());
        Assertions.assertEquals(testUserEntity.getFirstName() + " " + testUserEntity.getLastName(), userDetails.getFullName());

        var authorities = userDetails.getAuthorities();
        Assertions.assertEquals(1, authorities.size());

        var authoritiesIter = authorities.iterator();
        Assertions.assertEquals("ROLE_" + RoleEnum.USER.name(), authoritiesIter.next().getAuthority());

    }

    @Test
    void testLoadUserByUsername_UserDoesNotExist() {

    }
}
