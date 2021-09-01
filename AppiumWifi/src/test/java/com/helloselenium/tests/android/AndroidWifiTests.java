package com.helloselenium.tests.android;

import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class AndroidWifiTests {

    private static AndroidDriver driver;

    public static void hardwait(int sec) {
        //hard wait
        try {
            Thread.sleep(sec * 1000);
        } catch (InterruptedException e) {
        }
    }

    @BeforeClass
    public void before() {

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("deviceName", "device");
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("platformVersion", "10");
        capabilities.setCapability("udid", "D0AA002295JA1605108");
        capabilities.setCapability("adbExecTimeout", 60000);
        capabilities.setCapability("appPackage", "com.android.chrome");
        capabilities.setCapability("appActivity", "com.google.android.apps.chrome.Main");

        try {
            driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        driver.manage().timeouts().implicitlyWait(80, TimeUnit.SECONDS);
    }

    @Test(priority = 1, description = "Wifi OFF")
    public void wifiOffTest() {
        //toggle wifi
        driver.toggleWifi();
        hardwait(2);
        Assert.assertFalse(driver.getConnection().isWiFiEnabled());
        driver.findElement(MobileBy.AndroidUIAutomator("new UiSelector().textContains(\"Accept\")")).click();
    }

    @Test(priority = 2, description = "Wifi ON")
    public void wifiOnTest() {
        //toggle wifi
        driver.toggleWifi();
        hardwait(2);
        Assert.assertTrue(driver.getConnection().isWiFiEnabled());
        driver.findElement(MobileBy.AndroidUIAutomator("new UiSelector().textContains(\"Accept\")")).click();
    }

    @AfterClass
    public void after() {
        driver.quit();
    }
}
