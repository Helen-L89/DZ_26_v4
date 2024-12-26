package tests;

import factory.DriverFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.ConfigManager;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class KioskModeTest {

    private WebDriver driver;


    @BeforeEach
    public void setUp() {
        driver = DriverFactory.createDriver("kiosk");
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
    public void testModalWindow() {
        // Получение базового URL из файла конфигурации
        String baseUrl = ConfigManager.get("base.url");
        driver.get(baseUrl + "/training.html");

        // Нажать на "Открыть модальное окно"
        WebElement openModalButton = driver.findElement(By.id("openModalBtn"));
        openModalButton.click();

        // Проверить, что модальное окно открылось
        WebElement modalWindow = driver.findElement(By.id("myModal"));
        assertTrue(modalWindow.isDisplayed(), "Модальное окно не отображается");
    }
}
