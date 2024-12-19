package com.userhub.userhub.userTest;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.userhub.userhub.domain.entities.role.RoleEntity;
import com.userhub.userhub.domain.entities.user.UserEntity;
import com.userhub.userhub.infra.repository.roles.RoleRepository;
import com.userhub.userhub.infra.repository.users.UserRepository;

import java.util.List;
import java.util.Set;
import java.time.LocalDate;

@SpringBootTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @BeforeEach
    public void setUp() {
        // Limpa o banco de dados antes de cada teste
        userRepository.deleteAll();
        roleRepository.deleteAll();

        // Cria Papeis de teste e salva no banco antes de rodar os testes

        RoleEntity role1 = new RoleEntity("Admin");

        RoleEntity role2 = new RoleEntity("User");

        roleRepository.create(role1);
        roleRepository.create(role2);

        // Cria usuários de teste e salva no banco antes de rodar os testes
        Set<RoleEntity> roles = Set.of(role1, role2);

        UserEntity user1 = new UserEntity(
                "Pedro Costa",
                LocalDate.of(2003, 04, 12),
                "hyusenn",
                "pedro@gmail.com",
                "123123123");
        user1.addRoles(roles);
        UserEntity user2 = new UserEntity(
                "Tiao Costa",
                LocalDate.of(2003, 04, 12),
                "tiao",
                "tiao@gmail.com",
                "123123123");
        user2.addRole(role2);
        userRepository.create(user1);
        userRepository.create(user2);
    }

    @Test
    @Transactional
    public void testDeleteAll() {
        userRepository.deleteAll();
        List<UserEntity> users = userRepository.searchAll();
        assertThat(users).isEmpty();
    }

    @Test
    @Transactional
    public void testCreate() {
        UserEntity user = new UserEntity(
                "Pedro Costa",
                LocalDate.of(2003, 04, 12),
                "hyusenn",
                "pedro@gmail.com",
                "123123123");
        userRepository.create(user);
    }

    @Test
    @Transactional
    public void testFindAll() {
        List<UserEntity> users = userRepository.searchAll();
        assertThat(users).hasSize(2);
    }

    @Test
    @Transactional
    public void testFindByEmail() {
        UserEntity user = userRepository.searchByEmail("pedro@gmail.com");
        assertThat(user).isNotNull();
        assertThat(user.getEmail()).isEqualTo("pedro@gmail.com");
    }

    @Test
    @Transactional
    public void testFindByLogin() {
        UserEntity user = userRepository.searchByLogin("hyusenn");
        assertThat(user).isNotNull();
        assertThat(user.getLogin()).isEqualTo("hyusenn");
    }

    @Test
    @Transactional
    public void testFindByRole() {
        List<UserEntity> admins = userRepository.searchByRole("Admin");
        assertThat(admins).hasSize(1);
        assertThat(admins.get(0).getRoles().stream().anyMatch(role -> role.getName().equals("Admin"))).isTrue();
    }
}
