package thanhnv;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class FlickerCrawler {

    private List<String> listPhoto = new ArrayList<>();
    private WebDriver driver;

    public List<String> getListPhoto() {
        return listPhoto;
    }

    public void initDriver() {
        System.setProperty(AppConstant.GOOGLE_DRIVER_NAME, AppConstant.GOOGLE_DRIVER_PATH);
        driver = new ChromeDriver();
    }

    public void waitForPageLoaded() {
        ExpectedCondition<Boolean> expectation = new
                ExpectedCondition<Boolean>() {
                    public Boolean apply(WebDriver driver) {
                        return ((JavascriptExecutor) driver).executeScript("return document.readyState").toString().equals("complete");
                    }
                };
        try {
            Thread.sleep(1000);
            WebDriverWait wait = new WebDriverWait(driver, 30);
            wait.until(expectation);
        } catch (Throwable error) {
            System.out.printf("Timeout waiting for Page Load Request to complete.");
        }
    }

    public void crawlPhotoLinkDownload(String url) throws InterruptedException {
        if (driver != null) {
            driver.get(url);

            waitForPageLoaded();
            WebElement downloadButton = driver.findElement(By.xpath("/html/body/div[1]/div/div[2]/div/div[5]/div[3]"));

            if (downloadButton != null) {
                downloadButton.click();
            }


            String originalSizeLinkk = driver.findElement(By.xpath("/html/body/div[3]/div/div[2]/div/ul/li[5]/a")).getAttribute("href");

            if (originalSizeLinkk != null) {
                listPhoto.add(originalSizeLinkk);
                System.out.println("Photo Link");
            }

            WebElement overlayElement = driver.findElement(By.xpath("/html/body/div[4]"));
            overlayElement.click();

            WebElement nextButton = driver.findElement(By.xpath("/html/body/div[1]/div/div[2]/div/a[2]"));

            crawlPhotoLinkDownload(nextButton.getAttribute("href"));
        } else {
            System.out.println("DRIVER NOT INIT");
        }
    }
}
