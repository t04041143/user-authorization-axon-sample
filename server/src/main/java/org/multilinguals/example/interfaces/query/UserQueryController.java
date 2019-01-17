package org.multilinguals.example.interfaces.query;

//import UserDetailsView;
//import UserDetailsViewRepository;

import org.multilinguals.example.infrastructure.dto.QueryResponse;
import org.multilinguals.example.query.user.UserDetailsView;
import org.multilinguals.example.query.user.UserDetailsViewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserQueryController {
    private final UserDetailsViewRepository userDetailsViewRepository;

    @Autowired
    public UserQueryController(UserDetailsViewRepository userDetailsViewRepository) {
        this.userDetailsViewRepository = userDetailsViewRepository;
    }

    @GetMapping("/self-details")
    public QueryResponse<UserDetailsView> signUp(@RequestParam String userId) {
        Optional<UserDetailsView> userDetailsView = this.userDetailsViewRepository.findById(userId);

        return userDetailsView.map(QueryResponse::new).orElseGet(() -> new QueryResponse<>(null));
    }
}
