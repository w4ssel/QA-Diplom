package test;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import data.DataHelper;
import data.SQLHelper;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import page.CreditPage;
import page.MainPage;

import static com.codeborne.selenide.Selenide.open;
import static data.SQLHelper.cleanDatabase;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CreditCardTest {

    MainPage mainPage;
    CreditPage creditPage;


    @BeforeEach
    void setup() {
        Configuration.holdBrowserOpen = true;
        mainPage = open("http://localhost:8080/", MainPage.class);
        cleanDatabase();
        creditPage = mainPage.buyOnCredit();
    }
    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }
    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    @Test
    @DisplayName("Должен успешно выполнить платеж с одобренной карты")
    void shouldMakePaymentWithApprovedCard() {
        creditPage.sendCardInfo(DataHelper.getCardInfoWithApprovedCardNumber());
        creditPage.checkSuccessMessage();
        assertEquals("APPROVED", SQLHelper.getCreditCardStatus());
    }

    @Test
    @DisplayName("Должен отказать в совершении платежа с отклоненной карты")
    void shouldRejectPaymentWithDeclinedCard() {
        creditPage.sendCardInfo(DataHelper.getCardInfoWithDeclinedCardNumber());
        creditPage.checkErrorMessage();
        assertEquals("DECLINED", SQLHelper.getCreditCardStatus());
    }

    @Test
    @DisplayName("Должен отобразить ошибку при незаполненных полях")
    void shouldGetRequireErrorMessageWithBlankFields() {
        creditPage.clickContinueButton();
        creditPage.checkRequiredCardNumberErrorMessage();
        creditPage.checkRequiredMonthErrorMessage();
        creditPage.checkRequiredYearErrorMessage();
        creditPage.checkRequiredHolderErrorMessage();
        creditPage.checkRequiredCodeErrorMessage();
    }

    @Test
    @DisplayName("Должен отобразить ошибку при незаполненном номере карты")
    void shouldGetRequireErrorMessageWithEmptyCardNumber() {
        creditPage.sendCardInfo(DataHelper.getCardInfoWithoutCardNumber());
        creditPage.checkRequiredCardNumberErrorMessage();
    }

    @Test
    @DisplayName("Должен отобразить ошибку при незаполненном месяце")
    void shouldGetRequireErrorMessageWithEmptyMonth() {
        creditPage.sendCardInfo(DataHelper.getCardInfoWithoutMonth());
        creditPage.checkRequiredMonthErrorMessage();
    }

    @Test
    @DisplayName("Должен отобразить ошибку при незаполненном годе")
    void shouldGetRequireErrorMessageWithEmptyYear() {
        creditPage.sendCardInfo(DataHelper.getCardInfoWithoutYear());
        creditPage.checkRequiredYearErrorMessage();
    }

    @Test
    @DisplayName("Должен отобразить ошибку при незаполненном владельце")
    void shouldGetRequireErrorMessageWithEmptyHolder() {
        creditPage.sendCardInfo(DataHelper.getCardInfoWithoutHolder());
        creditPage.checkRequiredHolderErrorMessage();
    }

    @Test
    @DisplayName("Должен отобразить ошибку при незаполненном коде")
    void shouldGetRequireErrorMessageWithEmptyCode() {
        creditPage.sendCardInfo(DataHelper.getCardInfoWithoutCode());
        creditPage.checkRequiredCodeErrorMessage();
    }

    @Test
    @DisplayName("Должен отклонить платеж с непредусмотренной карты")
    void shouldRejectPaymentWithUnintendedCardNumber() {
        creditPage.sendCardInfo(DataHelper.getCardInfoWithUnintendedCardNumber());
        creditPage.checkErrorMessage();
    }

    @Test
    @DisplayName("Должен отобразить ошибку при невалидном номере карты")
    void shouldGetFormatErrorMessageWithInvalidCardNumber() {
        creditPage.sendCardInfo(DataHelper.getCardInfoWithInvalidCardNumber());
        creditPage.checkCardNumberFormatErrorMessage();
    }

    @Test
    @DisplayName("Должен отобразить ошибку при невалидном месяце")
    void shouldGetFormatErrorMessageWithInvalidMonth() {
        creditPage.sendCardInfo(DataHelper.getCardInfoWithInvalidMonth());
        creditPage.checkErrorMessageMonthLessCurrentMonthIfCurrentYear();
    }

    @Test
    @DisplayName("Должен отобразить ошибку при невалидном годе (год меньше текущего)")
    void shouldGetFormatErrorMessageWithInvalidYearLessCurrentYear() {
        creditPage.sendCardInfo(DataHelper.getCardInfoWithInvalidYearLessCurrentYear());
        creditPage.checkYearErrorMessageLessCurrentYear();
    }

    @Test
    @DisplayName("Должен отобразить ошибку при невалидном годе (год больше максимального)")
    void shouldGetFormatErrorMessageWithInvalidYearMoreMaxYear() {
        creditPage.sendCardInfo(DataHelper.getCardInfoWithInvalidYearMoreMaxYear());
        creditPage.checkYearErrorMessageMoreCurrentYear();
    }

    @Test
    @DisplayName("Должен отобразить ошибку при невалидном владельце (менее 3-ех букв)")
    void shouldGetFormatErrorMessageWithInvalidHolder() {
        creditPage.sendCardInfo(DataHelper.getCardInfoWithInvalidHolder());
        creditPage.checkHolderFormatErrorMessage();
    }

    @Test
    @DisplayName("Должен отобразить ошибку при невалидном владельце (кириллица)")
    void shouldGetFormatErrorMessageWithInvalidHolderRu() {
        creditPage.sendCardInfo(DataHelper.getCardInfoWithInvalidHolderRu());
        creditPage.checkHolderFormatErrorMessage();
    }

    @Test
    @DisplayName("Должен отобразить ошибку при невалидном коде")
    void shouldGetFormatErrorMessageWithInvalidCode() {
        creditPage.sendCardInfo(DataHelper.getCardInfoWithInvalidCode());
        creditPage.checkCodeFormatErrorMessage();
    }

}

