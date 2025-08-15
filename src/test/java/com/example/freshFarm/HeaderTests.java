import com.microsoft.playwright.*;
import org.junit.jupiter.api.*;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

class HeaderTests {
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
    public void HeaderIsShown() {
        page.navigate("http://localhost:8080");
        Locator header = page.locator("header");
        assertThat(header).isVisible();
    }

    @Test
    public void TitleIsShown() {
        page.navigate("http://localhost:8080");

        Locator headerTitle = page.locator("header h1");
        assertThat(headerTitle)
                .containsText("Fresh Farm");

        Locator headerTitle2 = page.locator("header h2");
        assertThat(headerTitle2)
                .containsText("Organics");
    }

    @Test
    public void SloganIsShown() {
        page.navigate("http://localhost:8080");

        Locator slogan = page.locator("header h3");
        assertThat(slogan)
                .containsText("Your favourite shop");
    }

    @Test
    public void ourStoryLinkWorks() {
        page.navigate("http://localhost:8080");
        Locator link = page.locator(".main-nav a").nth(0);
        assertThat(link).hasAttribute("href", "/");
        assertThat(link).containsText("Our Story");
    }

    @Test
    public void fruitsLinkWorks() {
        page.navigate("http://localhost:8080");
        Locator link = page.locator(".main-nav a").nth(2);
        assertThat(link).hasAttribute("href", "/fruits");
        assertThat(link).containsText("Fruits");
    }

    @Test
    public void vegetablesLinkWorks() {
        page.navigate("http://localhost:8080");
        Locator link = page.locator(".main-nav a").nth(3);
        assertThat(link).hasAttribute("href", "/vegetables");
        assertThat(link).containsText("Vegetables");
    }

    @Test
    public void milkLinkWorks() {
        page.navigate("http://localhost:8080");
        Locator link = page.locator(".main-nav a").nth(5);
        assertThat(link).hasAttribute("href", "/milk");
        assertThat(link).containsText("Milk");
    }

    @Test
    public void cheeseLinkWorks() {
        page.navigate("http://localhost:8080");
        Locator link = page.locator(".main-nav a").nth(6);
        assertThat(link).hasAttribute("href", "/cheese");
        assertThat(link).containsText("Cheese");
    }

    @Test
    public void yoghurtLinkWorks() {
        page.navigate("http://localhost:8080");
        Locator link = page.locator(".main-nav a").nth(7);
        assertThat(link).hasAttribute("href", "/yoghurt");
        assertThat(link).containsText("Yoghurt");
    }

    @Test
    public void boxesLinkWorks() {
        page.navigate("http://localhost:8080");
        Locator link = page.locator(".main-nav a").nth(8);
        assertThat(link).hasAttribute("href", "/boxes");
        assertThat(link).containsText("Pre-set Boxes");
    }

    @Test
    public void contactusLinkWorks() {
        page.navigate("http://localhost:8080");
        Locator link = page.locator(".main-nav a").nth(10);
        assertThat(link).hasAttribute("href", "/contact_us");
        assertThat(link).containsText("Contact Us");
    }

    @Test
    public void FAQLinkWorks() {
        page.navigate("http://localhost:8080");
        Locator link = page.locator(".main-nav a").nth(11);
        assertThat(link).hasAttribute("href", "/faq");
        assertThat(link).containsText("FAQ");
    }

    @Test
    public void CartLinkWorks() {
        page.navigate("http://localhost:8080");
        Locator link = page.locator(".main-nav a").nth(12);
        assertThat(link).hasAttribute("href", "/cart");
    }

    @Test
    public void ProfileLinkWorks() {
        page.navigate("http://localhost:8080");
        Locator link = page.locator(".main-nav a").nth(13);
        assertThat(link).hasAttribute("href", "/profile");
    }
}
