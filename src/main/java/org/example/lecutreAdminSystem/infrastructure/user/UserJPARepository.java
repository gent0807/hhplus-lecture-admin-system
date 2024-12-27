package org.example.lecutreAdminSystem.infrastructure.user;

import org.example.lecutreAdminSystem.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserJPARepository extends JpaRepository<User, Long> {

}
