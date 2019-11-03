package hello;



import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hello.model.UserAccount;

@RestController
@RequestMapping("/rest/token")
public class TokenController {

    private JwtGenerator jwtGenerator;

    public TokenController(JwtGenerator jwtGenerator) {
        this.jwtGenerator = jwtGenerator;
    }

//    @PostMapping
//    public String generate(@RequestBody final JwtUser jwtUser) {
//        return jwtGenerator.generate(jwtUser);
//
//    }
    
    @PostMapping
    public String generate(@RequestBody final UserAccount jwtUser) {
        return jwtGenerator.generateForUser(jwtUser);

    }
}
