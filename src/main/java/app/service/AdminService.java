package app.service;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.data.repository.reactive.ProfileRepoReact;

@Service
@Slf4j
public class AdminService {

    @Autowired ProfileRepoReact profilerepo;
    
    
    
}
