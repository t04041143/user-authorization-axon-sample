package org.multilinguals.example.query.user;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface UserDetailsViewRepository extends MongoRepository<UserDetailsView, String> {
    public List<UserDetailsView> findByUserSessionId(String sessionId);
}