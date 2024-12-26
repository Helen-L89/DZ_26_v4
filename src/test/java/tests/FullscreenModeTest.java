package tests;

import factory.DriverFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class FullscreenModeTest {

    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        // Создание драйвера перед каждым тестом
        driver = DriverFactory.createDriver("fullscreen");
    }

    @Test
    public void testFormSubmission() {
        try {
            // Получение базового URL из файла конфигурации
            String baseUrl = utils.ConfigManager.get("base.url");
            driver.get(baseUrl + "/training.html");

            // Заполнение формы
            WebElement nameField = driver.findElement(By.id("name"));
            WebElement emailField = driver.findElement(By.id("email"));
            WebElement submitButton = driver.findElement(By.cssSelector("button[type='submit']")); // Поиск кнопки

            nameField.sendKeys("Иван");
            emailField.sendKeys("ivan@example.com");
            submitButton.click();

            // Проверка сообщения об отправке
            WebElement successMessage = driver.findElement(By.id("messageBox"));
            assertTrue(successMessage.isDisplayed(), "Сообщение об успехе не отображается");
            assertTrue(successMessage.getText().contains("Форма отправлена с именем: Иван и email: ivan@example.com"),
                    "Сообщение не соответствует ожидаемому формату");
        } catch (Exception e) {
            e.printStackTrace();
            throw e; // Пробрасываем исключение, если тест не прошел
        }
    }

    @AfterEach
    public void tearDown() {
        // Проверяем, был ли создан объект драйвера, и только после этого закрываем
        if (driver != null) {
            driver.quit();
        }
    }
}