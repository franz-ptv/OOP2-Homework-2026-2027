package com.nhlstenden.uservalidation;

import com.nhlstenden.uservalidation.user.*;
import com.nhlstenden.uservalidation.validation.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class UserValidationModuleTest
{
    private UserValidationModule userValidationModule;
    private User user;

    @BeforeEach
    void setUp()
    {
        UserStorage userStorage = new UserStorage();
        userValidationModule = new UserValidationModule(userStorage);

        user = new User("Kris", "krisEShushlqk#7", "kriskata@gmail.com", LocalDate.of(2006, 7, 31));

        userValidationModule.addValidationRule(new Password(true, true, true, true, true));
        userValidationModule.addValidationRule(new Username(userStorage));
        userValidationModule.addValidationRule(new Email());
        userValidationModule.addValidationRule(new MinimumAge(18));
    }

    @Test
    public void registerUser_properUser_notToThrow()
    {
        assertDoesNotThrow(() -> userValidationModule.registerUser(user));
    }

    @Test
    public void registerUser_passwordHasSpaceRuleNotAllowSpace_toThrow()
    {
        User invalidUser = new User("Kris", "krisEShushlqk# 7", "kriskata@gmail.com", LocalDate.of(2006, 7, 31));
        userValidationModule.addValidationRule(new Password(false, true, true, true, true));

        assertThrows(IllegalArgumentException.class, () -> userValidationModule.registerUser(invalidUser));
    }

    @Test
    void registerUser_passwordMissingSpecialChar_throwsException()
    {
        User invalidUser = new User("Kris", "krisPassword123", "kriskata@gmail.com", LocalDate.of(2006, 7, 31));

        assertThrows(IllegalArgumentException.class, () -> userValidationModule.registerUser(invalidUser));
    }

    @Test
    void registerUser_passwordMissingNumber_throwsException()
    {
        User invalidUser = new User("Kris", "krisPassword#", "kriskata@gmail.com", LocalDate.of(2006, 7, 31));
        userValidationModule.addValidationRule(new Password(true, true, true, true, true));

        assertThrows(IllegalArgumentException.class, () -> userValidationModule.registerUser(invalidUser));
    }

    @Test
    void registerUser_usernameAlreadyExists_throwsException()
    {
        userValidationModule.registerUser(user);
        User userSameUsername = new User("Kris", "securePass#1", "existing@gmail.com", LocalDate.of(2000, 1, 1));

        assertThrows(IllegalArgumentException.class, () -> userValidationModule.registerUser(userSameUsername));
    }
}

