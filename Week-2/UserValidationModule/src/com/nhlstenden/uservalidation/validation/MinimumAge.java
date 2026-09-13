package com.nhlstenden.uservalidation.validation;

import com.nhlstenden.uservalidation.user.User;

import java.time.LocalDate;

public class MinimumAge implements ValidationRule
{
    private int age;

    public MinimumAge(int age)
    {
        this.setAge(age);
    }

    public int getAge()
    {
        return this.age;
    }

    public void setAge(int age)
    {
        if (age <= 0)
        {
            throw new IllegalArgumentException("Can not input age lower than or equal to 0");
        }

        this.age = age;
    }

    @Override
    public boolean validate(User user)
    {
        if (user.getAge() < getAge())
        {
            return false;
        }

        return true;
    }
}
