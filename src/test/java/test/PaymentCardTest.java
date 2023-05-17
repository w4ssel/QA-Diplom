package test;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import data.DataHelper;
import data.SQLHelper;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import page.MainPage;
import page.PaymentPage;

import static com.codeborne.selenide.Selenide.open;
import static data.SQLHelper.cleanDatabase;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PaymentCardTest {
    MainPage mainPage;
    PaymentPage paymentPage;


    @BeforeEach
    void setup() {
        Configuration.holdBrowserOpen = true;
        mainPage = open("http://localhost:8080/", MainPage.class);
        cleanDatabase();
        paymentPage = mainPage.buy();
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
        paymentPage.sendCardInfo(DataHelper.getCardInfoWithApprovedCardNumber());
        paymentPage.findSuccessMessage();
        assertEquals("APPROVED", SQLHelper.getPaymentCardStatus());
    }

    @Test
    @DisplayName("Должен отказать в совершении платежа с отклоненной карты")
    void shouldRejectPaymentWithDeclinedCard() {
        paymentPage.sendCardInfo(DataHelper.getCardInfoWithDeclinedCardNumber());
        paymentPage.findErrorMessage();
        assertEquals("DECLINED", SQLHelper.getPaymentCardStatus());
    }

    @Test
    @DisplayName("Должен отобразить ошибку при незаполненных полях")
    void shouldGetRequireErrorMessageWithBlankFields() {
        paymentPage.clickContinueButton();
        paymentPage.findRequiredCardNumberErrorMessage();
        paymentPage.findRequiredMonthErrorMessage();
        paymentPage.findRequiredYearErrorMessage();
        paymentPage.findRequiredHolderErrorMessage();
        paymentPage.findRequiredCodeErrorMessage();
    }

    @Test
    @DisplayName("Должен отобразить ошибку при незаполненном номере карты")
    void shouldGetRequireErrorMessageWithEmptyCardNumber() {
        paymentPage.sendCardInfo(DataHelper.getCardInfoWithoutCardNumber());
        paymentPage.findRequiredCardNumberErrorMessage();
    }

    @Test
    @DisplayName("Должен отобразить ошибку при незаполненном месяце")
    void shouldGetRequireErrorMessageWithEmptyMonth() {
        paymentPage.sendCardInfo(DataHelper.getCardInfoWithoutMonth());
        paymentPage.findRequiredMonthErrorMessage();
    }

    @Test
    @DisplayName("Должен отобразить ошибку при незаполненном годе")
    void shouldGetRequireErrorMessageWithEmptyYear() {
        paymentPage.sendCardInfo(DataHelper.getCardInfoWithoutYear());
        paymentPage.findRequiredYearErrorMessage();
    }

    @Test
    @DisplayName("Должен отобразить ошибку при незаполненном владельце")
    void shouldGetRequireErrorMessageWithEmptyHolder() {
        paymentPage.sendCardInfo(DataHelper.getCardInfoWithoutHolder());
        paymentPage.findRequiredHolderErrorMessage();
    }

    @Test
    @DisplayName("Должен отобразить ошибку при незаполненном коде")
    void shouldGetRequireErrorMessageWithEmptyCode() {
        paymentPage.sendCardInfo(DataHelper.getCardInfoWithoutCode());
        paymentPage.findRequiredCodeErrorMessage();
    }

    @Test
    @DisplayName("Должен отклонить платеж с непредусмотренной карты")
    void shouldRejectPaymentWithUnintendedCardNumber() {
        paymentPage.sendCardInfo(DataHelper.getCardInfoWithUnintendedCardNumber());
        paymentPage.findErrorMessage();
    }

    @Test
    @DisplayName("Должен отобразить ошибку при невалидном номере карты")
    void shouldGetFormatErrorMessageWithInvalidCardNumber() {
        paymentPage.sendCardInfo(DataHelper.getCardInfoWithInvalidCardNumber());
        paymentPage.findCardNumberFormatErrorMessage();
    }

    @Test
    @DisplayName("Должен отобразить ошибку при невалидном месяце")
    void shouldGetFormatErrorMessageWithInvalidMonth() {
        paymentPage.sendCardInfo(DataHelper.getCardInfoWithInvalidMonth());
        paymentPage.findMonthFormatErrorMessage();
    }

    @Test
    @DisplayName("Должен отобразить ошибку при невалидном годе (год меньше текущего)")
    void shouldGetFormatErrorMessageWithInvalidYearLessCurrentYear() {
        paymentPage.sendCardInfo(DataHelper.getCardInfoWithInvalidYearLessCurrentYear());
        paymentPage.findYearFormatErrorMessage();
    }

    @Test
    @DisplayName("Должен отобразить ошибку при невалидном годе (год больше максимального)")
    void shouldGetFormatErrorMessageWithInvalidYearMoreMaxYear() {
        paymentPage.sendCardInfo(DataHelper.getCardInfoWithInvalidYearMoreMaxYear());
        paymentPage.findYearFormatErrorMessage();
    }

    @Test
    @DisplayName("Должен отобразить ошибку при невалидном владельце (менее 3-ех букв)")
    void shouldGetFormatErrorMessageWithInvalidHolder() {
        paymentPage.sendCardInfo(DataHelper.getCardInfoWithInvalidHolder());
        paymentPage.findHolderFormatErrorMessage();
    }

    @Test
    @DisplayName("Должен отобразить ошибку при невалидном владельце (кириллица)")
    void shouldGetFormatErrorMessageWithInvalidHolderRu() {
        paymentPage.sendCardInfo(DataHelper.getCardInfoWithInvalidHolderRu());
        paymentPage.findHolderFormatErrorMessage();
    }

    @Test
    @DisplayName("Должен отобразить ошибку при невалидном коде")
    void shouldGetFormatErrorMessageWithInvalidCode() {
        paymentPage.sendCardInfo(DataHelper.getCardInfoWithInvalidCode());
        paymentPage.findCodeFormatErrorMessage();
    }

}
