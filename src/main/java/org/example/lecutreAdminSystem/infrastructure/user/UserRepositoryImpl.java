package org.example.lecutreAdminSystem.infrastructure.user;

import lombok.RequiredArgsConstructor;
import org.example.lecutreAdminSystem.domain.user.entity.User;
import org.example.lecutreAdminSystem.domain.user.repository.UserRepository;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

}
