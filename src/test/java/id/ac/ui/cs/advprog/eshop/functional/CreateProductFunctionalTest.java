package id.ac.ui.cs.advprog.eshop.functional;

import io.github.bonigarcia.seljup.SeleniumJupiter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@ExtendWith(SeleniumJupiter.class)
public class CreateProductFunctionalTest {

    @LocalServerPort
    private int serverPort;

    @Value("${app.baseUrl:http://localhost}")
    private String testBaseUrl;

    private String baseUrl;

    @BeforeEach
    void setUp() {
        baseUrl = String.format("%s:%d", testBaseUrl, serverPort);
    }

    // Test menuju halaman Create Product
    @Test
    void createProductPageIsCorrect(ChromeDriver driver) throws Exception {
        driver.get(baseUrl + "/product/create");
        String pageTitle = driver.getTitle();

        assertEquals("Create New Product", pageTitle);
    }

    // Test untuk cek keberhasilan dalam Create Product
    @Test
    void createProductPostIsCorrect(ChromeDriver driver) throws Exception {
        driver.get(baseUrl + "/product/create");
        driver.findElement(By.id("nameInput")).sendKeys("Sampo Cap Bambang");
        driver.findElement(By.id("quantityInput")).sendKeys("100");
        driver.findElement(By.id("submitButton")).click();
        String pageTitle = driver.getTitle();

        assertEquals("Product List", pageTitle);

        String productName = driver.findElement(By.xpath("//tbody/tr[1]/td[1]")).getText();
        String productQuantity = driver.findElement(By.xpath("//tbody/tr[1]/td[2]")).getText();

        assertEquals("Sampo Cap Bambang", productName);
        assertEquals("100", productQuantity);
    }

    // Test untuk memastikan quantity tidak bisa negatif
    //@Test
    void createProductWithNegativeQuantity(ChromeDriver driver) throws Exception {
        driver.get(baseUrl + "/product/create");
        driver.findElement(By.id("nameInput")).sendKeys("Sampo Cap Minus");
        driver.findElement(By.id("quantityInput")).sendKeys("-10");
        driver.findElement(By.id("submitButton")).click();
        String pageTitle = driver.getTitle();

        assertEquals("Product List", pageTitle);

        String productName = driver.findElement(By.xpath("//tbody/tr[1]/td[1]")).getText();
        String productQuantity = driver.findElement(By.xpath("//tbody/tr[1]/td[2]")).getText();

        assertEquals("Sampo Cap Minus", productName);
        assertEquals("0", productQuantity); // Pastikan quantity di-set ke 0
    }//
}
