package com.nps.userauth2server.repository;

import com.nps.userauth2server.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role save(Role role);

    @Override
    <S extends Role> List<S> saveAll(Iterable<S> entities);
}
