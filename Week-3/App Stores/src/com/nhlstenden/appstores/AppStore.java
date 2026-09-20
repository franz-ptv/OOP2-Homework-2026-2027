package com.nhlstenden.appstores;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AppStore
{
    private static final int CEDE_REVENUE_TO_DEV_IN_PERCENT = 30;
    private static final int AGE_RESTRICTION_VIOLENCE = 16;
    private static final int AGE_RESTRICTION_NUDITY = 18;
    private static final String EURO_CURRENCY = "euro";
    private static final String DOLLAR_CURRENCY = "dollar";
    private String currency;
    private int totalRevenueInCents;
    private List<App> apps;
    private List<User> users;
    private List<Purchase> purchases;
    private Map<String, Integer> appRevenueInCents;

    public AppStore(String currency)
    {
        this.setCurrency(currency);
        this.setTotalRevenueInCents(0);
        this.setApps(new ArrayList<>());
        this.setUsers(new ArrayList<>());
        this.setPurchases(new ArrayList<>());
        this.setAppRevenueInCents(new HashMap<>());
    }

    public String getCurrency()
    {
        return this.currency;
    }

    public void setCurrency(String currency)
    {
        if (!currency.equalsIgnoreCase(EURO_CURRENCY) && !currency.equalsIgnoreCase(DOLLAR_CURRENCY))
        {
            throw new IllegalArgumentException("Incorrect currency selected! Choose between dollar or euro.");
        }

        this.currency = currency;
    }

    public int getTotalRevenueInCents()
    {
        return this.totalRevenueInCents;
    }

    public void setTotalRevenueInCents(int totalRevenueInCents)
    {
        if (totalRevenueInCents < 0)
        {
            throw new IllegalArgumentException("Revenue can not be negative");
        }

        this.totalRevenueInCents = totalRevenueInCents;
    }

    public List<App> getApps()
    {
        return new ArrayList<>(this.apps);
    }

    public void setApps(List<App> apps)
    {
        if (apps == null)
        {
            throw new IllegalArgumentException("apps cannot be null");
        }

        for (App app : apps)
        {
            if (app == null)
            {
                throw new IllegalArgumentException("An app from apps cannot be null");
            }
        }

        this.apps = new ArrayList<>(apps);
    }

    public List<User> getUsers()
    {
        return new ArrayList<>(this.users);
    }

    public void setUsers(List<User> users)
    {
        if (users == null)
        {
            throw new IllegalArgumentException("users cannot be null");
        }

        for (User user : users)
        {
            if (user == null)
            {
                throw new IllegalArgumentException("A user from users cannot be null");
            }
        }

        this.users = new ArrayList<>(users);
    }

    public List<Purchase> getPurchases()
    {
        return new ArrayList<>(this.purchases);
    }

    public void setPurchases(List<Purchase> purchases)
    {
        if (purchases == null)
        {
            throw new IllegalArgumentException("purchases cannot be null");
        }

        for (Purchase purchase : purchases)
        {
            if (purchase == null)
            {
                throw new IllegalArgumentException("A purchase from purchases cannot be null");
            }
        }

        this.purchases = new ArrayList<>(purchases);
    }

    public Map<String, Integer> getAppRevenueInCents()
    {
        return new HashMap<>(this.appRevenueInCents);
    }

    public void setAppRevenueInCents(Map<String, Integer> appRevenueInCents)
    {
        if (appRevenueInCents == null)
        {
            throw new IllegalArgumentException("App revenue in cents can not be null!");
        }
        for (String s : appRevenueInCents.keySet())
        {
            if (s.isBlank())
            {
                throw new IllegalArgumentException("The name of the app can not be empty!");
            }
        }
        for (Integer v : appRevenueInCents.values())
        {
            if (v < 0)
            {
                throw new IllegalArgumentException("The value of a game can not be less than 0");
            }
        }

        this.appRevenueInCents = appRevenueInCents;
    }

    public abstract void uploadApp(App app);

    public void purchaseApp(App app, User user)
    {
        if (user == null)
        {
            throw new IllegalArgumentException("The user can not be null!");
        }
        if (app == null)
        {
            throw new IllegalArgumentException("App can not be null");
        }
        checkForDuplicatePurchase(user, app);
        checkAppExistence(app);
        verifyAge(user, app);
        downloadApp(user, app);
        totalRevenueInCents += getPriceAfterCededRevenueInCents(app);
        if (appRevenueInCents.containsKey(app.getName()))
        {
            int temp = appRevenueInCents.get(app.getName());
            temp += getPriceAfterCededRevenueInCents(app);
            appRevenueInCents.put(app.getName(), temp);
        }
        else
        {
            appRevenueInCents.put(app.getName(), getPriceAfterCededRevenueInCents(app));
        }
        Purchase purchase = new Purchase(user, app);
        purchases.add(purchase);
    }

    public void verifyAge(User user, App app)
    {
        int ageOfUser = user.getAge();
        if (app.hasNudity() && ageOfUser < AGE_RESTRICTION_NUDITY)
        {
            throw new DownloadNotAllowedException("App contains nudity. You must be 18 or older!");
        }
        if (app.hasViolence() && ageOfUser < AGE_RESTRICTION_VIOLENCE)
        {
            throw new DownloadNotAllowedException("App contains violence. You must be 16 or older!");
        }
    }

    public int getPriceAfterCededRevenueInCents(App app)
    {
        return app.getPriceInCents() - app.getPriceInCents() * CEDE_REVENUE_TO_DEV_IN_PERCENT / 100;
    }

    public void downloadApp(User user, App app)
    {
        // user and app are already checked
        // this is a helper method for purhcaseApp()
        user.addApp(app);
    }

    public int getRevenueOfAppInCents(App app)
    {
        if (app == null)
        {
            throw new IllegalArgumentException("App can not be null!");
        }
        if (appRevenueInCents.containsKey(app))
        {
            throw new IllegalArgumentException("The app has not been purchased and is not in this system");
        }

        return appRevenueInCents.get(app);
    }

    public void checkAppExistence(App app)
    {
        boolean appExists = false;
        for (App existingApp : apps)
        {
            if (app.equals(existingApp))
            {
                appExists = true;
                break;
            }
        }
        if (!appExists)
        {
            throw new IllegalArgumentException("The app does not exist in this app store");
        }
    }

    public void checkForDuplicatePurchase(User user, App app)
    {
        if (user.getApps().contains(app))
        {
            throw new IllegalArgumentException("The user already has the app purchased!");
        }
    }
}
