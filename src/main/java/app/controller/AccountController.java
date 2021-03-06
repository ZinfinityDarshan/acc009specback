package app.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(value = "/secure/account")
@Slf4j
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AccountController {

}
