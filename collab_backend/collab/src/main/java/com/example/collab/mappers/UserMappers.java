package com.example.collab.mappers;

import com.example.collab.dtos.UserDTO;
import com.example.collab.entities.User;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

/** Convertit entre {@link com.example.collab.entities.User} et {@link com.example.collab.dtos.UserDTO}. */
@Service
public class UserMappers {

    public UserDTO userToUserDTO(User user) {
        if (user == null) return null;
        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(user, userDTO);
        return userDTO;
    }
    public User userDTOToUser(UserDTO userDTO) {
        User user = new User();
        BeanUtils.copyProperties(userDTO, user);
        return user ;
    }
}
