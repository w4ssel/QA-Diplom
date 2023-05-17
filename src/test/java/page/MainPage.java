package page;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class MainPage {

    private final SelenideElement heading = $(byText("Путешествие дня"));
    private final SelenideElement buyButton = $$("button").find(text("Купить"));
    private final SelenideElement creditButton = $$("button").find(text("Купить в кредит"));

    public MainPage() {
        heading.shouldBe(visible);
    }

    public PaymentPage buy() {
        buyButton.click();
        return new PaymentPage();
    }

    public CreditPage buyOnCredit() {
        creditButton.click();
        return new CreditPage();
    }
}
