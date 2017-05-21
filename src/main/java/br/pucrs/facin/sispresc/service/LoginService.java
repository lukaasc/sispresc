package br.pucrs.facin.sispresc.service;

import br.pucrs.facin.sispresc.dao.UserRepository;
import br.pucrs.facin.sispresc.dto.UserDTO;
import br.pucrs.facin.sispresc.persistence.User;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by lucas on 21/05/2017.
 */
@Component
public class LoginService {

    final static Logger logger = Logger.getLogger(LoginService.class);

    @Autowired
    private UserRepository userRepository;

    public UserDTO authenticate(User requester) {
        try {
            User user = userRepository.findOne(requester.getUsername());
            UserDTO authorized = new UserDTO();

            authorized.setUsername(user.getUsername());
            authorized.setPassword(user.getPassword());

            if (user.getPassword().equals(requester.getPassword())) {
                authorized.setAuthorized(true);
            } else {
                authorized.setAuthorized(false);
            }
            return authorized;
        } catch (NullPointerException e) {
            logger.error("Unabled to authenticate user", e);
            return null;
        }
    }
}
