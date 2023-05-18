package page;

import com.codeborne.selenide.SelenideElement;
import data.DataHelper;

import java.time.Duration;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class CreditPage {

    private final SelenideElement heading = $$("h3").find(text("Кредит по данным карты"));
    private final SelenideElement cardNumber = $(byText("Номер карты")).parent().find("input");
    private final SelenideElement errorCardNumber = $(byText("Номер карты")).parent().find(".input__sub");
    private final SelenideElement month = $(byText("Месяц")).parent().find("input");
    private final SelenideElement errorMonth = $(byText("Месяц")).parent().find(".input__sub");
    private final SelenideElement year = $(byText("Год")).parent().find("input");
    private final SelenideElement errorYear = $(byText("Год")).parent().find(".input__sub");
    private final SelenideElement holder = $(byText("Владелец")).parent().find("input");
    private final SelenideElement errorHolder = $(byText("Владелец")).parent().find(".input__sub");
    private final SelenideElement code = $(byText("CVC/CVV")).parent().find("input");
    private final SelenideElement errorCode = $(byText("CVC/CVV")).parent().find(".input__sub");
    private final SelenideElement continueButton = $$("button").find(text("Продолжить"));
    private final SelenideElement successMessage = $$(".notification__content").find(text("Операция одобрена Банком."));
    private final SelenideElement errorMessage = $$(".notification__content").find(text("Ошибка! Банк отказал в проведении операции."));

    public CreditPage() {
        heading.shouldBe(visible);
    }

    public void sendCardInfo(DataHelper.CardInfo cardInfo) {
        cardNumber.setValue(cardInfo.getCardNumber());
        month.setValue(String.format("%02d", cardInfo.getMonth()));
        year.setValue(String.valueOf(cardInfo.getYear()));
        holder.setValue(cardInfo.getHolder());
        code.setValue(cardInfo.getCode());
        continueButton.click();
    }

    public void clickContinueButton() {
        continueButton.click();
    }

    public void findSuccessMessage() {
        successMessage.shouldBe(visible, Duration.ofSeconds(15));
    }

    public void findErrorMessage() {
        errorMessage.shouldBe(visible, Duration.ofSeconds(15));
    }

    public void findCardNumberFormatErrorMessage() {
        errorCardNumber.shouldBe(visible).shouldHave(text("Неверный формат"));
    }

    public void findRequiredCardNumberErrorMessage() {
        errorCardNumber.shouldBe(visible).shouldHave(text("Поле обязательно для заполнения"));
    }

    public void findErrorMessageMonthLessCurrentMonthIfCurrentYear() {
        errorMonth.shouldBe(visible).shouldHave(text("Истёк срок действия карты"));
    }

    public void findRequiredMonthErrorMessage() {
        errorMonth.shouldBe(visible).shouldHave(text("Поле обязательно для заполнения"));
    }

    public void findYearErrorMessageMoreCurrentYear() {
        errorYear.shouldBe(visible).shouldHave(text("Неверно указан срок действия карты"));
    }

    public void findYearErrorMessageLessCurrentYear() {
        errorYear.shouldBe(visible).shouldHave(text("Истёк срок действия карты"));
    }

    public void findRequiredYearErrorMessage() {
        errorYear.shouldBe(visible).shouldHave(text("Поле обязательно для заполнения"));
    }

    public void findHolderFormatErrorMessage() {
        errorHolder.shouldBe(visible).shouldHave(text("Неверный формат"));
    }

    public void findRequiredHolderErrorMessage() {
        errorHolder.shouldBe(visible).shouldHave(text("Поле обязательно для заполнения"));
    }

    public void findCodeFormatErrorMessage() {
        errorCode.shouldBe(visible).shouldHave(text("Неверный формат"));
    }

    public void findRequiredCodeErrorMessage() {
        errorCode.shouldBe(visible).shouldHave(text("Поле обязательно для заполнения"));
    }



}
