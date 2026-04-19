package br.com.pedrocasseb.service.impl;

import br.com.pedrocasseb.mapper.UserMapper;
import br.com.pedrocasseb.model.User;
import br.com.pedrocasseb.payload.dto.UserDTO;
import br.com.pedrocasseb.repository.UserRepository;
import br.com.pedrocasseb.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public UserDTO getUserByEmail(String email) throws Exception {
        User user = userRepository.findByEmail(email);
        if(user == null){
            throw new Exception("user not found");
        }
        return UserMapper.toDTO(user);
    }

    @Override
    public UserDTO getUserById(Long id) throws Exception {
        User user = userRepository.findById(id).orElseThrow(
                ()-> new Exception("user not found with id " + id)
        );
        return UserMapper.toDTO(user);
    }

    @Override
    public List<UserDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        return UserMapper.toDTOList(users);
    }
}
