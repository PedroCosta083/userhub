package com.userhub.userhub.userTest;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.userhub.userhub.application.builders.role.RoleBuilder;
import com.userhub.userhub.application.builders.user.UserBuilder;
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

        RoleEntity role1 = new RoleBuilder().name("Admin").build();
        RoleEntity role2 = new RoleBuilder().name("User").build();

        roleRepository.create(role1);
        roleRepository.create(role2);

        // Cria usuários de teste e salva no banco antes de rodar os testes
        Set<RoleEntity> roles = Set.of(role1, role2);

        UserEntity user1 = new UserBuilder()
                .name("Pedro Costa")
                .birthday(LocalDate.of(2003, 04, 12))
                .login("hyusenn")
                .email("pedro@gmail.com")
                .password("123123123")
                .roles(roles)
                .build();
        // user1.addRoles(roles);
        UserEntity user2 = new UserBuilder()
                .name("Tiao Costa")
                .birthday(LocalDate.of(2003, 04, 12))
                .login("tiao")
                .email("tiao@gmail.com")
                .password("123123123")
                .build();
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
        UserEntity user = new UserBuilder()
                .name("Pedro Costa")
                .birthday(LocalDate.of(2003, 04, 12))
                .login("hyusenn")
                .email("pedro@gmail.com")
                .password("123123123")
                .build();
        userRepository.create(user);
        UserEntity foundUser = userRepository.searchById(user.getId());
        assertThat(foundUser).isNotNull();
        assertThat(foundUser.getId()).isEqualTo(user.getId());
        assertThat(foundUser.getName()).isEqualTo(user.getName());
        assertThat(foundUser.getEmail()).isEqualTo(user.getEmail());

    }

    @Test
    @Transactional
    public void testUpdate() {
        UserEntity user = new UserBuilder()
                .name("Pedro Costa")
                .birthday(LocalDate.of(2003, 04, 12))
                .login("hyusenn")
                .email("pedro@gmail.com")
                .password("123123123")
                .build();
        userRepository.create(user);
        UserEntity updatedUser = new UserBuilder()
                .id(user.getId())
                .name(user.getName())
                .active(user.isActive())
                .createdAt(user.getCreatedAt())
                .updatedAt(user.getUpdatedAt())
                .deactivatedAt(user.getDeactivatedAt())
                .birthday(user.getBirthday())
                .login("troll")
                .email(user.getEmail())
                .password(user.getPassword())
                .build();
        userRepository.updateUser(updatedUser);
        UserEntity foundUser = userRepository.searchById(updatedUser.getId());
        assertThat(foundUser).isNotNull();
        assertThat(foundUser.getId()).isEqualTo(user.getId());
        assertThat(foundUser.getName()).isEqualTo(user.getName());
        assertThat(foundUser.getEmail()).isEqualTo(user.getEmail());
        assertThat(foundUser.getLogin()).isEqualTo("troll");

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
