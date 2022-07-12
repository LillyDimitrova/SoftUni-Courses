package bg.softuni.FindYourHome.model.mapper;

import bg.softuni.FindYourHome.model.dtos.UserRegistrationDTO;
import bg.softuni.FindYourHome.model.entity.UserEntity;
import bg.softuni.FindYourHome.model.enums.RoleEnum;
import org.springframework.stereotype.Component;

import javax.annotation.processing.Generated;
import java.util.List;

@Generated(
        value = "org.mapstruct.ap.MappingProcessor",
        date = "2022-06-16T11:24:39+0300",
        comments = "version: 1.5.1.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.4.1.jar, environment: Java 11.0.15 (Oracle Corporation)"
)
@Component
public class UserMapperImpl implements UserMapper{
    @Override
    public UserEntity userDtoToUserEntity(UserRegistrationDTO registerDTO) {
        if (registerDTO == null) {
            return null;
        }
        UserEntity newUser = new UserEntity()
                .setUsername(registerDTO.getUsername())
                .setEmail(registerDTO.getEmail())
                .setFirstName(registerDTO.getFirstName())
                .setLastName(registerDTO.getLastName());

        return newUser;
    }
}
