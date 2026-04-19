package br.com.pedrocasseb.service;

import br.com.pedrocasseb.payload.dto.UserDTO;

import java.util.List;

public interface UserService {
    UserDTO getUserByEmail(String email) throws Exception;
    UserDTO getUserById(Long id) throws Exception;
    List<UserDTO> getAllUsers();
}
