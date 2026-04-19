package br.com.pedrocasseb.controller;

import br.com.pedrocasseb.payload.response.ApiResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping
    public ApiResponse homeController() {
        ApiResponse response = new ApiResponse("Airline Core Service!");
        return response;
    }
}
