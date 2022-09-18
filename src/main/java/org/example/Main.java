package org.example;

import org.checkerframework.checker.units.qual.C;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v85.emulation.Emulation;

import java.util.Optional;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "D:\\MP\\Automation Test\\Selenium_Tutorial\\chromedriver.exe");

        ChromeDriver driver = new ChromeDriver();

        DevTools devTools = driver.getDevTools();

        devTools.createSession();

        //send commands to CDP Methods -> CDP Methods will invoke and get access to dev-tool
        devTools.send(Emulation.setDeviceMetricsOverride(600, 1000, 50, true, Optional.empty(),
                Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(),
                 Optional.empty(), Optional.empty()));

        String baseURL = "https://www.memoryexpress.com/";
        driver.get(baseURL);

        Thread.sleep(10000);
        driver.close();
    }
}