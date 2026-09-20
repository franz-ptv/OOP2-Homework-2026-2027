package com.nhlstenden.appstores;

import java.util.List;

public class GooglePlayStore extends AppStore
{
    public GooglePlayStore(String currency)
    {
        super(currency);
    }

    @Override
    public void uploadApp(App app)
    {
        if (app == null)
        {
            throw new IllegalArgumentException("The app can not be null!");
        }
        for (App existingApp : getApps())
        {
            if (existingApp.equals(app))
            {
                throw new IllegalArgumentException("There is such an app already uploaded on this store");
            }
        }

        List<App> updatingAppsList = getApps();
        updatingAppsList.add(app);
        setApps(updatingAppsList);
    }
}
