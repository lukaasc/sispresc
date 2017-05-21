package br.pucrs.facin.sispresc.resource;

import br.pucrs.facin.sispresc.persistence.User;
import br.pucrs.facin.sispresc.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by lucas on 21/05/2017.
 */
@RestController
@RequestMapping("/api/login")
public class LoginResource {

    @Autowired
    private LoginService loginService;

    @RequestMapping(value="/authenticate", method = RequestMethod.POST)
    public ResponseEntity authenticate(@RequestBody User user){
        if(!loginService.authenticate(user).isAuthorized()){
             return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        return ResponseEntity.ok(loginService.authenticate(user));
    }
}
