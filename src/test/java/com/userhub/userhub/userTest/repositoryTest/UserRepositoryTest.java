package com.userhub.userhub.userTest.repositoryTest;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.mockito.Mock;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.Transactional;

import com.userhub.userhub.adapters.repositories.RoleRepository;
import com.userhub.userhub.adapters.repositories.UserRepository;
import com.userhub.userhub.domain.builders.RoleBuilder;
import com.userhub.userhub.domain.builders.UserBuilder;
import com.userhub.userhub.domain.entities.role.RoleEntity;
import com.userhub.userhub.domain.entities.user.UserEntity;
import com.userhub.userhub.domain.objetcValues.Login;
import com.userhub.userhub.domain.objetcValues.Password;
import com.userhub.userhub.domain.objetcValues.UserName;
import com.userhub.userhub.infra.loaders.BadWordsFileLoader;
import com.userhub.userhub.domain.objetcValues.Email;

import java.util.List;
import java.util.Set;
import java.time.LocalDate;

import java.io.IOException;

@SpringBootTest
@Import(BadWordsFileLoader.class)
public class UserRepositoryTest {

        @Autowired
        private UserRepository userRepository;

        @Autowired
        private RoleRepository roleRepository;

        @Mock
        private BadWordsFileLoader badWordService;

        private List<String> badWords;

        @BeforeEach
        public void setUp() {

                try {
                        badWords = badWordService.loadBadWords("src/main/resources/config/badwords.json");
                } catch (IOException e) {
                        e.printStackTrace();
                }
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
                                .login(new Login(
                                                new UserName("hyusenn", badWords),
                                                new Password("Hyusenn123")))
                                .email(new Email("pedro@gmail.com"))
                                .roles(roles)
                                .build();
                // user1.addRoles(roles);
                UserEntity user2 = new UserBuilder()
                                .name("Tiao Costa")
                                .birthday(LocalDate.of(2003, 04, 12))
                                .login(new Login(
                                                new UserName("tiaosad", badWords),
                                                new Password("Tiao1234")))
                                .email(new Email("tiao@gmail.com"))
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
                                .login(new Login(
                                                new UserName("hyusenn", badWords),
                                                new Password("Hyusenn123")))
                                .email(new Email("pedro@gmail.com"))
                                .build();
                userRepository.create(user);
                UserEntity foundUser = userRepository.searchById(user.getId());
                assertThat(foundUser).isNotNull();
                assertThat(foundUser.getId()).isEqualTo(user.getId());
                assertThat(foundUser.getName()).isEqualTo(user.getName());
                assertThat(foundUser.getEmail().getvalue()).isEqualTo(user.getEmail().getvalue());
        }

        @Test
        @Transactional
        public void testUpdate() {
                UserEntity user = new UserBuilder()
                                .name("Pedro Costa")
                                .birthday(LocalDate.of(2003, 04, 12))
                                .login(new Login(
                                                new UserName("hyusenn", badWords),
                                                new Password("Hyusenn123")))
                                .email(new Email("pedro@gmail.com"))
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
                                .login(new Login(
                                                new UserName("troll", badWords),
                                                new Password("Hyusenn123")))
                                .email(user.getEmail())
                                .build();
                userRepository.updateUser(updatedUser);
                UserEntity foundUser = userRepository.searchById(updatedUser.getId());
                assertThat(foundUser).isNotNull();
                assertThat(foundUser.getId()).isEqualTo(user.getId());
                assertThat(foundUser.getName()).isEqualTo(user.getName());
                assertThat(foundUser.getEmail()).isEqualTo(user.getEmail());
                assertThat(foundUser.getLogin().getUsername().getValue()).isEqualTo("troll");

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
                assertThat(user.getEmail().getvalue()).isEqualTo("pedro@gmail.com");
        }

        @Test
        @Transactional
        public void testFindByLogin() {
                UserEntity user = userRepository.searchByLogin("hyusenn");
                assertThat(user).isNotNull();
                assertThat(user.getLogin().getUsername().getValue()).isEqualTo("hyusenn");
        }

        @Test
        @Transactional
        public void testFindByRole() {
                List<UserEntity> admins = userRepository.searchByRoleName("Admin");
                assertThat(admins).hasSize(1);
                assertThat(admins.get(0).getRoles().stream().anyMatch(role -> role.getName().equals("Admin"))).isTrue();
        }
}
