package br.com.pedrocasseb.mapper;

import br.com.pedrocasseb.model.User;
import br.com.pedrocasseb.payload.dto.UserDTO;

import java.util.List;
import java.util.stream.Collectors;

public class UserMapper {
    public static UserDTO toDTO(User user) {
        if(user == null) return null;
        return UserDTO.builder()
                .id(user.getId())
                .fullName(user.getFullName())
                .email(user.getEmail())
                .role(user.getRole())
                .lastLogin(user.getLastLogin())
                .phone(user.getPhone())
                .build();
    }

    public static List<UserDTO> toDTOList(List<User> users) {
        return users.stream()
                .map(UserMapper::toDTO)
                .collect(Collectors.toList());
    }
}
