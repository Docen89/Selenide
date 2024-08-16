import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.switchTo;
import static org.junit.jupiter.api.Assertions.assertEquals;

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
  }

  @CsvSource(value = {"Taras,test@bk.ru, mars, forest"})
  @ParameterizedTest(name = "Проверка заполнения полей userName,userEmail,currentAddress,permanentAddress")
  void CheckingTheFieldsUserNameUserEmailCurrentAddressPermanentAddress(String UserName,
      String UserEmail, String CurrentAddress, String PermanentAddress) {
    $(byXpath("//div[@class='card mt-4 top-card']")).click();
    $(byXpath("//li[@id='item-0']")).click();
    $(byXpath("//input[@id='userName']")).setValue(UserName);
    $(byXpath("//input[@id='userEmail']")).setValue(UserEmail);
    $(byXpath("//textarea[@id='currentAddress']")).setValue(CurrentAddress);
    $(byXpath("//textarea[@id='permanentAddress']")).setValue(PermanentAddress);
    $(byXpath("//button[@id='submit']")).click();
    $(byXpath("//p[@id='name']")).shouldHave(text("Taras"));
    $(byXpath("//p[@id='email']")).shouldHave(text("test@bk.ru"));
    $(byXpath("//p[@id='currentAddress']")).shouldHave(text("mars"));
    $(byXpath("//p[@id='permanentAddress']")).shouldHave(text("forest"));
  }

  @Test
  @DisplayName("Открытие новой вкладки")
  void OpeningANewTab() {
    $(byXpath("//div[@class='category-cards']/div[3]")).click();
    $(byXpath("//li[@class='btn btn-light active']")).click();
    $(byXpath("//button[@id='tabButton")).click();
    switchTo().window(1);
    $(byXpath("//h1[@id='sampleHeading']")).shouldHave(text("This is a sample page"));
  }
}