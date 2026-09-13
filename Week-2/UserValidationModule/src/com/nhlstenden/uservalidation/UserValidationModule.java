package com.nhlstenden.uservalidation;
import com.nhlstenden.uservalidation.user.*;
import com.nhlstenden.uservalidation.validation.ValidationRule;

import java.util.ArrayList;
import java.util.List;


public class UserValidationModule
{
    private UserStorage userStorage;
    private List<ValidationRule> validationRules;

    public UserValidationModule(UserStorage userStorage)
    {
        this.setUserStorage(userStorage);
        this.setValidationRules(new ArrayList<>());
    }

    public UserStorage getUserStorage()
    {
        return this.userStorage;
    }

    public void setUserStorage(UserStorage userStorage)
    {
        if (userStorage == null)
        {
            throw new IllegalArgumentException("Can not set userStorage as null");
        }

        this.userStorage = userStorage;
    }

    public List<ValidationRule> getValidationRules()
    {
        return new ArrayList<>(this.validationRules);
    }

    public void setValidationRules(List<ValidationRule> validationRules)
    {
        if (validationRules == null)
        {
            throw new IllegalArgumentException("validationRules cannot be null");
        }

        for (ValidationRule validationRule : validationRules)
        {
            if (validationRule == null)
            {
                throw new IllegalArgumentException("A validation rule from validationRules cannot be null");
            }
        }

        this.validationRules = new ArrayList<>(validationRules);
    }

    public void registerUser(User user)
    {
        if (user == null)
        {
            throw new IllegalArgumentException("User can not be null!");
        }
        for (ValidationRule validationRule : validationRules)
        {
            if (!validationRule.validate(user))
            {
                throw new IllegalArgumentException("There is a problem with the " + validationRule.getClass().getSimpleName());
            }
        }
        userStorage.addUser(user);
    }

    public void addValidationRule(ValidationRule validationRule)
    {
        if (validationRule == null)
        {
            throw new IllegalArgumentException("The validation rule can not be null");
        }

        validationRules.add(validationRule);
    }

    public void removeValidationRule(ValidationRule validationRule)
    {
        if (validationRule == null)
        {
            throw new IllegalArgumentException("The validation rule can not be null");
        }

        validationRules.remove(validationRule);
    }
}
