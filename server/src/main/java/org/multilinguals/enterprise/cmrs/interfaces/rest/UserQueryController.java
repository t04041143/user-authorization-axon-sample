package org.multilinguals.enterprise.cmrs.interfaces.rest;

import org.multilinguals.enterprise.cmrs.query.user.UserDetailsView;
import org.multilinguals.enterprise.cmrs.query.user.UserDetailsViewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/user")
public class UserQueryController {
    private final UserDetailsViewRepository userDetailsViewRepository;

    @Autowired
    public UserQueryController(UserDetailsViewRepository userDetailsViewRepository) {
        this.userDetailsViewRepository = userDetailsViewRepository;
    }

    @GetMapping("/details")
    public Mono<UserDetailsView> signUp(@RequestParam String userId) {
        return this.userDetailsViewRepository.findById(userId);
    }
}
