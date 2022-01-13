package com.tmaexample;
import static org.assertj.core.api.Assertions.assertThat;

import com.tmaexample.entities.User;
import com.tmaexample.repos.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;


@RunWith(SpringRunner.class)
@DataJpaTest
public class JPA_UnitTest {
    @Autowired
    private TestEntityManager entityManager;

    @MockBean
    private UserRepository userRepo;

    @Test
    public void found_no_user_if_repo_empty() {
        Iterable<User> customerGroups = userRepo.findAll();
        assertThat(customerGroups).isEmpty();
    }
    @Test
    public void create_a_user() {
        User user = new User();
        user.setName("Test");
        user.setPassword("12345");
        user.setCreated_at(new Date());
        user.setModified_at(new Date());
        user.setEmail("yes@test.com");
        userRepo.save(user);
        assertThat(user).hasFieldOrPropertyWithValue("email", "yes@test.com");
    }
    @Test
    public void found_all_user() {
        User user1 = new User();
        entityManager.persist(user1);

        User user2 = new User();
        entityManager.persist(user2);

        Iterable<User> customerGroups = userRepo.findAll();
        assertThat(customerGroups).hasSize(2).contains(user1, user2);
    }
    @Test
    public void found_user_with_id() {
        User user1 = new User();
        entityManager.persist(user1);

        User user2 = new User();
        entityManager.persist(user2);
        User foundUser= userRepo.findById(user1.getId()).get();

        assertThat(foundUser).isEqualTo(user2);
    }
}
