package br.com.pedrocasseb.controller;

import br.com.pedrocasseb.payload.response.ApiResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping()
    public String HomeController() {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("Hello World");
        return apiResponse.getMessage();
    }
}
