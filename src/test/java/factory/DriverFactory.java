package factory;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class DriverFactory {
    public static WebDriver createDriver(String mode) {
        WebDriverManager.firefoxdriver().setup();
        FirefoxOptions options = new FirefoxOptions();

        switch (mode) {
            case "headless":
                options.addArguments("--headless");
                break;
            case "kiosk":
                options.addArguments("--kiosk");
                break;
            case "fullscreen":
                options.addArguments("--start-fullscreen");
                break;
            default:
                throw new IllegalArgumentException("Неизвестный режим: " + mode);
        }

        return new FirefoxDriver(options);
    }
}

