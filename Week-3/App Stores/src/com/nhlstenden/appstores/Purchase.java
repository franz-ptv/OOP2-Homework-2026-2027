package com.nhlstenden.appstores;

public class Purchase
{
    private User user;
    private App app;

    public Purchase(User user, App app)
    {
        this.setUser(user);
        this.setApp(app);
    }

    public User getUser()
    {
        return this.user;
    }

    public void setUser(User user)
    {
        if (user == null)
        {
            throw new IllegalArgumentException("User can not be null");
        }

        this.user = user;
    }

    public App getApp()
    {
        return this.app;
    }

    public void setApp(App app)
    {
        if (app == null)
        {
            throw new IllegalArgumentException("App can not be null");
        }

        this.app = app;
    }
}
