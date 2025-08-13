package com.example.freshFarm;

import com.microsoft.playwright.*;
import org.junit.jupiter.api.*;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

class FooterTests {
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
    public void footerIsShown() {
        page.navigate("http://localhost:8080");
        Locator footer = page.locator("footer");
        assertThat(footer).isVisible();
    }

    @Test
    public void CorrectTextWithinFooter() {
        page.navigate("http://localhost:8080");

        Locator footer = page.locator("footer p").nth(1);
        assertThat(footer)
                .containsText("Powered by Stripe");

        Locator _footer = page.locator("footer p").nth(0);
        assertThat(_footer)
                .containsText("© 2025 Fresh Farm Organics. All rights reserved.");
    }
}
