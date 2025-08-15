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
    public void farmImage0IsVisible() {
        page.navigate("http://localhost:8080");

        Locator farmImage = page.locator("div.home-images img").nth(0);
        assertThat(farmImage)
                .hasAttribute("src", "https://images.unsplash.com/photo-1500595046743-cd271d694d30?q=80&w=1774&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D");
        assertThat(farmImage).
                hasAttribute("alt", "Cows and calf on the field during golden hour");
    }
    @Test
    public void farmImage1IsVisible() {
        page.navigate("http://localhost:8080");

        Locator farmImage = page.locator("div.home-images img").nth(1);
        assertThat(farmImage)
                .hasAttribute("src", "https://images.unsplash.com/photo-1471193945509-9ad0617afabf?q=80&w=1770&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D");
        assertThat(farmImage)
                .hasAttribute("alt", "Fresh carrot and leak");
    }

    @Test
    public void InfoBox0ImageIsVisibleAndHasTitleAndDisplaysTextBeneath() {
        page.navigate("http://localhost:8080");

        Locator infoImage = page.locator("div.usp-item img").nth(0);
        assertThat(infoImage)
                .hasAttribute("src","https://plus.unsplash.com/premium_photo-1661963367713-b85abde75a23?q=80&w=1770&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D");
        assertThat(infoImage)
                .hasAttribute("alt","Nature & sustainability");

        Locator infoTitle = page.locator("div.usp-item h3").nth(0);
        assertThat(infoTitle)
                .containsText("Nature & Sustainability");

        Locator infoText = page.locator("div.usp-item p").nth(0);
        assertThat(infoText).containsText("We farm with biodiversity in mind—wildflower meadows, low-intensity methods, and soil-first practices. Our goal is to protect and restore the land so it can thrive for generations to come.");
    }

    @Test
    public void InfoBox1ImageIsVisibleAndHasTitleAndDisplaysTextBeneath() {
        page.navigate("http://localhost:8080");

        Locator infoImage = page.locator("div.usp-item img").nth(1);
        assertThat(infoImage)
                .hasAttribute("src","https://media.istockphoto.com/id/1241482623/photo/assorted-of-dairy-product-with-milk-butter-cheese.jpg?s=1024x1024&w=is&k=20&c=KpXSjrHeiRhL5JN41BVfbHOaz8hsYU3a7ToFdvWtntI=");
        assertThat(infoImage)
                .hasAttribute("alt","Organic dairy");

        Locator infoTitle = page.locator("div.usp-item h3").nth(1);
        assertThat(infoTitle)
                .containsText("Organic Dairy");

        Locator infoText = page.locator("div.usp-item p").nth(1);
        assertThat(infoText).containsText("Certified organic milk, yoghurt and cheese—pure, simple, and produced with animal welfare at heart. Every product is made with care, from grass-fed cows to your kitchen table.");
    }

    @Test
    public void InfoBox2ImageIsVisibleAndHasTitleAndDisplaysTextBeneath() {
        page.navigate("http://localhost:8080");

        Locator infoImage = page.locator("div.usp-item img").nth(2);
        assertThat(infoImage)
                .hasAttribute("src","https://images.unsplash.com/photo-1695654392283-4ea0fa0b4bb6?q=80&w=1769&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D");
        assertThat(infoImage)
                .hasAttribute("alt","Fresh delivery");

        Locator infoTitle = page.locator("div.usp-item h3").nth(2);
        assertThat(infoTitle)
                .containsText("Delivered Fresh To Your Door");

        Locator infoText = page.locator("div.usp-item p").nth(2);
        assertThat(infoText).containsText("Carefully packed, chilled when needed, and delivered nationwide in recyclable packaging. We make sure your order arrives as fresh as the moment it left the farm.");
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

    @Test
    public void farmImage2IsVisible() {
        page.navigate("http://localhost:8080");

        Locator farmImage = page.locator("div.home-images img").nth(2);
        assertThat(farmImage)
                .hasAttribute("src", "https://kentdowns.org.uk/wp-content/uploads/2025/02/Untitled-design-96-1024x620-1-aspect-ratio-760-400.png");
        assertThat(farmImage)
                .hasAttribute("alt", "Cows and calf on the field during golden hour");
    }

    @Test
    public void farmImage3IsVisible() {
        page.navigate("http://localhost:8080");

        Locator farmImage = page.locator("div.home-images img").nth(3);
        assertThat(farmImage)
                .hasAttribute("src", "https://assets.farmsanctuary.org/content/uploads/2025/08/03093305/b82704a9-c906-4430-be2b-52c7d298d223.jpg");
        assertThat(farmImage)
                .hasAttribute("alt", "Fresh carrot and leak");
    }
}





