import com.thoughtworks.gauge.Logger;
import com.thoughtworks.gauge.Step;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;

import java.time.Duration;
import java.util.List;
import java.util.Random;


public class BasePage extends BaseTest {

    @Step("<int> saniye kadar bekle")
    public void waitForSecond(int s) throws InterruptedException {
        Thread.sleep(1000 * s);
        Logger.info("App aciliyor");
    }


    @Step("Id = <id> olan elementi bul ve tıkla")
    public void startShopping(String id) {
        appiumDriver.findElement(By.id(id)).click();
    }

    @Step("Kategori Id = <id> olan elementi bul ve tıkla")
    public void clickCategoryId(String id) {
        appiumDriver.findElement(By.id(id)).click();
    }

    @Step("Xpath = <xpath> olan elementi bul ve tıkla")
    public void clickCategoriesWoman(String xpath) {
        appiumDriver.findElement(By.xpath(xpath)).click();
    }

    @Step("Sayfayı aşağı doğru kaydır")
    public void swipeDown() {
        final int ANIMATION_TIME = 200; // ms
        final int PRESS_TIME = 200; // ms
        int edgeBorder = 10; // better avoid edges
        PointOption pointOptionStart, pointOptionEnd;

        Dimension dims = appiumDriver.manage().window().getSize();
        System.out.println("Telefon Ekran Boyutu " + dims);

        pointOptionStart = PointOption.point(dims.width / 2, dims.height / 2);
        System.out.println("Baslangıc noktasi " + pointOptionStart);
        pointOptionEnd = PointOption.point(dims.width / 2, dims.height / 3);
        System.out.println("Bitis noktasi " + pointOptionEnd);
        try {
            new TouchAction(appiumDriver)
                    .press(pointOptionStart)
                    // a bit more reliable when we add small wait
                    .waitAction(WaitOptions.waitOptions(Duration.ofMillis(PRESS_TIME)))
                    .moveTo(pointOptionEnd)
                    .release().perform();
        } catch (Exception e) {
            System.err.println("swipeScreen(): TouchAction FAILED\n" + e.getMessage());
            return;
        }

        try {
            Thread.sleep(ANIMATION_TIME);
        } catch (InterruptedException e) {

        }
    }


    @Step("Xpath = <xpath> olan elementi bul ve tıkla.")
    public void childCategoriesPant(String xpath) {
        appiumDriver.findElement(By.xpath(xpath)).click();
    }

    @Step("Sayfanın en aşağısına iki defa scroll et")
    public void scrollDown() {
        final int ANIMATION_TIME = 200; // ms
        final int PRESS_TIME = 200; // ms
        PointOption pointOptionStart, pointOptionEnd;

        Dimension dims = appiumDriver.manage().window().getSize();
        System.out.println("Telefon Ekran Boyutu " + dims);

        for (int i = 0; i < 2; i++) {
            pointOptionStart = PointOption.point(dims.width / 2, dims.height / 2);
            System.out.println("Baslangıc noktasi " + pointOptionStart);
            pointOptionEnd = PointOption.point(dims.width / 2, dims.height / 6);
            System.out.println("Bitis noktasi " + pointOptionEnd);
            try {
                new TouchAction(appiumDriver)
                        .press(pointOptionStart)
                        // a bit more reliable when we add small wait
                        .waitAction(WaitOptions.waitOptions(Duration.ofMillis(PRESS_TIME)))
                        .moveTo(pointOptionEnd)
                        .release().perform();
            } catch (Exception e) {
                System.err.println("swipeScreen(): TouchAction FAILED\n" + e.getMessage());
                return;
            }

            try {
                Thread.sleep(ANIMATION_TIME);
            } catch (InterruptedException e) {

            }
        }
    }

    @Step("Xpath = <xpath> olan elementi bul ve tıkla..")
    public void randomSelectProduct(String xpath) {
        Random random = new Random();
        MobileElement recyclerViewElement = appiumDriver.findElement(By.xpath(xpath));
        List<MobileElement> bindViewElements = recyclerViewElement.findElements(By.xpath("//*[@class=\"android.widget.FrameLayout\"]"));

        int index = random.nextInt(bindViewElements.size() - 1);
        bindViewElements.get(index).click();
    }

    @Step("<id> elementinin sayfada gorunur oldugunu kontrol et")
    public void findByelement(String id) {
        MobileElement element = appiumDriver.findElement(By.id(id));
        if (element.isDisplayed()) {
            System.out.println("Element gorunur oldu.");
        } else {
            System.out.println("Element gorunur degıl.");
        }
    }

    @Step("Id = <id> olan elemente tıkla ve <id2> li acılan sayfayı dogrula")
    public void clickAndfindByelement(String id, String id2) {
        appiumDriver.findElement(By.id(id)).click();
        MobileElement element = appiumDriver.findElement(By.id(id2));
        if (element.isDisplayed()) {
            System.out.println("Element gorunur oldu.");
        } else {
            System.out.println("Element gorunur degıl.");
        }
    }

    @Step("Id = <id> olan elementi bul ve <username> degerini yaz")
    public void clickEmail(String id, String username) {

        MobileElement emailElement = appiumDriver.findElement(By.id(id));
        emailElement.click();
        emailElement.sendKeys(username);

    }

    @Step("Id = <id> olan elementi bul ve <password> degerini yaz.")
    public void clickPassword(String id, String password) {
        MobileElement passwordElement = appiumDriver.findElement(By.id(id));
        passwordElement.click();
        passwordElement.sendKeys(password);

    }

    @Step("Geri butonuna iki kere tıkla")
    public void clickBackPage() throws InterruptedException {
        for (int i = 0; i < 2; i++) {
            appiumDriver.findElement(By.id("com.ozdilek.ozdilekteyim:id/ivBack")).click();
            Thread.sleep(2000);
        }
    }

    @Step("Xpath = <xpath> olan elementi bul ve tıkla...")
    public void randomSelectProduct2(String xpath) {
        Random random = new Random();
        MobileElement recyclerViewElement = appiumDriver.findElement(By.xpath(xpath));
        List<MobileElement> bindViewElements = recyclerViewElement.findElements(By.xpath("//*[@class=\"android.widget.FrameLayout\"]"));

        int index = random.nextInt(bindViewElements.size() - 1);
        bindViewElements.get(index).click();
        appiumDriver.findElement(By.id("com.ozdilek.ozdilekteyim:id/cardAddToCart")).click();

    }
}
