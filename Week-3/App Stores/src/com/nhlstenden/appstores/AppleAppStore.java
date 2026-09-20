package com.nhlstenden.appstores;

import java.util.List;

public class AppleAppStore extends AppStore
{

    public AppleAppStore(String currency)
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
        if (app.hasNudity())
        {
            throw new IllegalArgumentException("App containing nudity can not be uploaded on the Apple App store cause we are lame. Switch to android!");
        }

        List<App> updatingAppsList = getApps();
        updatingAppsList.add(app);
        setApps(updatingAppsList);
    }
}
