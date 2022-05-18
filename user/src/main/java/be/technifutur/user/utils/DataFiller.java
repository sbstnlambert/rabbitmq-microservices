package be.technifutur.user.utils;

import be.technifutur.user.model.entity.User;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import be.technifutur.user.repository.UserRepository;

import java.util.List;
import java.util.UUID;

@Component
public class DataFiller implements InitializingBean {

    private final UserRepository repository;
    private final PasswordEncoder encoder;

    public DataFiller(UserRepository repository, PasswordEncoder encoder) {
        this.repository = repository;
        this.encoder = encoder;
    }

    @Override
    public void afterPropertiesSet() throws Exception {

        User user0 = new User(
                0L,
                UUID.randomUUID(),
                "Alex",
                encoder.encode("Alex"),
                true,
                List.of("USER")
        );

        repository.save(user0);

        User user1 = new User(
                0L,
                UUID.randomUUID(),
                "Yannick",
                encoder.encode("Yannick"),
                true,
                List.of("USER")
        );

        repository.save(user1);

    }
}
