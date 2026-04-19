package br.com.pedrocasseb.payload.response;

import br.com.pedrocasseb.payload.dto.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponse {
    private String jwt;
    private String message;
    private String title;
    private UserDTO user;
}
