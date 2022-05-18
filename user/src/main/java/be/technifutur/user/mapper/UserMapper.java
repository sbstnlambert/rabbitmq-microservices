package be.technifutur.user.mapper;

import be.technifutur.user.model.dto.UserDTO;
import be.technifutur.user.model.entity.User;

public class UserMapper {

    public static UserDTO toDto(User entity){
        if(entity == null)
            return null;

        return new UserDTO(entity.getUsername(),entity.getRoles());

    }

}
