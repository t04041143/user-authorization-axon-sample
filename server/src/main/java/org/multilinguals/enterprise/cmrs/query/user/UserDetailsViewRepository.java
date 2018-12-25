package org.multilinguals.enterprise.cmrs.query.user;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface UserDetailsViewRepository extends ReactiveMongoRepository<UserDetailsView, String> {
}