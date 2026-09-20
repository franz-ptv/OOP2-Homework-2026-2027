package com.nhlstenden.appstores;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class AppStoreTest
{
    private AppleAppStore appleAppStore;
    private GooglePlayStore googlePlayStore;
    private User kris;
    private User franz;
    private User baby;
    private App brawlStars;
    private App duolingo;
    private App leagueOfLegends;
    private App gta;
    private App minecraft;
    private EmailValidator emailValidator;

    @BeforeEach
    void setUp()
    {
        appleAppStore = new AppleAppStore("dollar");
        googlePlayStore = new GooglePlayStore("euro");
        kris = new User("Kris", "kriskata@gay.com", LocalDate.of(2006, 7, 31));
        franz = new User("Franz", "franz@gmail.com", LocalDate.of(2006, 5, 29));
        baby = new User("Baby", "", LocalDate.now().minusYears(1));
        brawlStars = new App("Brawl Stars", 0, false, false);
        duolingo = new App("Duolingo", 0, false, false);
        leagueOfLegends = new App("League of Legends", 0, true, false);
        gta = new App("GTA V", 60, true, true);
        minecraft = new App("Minecraft", 25, false, false);
        emailValidator = new EmailValidator();

        googlePlayStore.uploadApp(brawlStars);
        googlePlayStore.uploadApp(duolingo);
        googlePlayStore.uploadApp(leagueOfLegends);
        googlePlayStore.uploadApp(gta);
        googlePlayStore.uploadApp(minecraft);

        appleAppStore.uploadApp(brawlStars);
        appleAppStore.uploadApp(duolingo);
        appleAppStore.uploadApp(leagueOfLegends);
        appleAppStore.uploadApp(minecraft);
    }

    @Test
    void purchaseApp_validAppPurchase_DoesNotThrow()
    {
        assertDoesNotThrow(() -> appleAppStore.purchaseApp(brawlStars, franz));
    }

    @Test
    void purchaseApp_underageAppPurchase_ThrowsDownloadNotAllowed()
    {
        assertThrows(DownloadNotAllowedException.class, () -> googlePlayStore.purchaseApp(gta, baby));
    }

    @Test
    void purchaseApp_appPurchasedFor60euro_revenueShouldBe42()
    {
        googlePlayStore.purchaseApp(gta, franz);
        assertEquals(42, googlePlayStore.getTotalRevenueInCents());
    }

    @Test
    void getRevenue_noAppsPurchased_noRevenue()
    {
        assertEquals(0, appleAppStore.getTotalRevenueInCents());
    }

    @Test
    void purchaseApp_attemptedDuplicatePurchase_expectsToThrow()
    {
        googlePlayStore.purchaseApp(minecraft, franz);
        assertThrows(IllegalArgumentException.class, () -> googlePlayStore.purchaseApp(minecraft, franz));
    }

    @Test
    void purchaseApp_validAppPurchaseAdultAppWithViolenceAndNudity_doesNotThrow()
    {
        assertDoesNotThrow(() -> googlePlayStore.purchaseApp(gta, franz));
    }

}
