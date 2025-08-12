package com.example.freshFarm.extractor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.time.Duration;
import java.util.*;

public class ProductScraper {

    private static final String[] urls = {
            "https://thevegboxcompany.co.uk/fruit-veg-salad/fruit/",
            "https://thevegboxcompany.co.uk/fruit-veg-salad/veg/",
            "https://thevegboxcompany.co.uk/fruit-veg-boxes/mixed-boxes/"
    };

    public static void main(String[] args) {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        WebDriver driver = new ChromeDriver(options);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        List<Map<String, String>> allProducts = new ArrayList<>();

        for (String url : urls) {
            System.out.println("\nScraping: " + url);
            driver.get(url);

            // Wait until at least one product panel is loaded
            try {
                wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div.panel")));
            } catch (Exception e) {
                System.out.println("Timed out waiting for content at: " + url);
                continue;
            }

            // Determine category based on URL
            String category = "unknown";
            if (url.endsWith("/fruit/")) {
                category = "fruit";
            } else if (url.endsWith("/veg/")) {
                category = "vegetable";
            } else if (url.contains("mixed-boxes")) {
                category = "packageBox";
            }

            List<WebElement> products = driver.findElements(By.cssSelector("div.panel"));

            for (WebElement product : products) {
                try {
                    String name = product.findElement(By.cssSelector("div.data-title")).getText();
                    String price = product.findElement(By.cssSelector("div.data-price")).getText();
                    String imageUrl = product.findElement(By.tagName("img")).getAttribute("src");

                    Map<String, String> productData = new HashMap<>();
                    productData.put("productName", name);
                    productData.put("unitPrice", price);
                    productData.put("category", category);
                    productData.put("imageUrl", imageUrl);

                    allProducts.add(productData);

                } catch (Exception e) {
                    System.out.println("Skipping one product due to missing data.");
                }
            }
        }

        driver.quit();

        // Write to JSON file
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.enable(SerializationFeature.INDENT_OUTPUT); // pretty print
            mapper.writeValue(new File("src/main/resources/data/freshFarm_products.json"), allProducts);
            System.out.println("Saved " + allProducts.size() + " products to freshFarm_products.json");
        } catch (Exception e) {
            System.out.println("Failed to write JSON file: " + e.getMessage());
        }
    }
}