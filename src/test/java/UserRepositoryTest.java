import model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import repository.UserRepository;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;

import javax.transaction.Transactional;

@RunWith(SpringRunner.class)
@Transactional
@SpringBootTest
public class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @Test
    public void insertAndReceiveUserEntityCreatedWidthBuilder(){
        final String name = "name";
        final String email = "email";
        final String password = "password";
        User user = User.builder().setLogin(name).setEmail(email).setPassword(password).build();
        user = userRepository.save(user);
        assertThat("User did not get an generated Id!", user.getId(), greaterThan(-1L));

    }
}
