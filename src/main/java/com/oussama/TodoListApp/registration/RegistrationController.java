package com.oussama.TodoListApp.registration;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RequestMapping("api/v1/registration")
@RestController
public class RegistrationController {
    private final RegistrationService registrationService;

    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    public String register(
            @RequestBody RegistrationRequest registrationRequest
    ) {
       return registrationService.register(registrationRequest);
    }
}
