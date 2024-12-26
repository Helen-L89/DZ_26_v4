package tests;

import factory.DriverFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.ConfigManager;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HeadlessModeTest {

    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        driver = DriverFactory.createDriver("headless");
    }

    /**
     * Метод для закрытия драйвера после каждого теста.
     */
    @AfterEach
    public void tearDown() {
        if (driver != null) { // Проверяем, что драйвер был создан
            driver.quit();
        }
    }

    @Test
    public void testInputField() {
        // Получение базового URL из файла конфигурации
        String baseUrl = ConfigManager.get("base.url");
        driver.get(baseUrl + "/training.html");

        // Найти поле ввода и ввести текст
        WebElement inputField = driver.findElement(By.id("textInput"));
        inputField.sendKeys("ОТУС");

        // Проверка текста
        assertEquals("ОТУС", inputField.getAttribute("value"), "Текст не соответствует ожидаемому");
    }
}