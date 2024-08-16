import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.switchTo;
import static com.codeborne.selenide.files.DownloadActions.click;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.openqa.selenium.remote.tracing.EventAttribute.setValue;

import com.codeborne.selenide.Configuration;
import jdk.jfr.Name;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class selenideTest {

  @BeforeEach
  void setup() {
    open("http://demoqa.com/");
    Configuration.pageLoadStrategy = "eager";
    Configuration.browserSize = "1920x1080";
  }

  @CsvSource(value = {"Taras,test@bk.ru, mars, forest"})
  @ParameterizedTest(name = "Проверка заполнения полей userName,userEmail,currentAddress,permanentAddress")
  void CheckingTheFieldsUserNameUserEmailCurrentAddressPermanentAddress(String UserName,
      String UserEmail, String CurrentAddress, String PermanentAddress) {
    $x("//div[@class='category-cards']/div[position()=1]").click();
    $x("//li[@id='item-0']").click();
    $x("//input[@id='userName']").setValue(UserName);
    $x("//input[@id='userEmail']").setValue(UserEmail);
    $x("//textarea[@id='currentAddress']").setValue(CurrentAddress);
    $x("//textarea[@id='permanentAddress']").setValue(PermanentAddress);
    $x("///button[@id='submit']").scrollIntoView(true).click();
    $x("//button[@id='submit']").click();
    $x("//p[@id='name']").shouldHave(text("Taras"));
    $x("//p[@id='email']").shouldHave(text("test@bk.ru"));
    $x("//p[@id='currentAddress']").shouldHave(text("mars"));
    $x("//p[@id='permanentAddress']").shouldHave(text("forest"));
  }

  @Test
  @DisplayName("Открытие новой вкладки")
  void OpeningANewTab() {
    $x("//div[@class='category-cards']/div[position()=3]").click();
    $x("//span[text()='Browser Windows']").click();
    $x("//span[text()='Browser Windows']").click();
    $x("//button[text()='New Tab']").click();
    switchTo().window(1);
    $x("//h1[@id='sampleHeading']").shouldHave(text("This is a sample page"));
  }
}