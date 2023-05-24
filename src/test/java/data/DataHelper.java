package data;


import com.github.javafaker.Faker;
import lombok.Value;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Random;

public class DataHelper {

    private static Faker faker = new Faker(new Locale("en"));

    private static Random random = new Random();

    private DataHelper() {
    }

    public static String getApprovedCardNumber() {
        return "4444 4444 4444 4441";
    }

    public static String getDeclinedCardNumber() {
        return "4444 4444 4444 4442";
    }

    public static String getUnintendedCardNumber()  {
        return "4444 4444 4444 4444";
    }

    public static String getInvalidCardNumber() {
        return "4444 4444 4444 444";
    }

    private static LocalDate currentDate = LocalDate.now();

    public static int getCurrentMonth() {
        return currentDate.getMonthValue();
    }

    public static int getRandomValidMonthIfCurrentYear() {
        return random.nextInt(13 - getCurrentMonth()) + getCurrentMonth();
    }

    public static int getRandomInvalidMonthIfCurrentYear() {
        return random.nextInt(getCurrentMonth());
    }


    public static int getCurrentYear() {
        var currentYear = currentDate.format(DateTimeFormatter.ofPattern("yy"));
       return Integer.parseInt(currentYear);
    }

    public static int getRandomValidYear() {
        var maxYear = getCurrentYear() + 5;
        return random.nextInt(maxYear - getCurrentYear() + 1) + getCurrentYear();
    }

    public static String getRandomInvalidYearLessCurrentYear() {
        var randomYear = random.nextInt(getCurrentYear() - 1) + 1;
        return String.format("%02d", randomYear);
    }

    public static int getRandomInvalidYearMoreMaxYear() {
        var maxYear = getCurrentYear() + 5;
        return random.nextInt(100 - maxYear +1) + maxYear;
    }


    public static String getRandomValidHolder() {
        return faker.name().fullName();
    }

    public static String getRandomValidHolderRu() {
        var faker = new Faker(new Locale("ru"));
        return faker.name().fullName();
    }

    public static String getRandomInvalidHolder() {
        return faker.bothify("??");
    }

    public static String getRandomValidCode() {
        return faker.numerify("###");
    }

    public static String getRandomInvalidCode() {
        return faker.numerify("##");
    }



    @Value
    public static class CardInfo {
       String cardNumber;
       Integer month;
       Integer year;
       String holder;
       String code;
    }

    public static CardInfo getCardInfoWithApprovedCardNumber() {
        return new CardInfo(getApprovedCardNumber(), getRandomValidMonthIfCurrentYear(), getRandomValidYear(), getRandomValidHolder(), getRandomValidCode());
    }

    public static CardInfo getCardInfoWithDeclinedCardNumber() {
        return new CardInfo(getDeclinedCardNumber(), getRandomValidMonthIfCurrentYear(), getRandomValidYear(), getRandomValidHolder(), getRandomValidCode());
    }

    public static CardInfo getCardInfoWithoutCardNumber() {
        return new CardInfo("", getRandomValidMonthIfCurrentYear(), getRandomValidYear(), getRandomValidHolder(), getRandomValidCode());
    }

    public static CardInfo getCardInfoWithoutMonth() {
        return new CardInfo(getApprovedCardNumber(), null, getRandomValidYear(), getRandomValidHolder(), getRandomValidCode());
    }

    public static CardInfo getCardInfoWithoutYear() {
        return new CardInfo(getApprovedCardNumber(), getRandomValidMonthIfCurrentYear(), null, getRandomValidHolder(), getRandomValidCode());
    }

    public static CardInfo getCardInfoWithoutHolder() {
        return new CardInfo(getApprovedCardNumber(), getRandomValidMonthIfCurrentYear(), getRandomValidYear(), "", getRandomValidCode());
    }

    public static CardInfo getCardInfoWithoutCode() {
        return new CardInfo(getApprovedCardNumber(), getRandomValidMonthIfCurrentYear(), getRandomValidYear(), getRandomValidHolder(), "");
    }

    public static CardInfo getCardInfoWithUnintendedCardNumber() {
        return new CardInfo(getUnintendedCardNumber(), getRandomValidMonthIfCurrentYear(), getRandomValidYear(), getRandomValidHolder(), getRandomValidCode());
    }

    public static CardInfo getCardInfoWithInvalidCardNumber() {
        return new CardInfo(getInvalidCardNumber(), getRandomValidMonthIfCurrentYear(), getRandomValidYear(), getRandomValidHolder(), getRandomValidCode());
    }

    public static CardInfo getCardInfoWithInvalidMonth() {
        return new CardInfo(getApprovedCardNumber(), getRandomInvalidMonthIfCurrentYear(), getCurrentYear(), getRandomValidHolder(), getRandomValidCode());
    }

    public static CardInfo getCardInfoWithInvalidYearLessCurrentYear() {
        return new CardInfo(getApprovedCardNumber(), getRandomValidMonthIfCurrentYear(), Integer.parseInt(getRandomInvalidYearLessCurrentYear()), getRandomValidHolder(), getRandomValidCode());
    }

    public static CardInfo getCardInfoWithInvalidYearMoreMaxYear() {
        return new CardInfo(getApprovedCardNumber(), getRandomValidMonthIfCurrentYear(), getRandomInvalidYearMoreMaxYear(), getRandomValidHolder(), getRandomValidCode());
    }

    public static CardInfo getCardInfoWithInvalidHolder() {
        return new CardInfo(getApprovedCardNumber(), getRandomValidMonthIfCurrentYear(), getRandomValidYear(), getRandomInvalidHolder(), getRandomValidCode());
    }

    public static CardInfo getCardInfoWithInvalidHolderRu() {
        return new CardInfo(getApprovedCardNumber(), getRandomValidMonthIfCurrentYear(), getRandomValidYear(), getRandomValidHolderRu(), getRandomValidCode());
    }

    public static CardInfo getCardInfoWithInvalidCode() {
        return new CardInfo(getApprovedCardNumber(), getRandomValidMonthIfCurrentYear(), getRandomValidYear(), getRandomValidHolder(), getRandomInvalidCode());
    }


}
