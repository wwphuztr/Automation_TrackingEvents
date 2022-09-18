package org.example;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v85.network.Network;
import org.openqa.selenium.devtools.v85.network.model.Response;

import java.util.Optional;

public class NetworkMockingCDP {
    public static void main(String[] args) throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "D:\\MP\\Automation Test\\Selenium_Tutorial\\chromedriver.exe");

        ChromeDriver driver = new ChromeDriver();

        DevTools devTools = driver.getDevTools();
        devTools.createSession();
        devTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));

        //Event will get fired
        devTools.addListener(Network.responseReceived(), response -> {

            Response res = response.getResponse();
            if (res.getUrl().contains("png")) {
                System.out.println(res.getUrl());
                System.out.println(res.getStatus());
            }
        });

        String baseURL = "https://www.memoryexpress.com/";
        driver.get(baseURL);

        Thread.sleep(10000);
        driver.close();
    }
}