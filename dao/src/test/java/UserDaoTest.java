import com.github.nechai.aeroflot.dao.impl.UserDao;
import com.github.nechai.aeroflot.model.Role;
import com.github.nechai.aeroflot.model.User;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import static org.junit.Assert.*;

public class UserDaoTest {
    UserDao  userDao=UserDao.getInstance();

    @BeforeClass
    public static void beforeClass() {

    }
    @Test
    @Disabled
    public void insertTest() {
        String loginRight = "login_loginInsertR";
        String passwordRight = "PasswordR";
        String loginWrong = "login_loginInsertW";
        String passwordWrong = "PasswordW";
        User userInsert = new User(
                "TestUserName",
                "TestUserFirstName",
                "1111111111",
                "test@test.by",
                loginRight,
                passwordRight,
                Role.DISPATCHER
        );
        assertTrue("Insert new user", userDao.insert(userInsert));
  //      assertFalse("Insert exist user", userDao.insert(userInsert));
  //      assertFalse("Insert null user", userDao.insert(null));
        userDao.delete(userInsert);
    }

    @Test
    @Disabled
    public void loginTest() {
        String loginRight = "login_loginTestR";
        String passwordRight = "testPassword";
        String loginWrong = "login_loginTestW";
        String passwordWrong = "testPasswordW";
        User userLogin = new User(
                "TestUserName",
                "TestUserFirstName",
                "1111111111",
                "test@test.by",
                loginRight,
                passwordRight,
                Role.DISPATCHER
        );
        userDao.insert(userLogin);
        assertEquals(userDao.login(loginRight,passwordRight),userLogin);
        assertNull(userDao.login(loginWrong,passwordRight));
        assertNull(userDao.login(loginRight,passwordWrong));
        userDao.delete(userLogin);
    }
    @Test
    @Disabled
    public void getUserByloginTest() {
        String loginRight = "login_getUserByloginR";
        String passwordRight = "testPassword";
        String loginWrong = "login_getUserByloginW";
        String passwordWrong = "testPasswordW";
        User userBylogin = new User(
                "TestUserName",
                "TestUserFirstName",
                "1111111111",
                "test@test.by",
                loginRight,
                passwordRight,
                Role.DISPATCHER
        );
        userDao.insert(userBylogin);
        assertEquals(userDao.getUserBylogin(loginRight),userBylogin);
         userDao.delete(userBylogin);
    }


   @Test
   @Disabled
     public void deleteByLoginTest() {
        String loginRight = "login_loginDeleteDeleteR";
        String passwordRight = "PasswordR";
        String loginWrong = "login_loginDeletetW";
        String passwordWrong = "PasswordW";
        User userDelete = new User(
                "TestUserName",
                "TestUserFirstName",
                "1111111111",
                "test@test.by",
                loginRight,
                passwordRight,
                Role.DISPATCHER
        );
        userDao.insert(userDelete);
        assertTrue("Delete new user", userDao.delete(loginRight));
        assertFalse("Delete deleted user", userDao.delete(loginRight));
        assertFalse("Delete unexist user", userDao.delete(loginWrong));
        userDao.delete(userDelete);
    }
    @Test
    @Disabled
    public void updateTest() {
       String loginRight = "login_loginUpdateR";
        String passwordRight = "PasswordR";
        String loginWrong = "login_loginUpdateW";
        String passwordWrong = "PasswordW";
        User userUp = new User(
                "TestUserName",
                "TestUserFirstName",
                "1111111111",
                "test@test.by",
                loginRight,
                passwordRight,
                Role.DISPATCHER
        );
        userDao.insert(userUp);
        userUp.setEmail("www.@tut.by");
        assertTrue("Update user", userDao.update(userUp));
        userDao.delete(userUp);
    }
}
