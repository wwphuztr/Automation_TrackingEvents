package org.example;

import org.checkerframework.checker.units.qual.C;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v85.emulation.Emulation;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class CDP_CommandTest {
    public static void main(String[] args) throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "D:\\MP\\Automation Test\\Selenium_Tutorial\\chromedriver.exe");

        ChromeDriver driver = new ChromeDriver();

        DevTools devTools = driver.getDevTools();

        devTools.createSession();

        Map deviceMetrics = new HashMap();
        deviceMetrics.put("width", 600);
        deviceMetrics.put("height", 1000);
        deviceMetrics.put("deviceScaleFactor", 50);
        deviceMetrics.put("mobile", true);

        driver.executeCdpCommand("Emulation.setDeviceMetricsOverride", deviceMetrics);

        String baseURL = "https://www.memoryexpress.com/";
        driver.get(baseURL);

        Thread.sleep(10000);
        driver.close();
    }
}