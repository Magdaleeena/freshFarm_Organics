package com.example.freshFarm;

import com.microsoft.playwright.*;
import org.junit.jupiter.api.*;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

class LandingPageTests {
    static Playwright playwright;
    static Browser browser;
    BrowserContext context;
    Page page;

    @BeforeAll
    static void launchBrowser() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch();
    }

    @AfterAll
    static void closeBrowser() {
        playwright.close();
    }

    @BeforeEach
    void createContextAndPage() {
        context = browser.newContext();
        page = context.newPage();
    }

    @AfterEach
    void closeContext() {
        context.close();
    }
    @Test
    public void farmImageIsVisible() {
        page.navigate("http://localhost:8080");
        Locator farmImage = page.locator("section.home-image img");
        assertThat(farmImage).hasAttribute("src", "https://assets.savills.com/properties/GBYORUYOR250067/YOR250067_18_l_lis.jpg");
        assertThat(farmImage).hasAttribute("alt", "Farm and fields");
    }


    @Test
    public void pageHasCorrectStoryHeading() {
        page.navigate("http://localhost:8080");
        Locator pageBody = page.locator("section.about h2");
        assertThat(pageBody).containsText("About Us");
    }

    @Test
    public void aboutUsContainsText() {
        page.navigate("http://localhost:8080");
        Locator about = page.locator("section.about");
        assertThat(about).containsText("Fresh Farm Organics");
        assertThat(about).containsText("happy animals make better dairy");
    }
}





