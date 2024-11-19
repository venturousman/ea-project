package cs544;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import cs544.models.Role;
import cs544.models.User;
import cs544.repositories.RoleRepository;
import cs544.repositories.UserRepository;

@SpringBootApplication
public class App implements CommandLineRunner {

    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder pEncoder;
    @Autowired
    private UserRepository userRepository;

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
//        Role role1 = new Role("Admin");
//        Role role2 = new Role("User");
//        roleRepository.save(role1);
//        roleRepository.save(role2);
//
//        User user1 = new User("admin1@miu.edu", pEncoder.encode("password"), "Alan", "Smith");
//        User user2 = new User("user1@miu.edu", pEncoder.encode("password"), "Helen", "Pearson");
//        User user3 = new User("user2@miu.edu", pEncoder.encode("password"), "Robin", "Plevin");
//
//        user1.addRole(role1);
//        user2.addRole(role2);
//        user3.addRole(role2);
//
//        userRepository.save(user1);
//        userRepository.save(user2);
//        userRepository.save(user3);
    }
}