package org.example;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v85.network.Network;
import org.openqa.selenium.devtools.v85.network.model.Request;
import org.openqa.selenium.devtools.v85.network.model.Response;
import org.openqa.selenium.devtools.v85.page.Page;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static java.awt.SystemColor.text;

public class NetworkMockingCDP {
    private static String command = null;
    private static Map<String, Object> params = new HashMap<>();
    private static String script = null;
    private static Map<String, Object> result = new HashMap<>();

    public static void main(String[] args) throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "D:\\MP\\Automation Test\\Selenium_Tutorial\\chromedriver.exe");

        ChromeDriver driver = new ChromeDriver();

        DevTools devTools = driver.getDevTools();
        devTools.createSession();
        devTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));

        //Event will get fired
        devTools.addListener(Network.responseReceived(), response -> {

            Response res = response.getResponse();
            if (res.getUrl().contains("stg")) {
                System.out.println(res.getUrl());
                System.out.println(res.getStatus());
            }
        });

        devTools.addListener(Network.requestWillBeSent(), request -> {

            Request req = request.getRequest();
            if (req.getUrl().contains("stg")) {
                System.out.println(req.getUrl());
            }
        });

        command = "Page.addScriptToEvaluateOnNewDocument";
        params = new HashMap<>();
        script = "if (window.self === window.top) {\n" +
                "var currentScript = document.querySelector('[wid]')\n" +
                "currentScript && currentScript.remove()\n" +
                "var scriptTag = document.createElement('script');\n" +
                "scriptTag.setAttribute('wid', 'f772eddb-a214-ed11-aaf7-061f6a8be99c');\n" +
                "scriptTag.setAttribute('src','https://cdn.stg.p-a.io/js/me/t.js');\n" +
                "scriptTag.defer = true;\n" +
                "window.onload = function() {\n" +
                "  document.head.appendChild(scriptTag);\n" +
                "}\n" +
                "}\n" +
                "localStorage.setItem('pa-islop',true)";
        params.put("source", script);
        result = driver.executeCdpCommand(command, params);

        String baseURL = "https://www.memoryexpress.com/Products/MX00119587";
        driver.get(baseURL);

        Thread.sleep(2000);
        driver.close();

    }
}