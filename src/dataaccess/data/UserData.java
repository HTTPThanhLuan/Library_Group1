package dataaccess.data;

import model.Role;
import model.User;

import java.util.ArrayList;
import java.util.List;

public class UserData {
    public List<User> getUsers() {
        List<User> users = new ArrayList<>();
        User user1 = new User();
        user1.setCity("FAIRFIELD, IA");
        user1.setFirstName("Lam");
        user1.setLastName("Tang");
        user1.setPostalCode("52556");
        user1.setEmail("lamtang@yahoo.com");
        user1.setUsername("lam");
        user1.setPassword("lam");
        user1.setRole(Role.BOTH);
        users.add(user1);
        User user2 = new User();
        user2.setCity("FAIRFIELD, IA");
        user2.setFirstName("Vong");
        user2.setLastName("Nguyen");
        user2.setPostalCode("52556");
        user2.setEmail("vongnguyen@yahoo.com");
        user2.setUsername("admin");
        user2.setPassword("admin");
        user2.setRole(Role.ADMIN);
        users.add(user2);
        User user3 = new User();
        user3.setCity("FAIRFIELD, IA");
        user3.setFirstName("Luan");
        user3.setLastName("Tran");
        user3.setPostalCode("52556");
        user3.setEmail("luantran@yahoo.com");
        user3.setUsername("luan");
        user3.setPassword("luan");
        user3.setRole(Role.LIBRARIAN);
        users.add(user3);
        return users;
    }
}
