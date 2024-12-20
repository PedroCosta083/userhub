package com.userhub.userhub.roleTest;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.userhub.userhub.application.builders.role.RoleBuilder;
import com.userhub.userhub.domain.entities.role.RoleEntity;
import com.userhub.userhub.infra.repository.roles.RoleRepository;

@SpringBootTest
public class RoleRepositoryTest {

    @Autowired
    private RoleRepository roleRepository;

    @BeforeEach
    public void setUp() {
        // Limpa o banco de dados antes de cada teste
        roleRepository.deleteAll();

        // RoleEntity role1 = new RoleEntity("Admin");
        // RoleEntity role2 = new RoleEntity("User");
        // roleRepository.create(role1);
        // roleRepository.create(role2);
    }

    @Test
    @Transactional
    public void testCreateRole() {
        RoleEntity roleEntity = new RoleBuilder().name("Admin").build();
        roleRepository.create(roleEntity);
        RoleEntity foundEntity = roleRepository.searchById(roleEntity.getId());
        assertThat(foundEntity).isNotNull();
        assertThat(foundEntity.getName()).isEqualTo("Admin");
    }

    @Test
    @Transactional
    public void testSearchById() {
        RoleEntity roleEntity = new RoleBuilder().name("Admin").build();
        roleRepository.create(roleEntity);

        RoleEntity result = roleRepository.searchById(roleEntity.getId());
        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(roleEntity.getId());
    }

    @Test
    @Transactional
    public void testSearchByName() {
        RoleEntity roleEntity = new RoleBuilder().name("Admin").build();
        roleRepository.create(roleEntity);

        RoleEntity result = roleRepository.searchByName("Admin");
        assertThat(result).isNotNull();
        assertThat(result.getName()).isEqualTo("Admin");
    }

    @Test
    @Transactional
    public void testSearchAll() {
        RoleEntity roleEntity1 = new RoleBuilder().name("Admin").build();
        RoleEntity roleEntity2 = new RoleBuilder().name("User").build();
        roleRepository.create(roleEntity1);
        roleRepository.create(roleEntity2);

        List<RoleEntity> result = roleRepository.searchAll();
        assertThat(result).hasSize(2);
    }

    @Test
    @Transactional
    public void testDeleteAll() {
        RoleEntity roleEntity1 = new RoleBuilder().name("Admin").build();
        RoleEntity roleEntity2 = new RoleBuilder().name("User").build();
        roleRepository.create(roleEntity1);
        roleRepository.create(roleEntity2);

        roleRepository.deleteAll();
        List<RoleEntity> result = roleRepository.searchAll();
        assertThat(result).isEmpty();
    }
}
