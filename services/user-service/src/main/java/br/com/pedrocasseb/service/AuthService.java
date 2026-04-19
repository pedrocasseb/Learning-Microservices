package br.com.pedrocasseb.service;

import br.com.pedrocasseb.payload.dto.UserDTO;
import br.com.pedrocasseb.payload.response.AuthResponse;

public interface AuthService {
    AuthResponse login(String email, String password) throws Exception;
    AuthResponse signup(UserDTO req) throws Exception;
}
