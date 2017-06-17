package at.fh.swenga.account.service;

import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface SecurityService {
    public String findLoggedInUsername();
   
    

    void autologin(String username, String password);
}