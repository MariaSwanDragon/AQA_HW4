package ru.netology.selenid;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.time.Duration;


import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class DeliveryCardTest {



    @BeforeEach
    void setUp() {
        open("http://localhost:9999");
    }

    @Test
    public void shouldSuccessOrderTheCard() {


        $("[data-test-id='city'] input").setValue("Москва");
        $("[data-test-id='date'] input").setValue("11.08.2022");
        $("[data-test-id='name'] input").setValue("Лебедева Мария");
        $("[data-test-id='phone'] input").setValue("+79060410000");
        $("[data-test-id='agreement']").click();
        $(byText("Забронировать")).click();
        $(".notification__content")
                .shouldHave(Condition.text("Встреча успешно забронирована на " + "11.08.2022"), Duration.ofSeconds(20));
    }
    @Test
    public void shouldFaildWhenSityEmpty() {


        $("[data-test-id='city'] input").setValue("");
        $("[data-test-id='date'] input").setValue("11.08.2022");
        $("[data-test-id='name'] input").setValue("Лебедева Мария");
        $("[data-test-id='phone'] input").setValue("+79060410000");
        $("[data-test-id='agreement']").click();
        $(byText("Забронировать")).click();
        $("[data-test-id='city'] .input__sub").shouldHave(exactText("Поле обязательно для заполнения"));
    }
    @Test
    public void shouldFaildWhenSityOnLatinica() {


        $("[data-test-id='city'] input").setValue("Moscow");
        $("[data-test-id='date'] input").setValue("11.08.2022");
        $("[data-test-id='name'] input").setValue("Лебедева Мария");
        $("[data-test-id='phone'] input").setValue("+79060410000");
        $("[data-test-id='agreement']").click();
        $(byText("Забронировать")).click();
        $("[data-test-id='city'] .input__sub").shouldHave(exactText("Доставка в выбранный город недоступна"));
    }
    @Test
    public void shouldFaildWhenSityNotFromList() {


        $("[data-test-id='city'] input").setValue("Комсомольск-на-Амуре");
        $("[data-test-id='date'] input").setValue("11.08.2022");
        $("[data-test-id='name'] input").setValue("Лебедева Мария");
        $("[data-test-id='phone'] input").setValue("+79060410000");
        $("[data-test-id='agreement']").click();
        $(byText("Забронировать")).click();
        $("[data-test-id='city'] .input__sub").shouldHave(exactText("Доставка в выбранный город недоступна"));
    }
    @Test
    public void shouldFaildWhenRongData() {


        $("[data-test-id='city'] input").setValue("Москва");
        $("[data-test-id='date'] input").setValue("11.04.2022");
        $("[data-test-id='name'] input").setValue("Lebedeva Mariia");
        $("[data-test-id='phone'] input").setValue("+79060410000");
        $("[data-test-id='agreement']").click();
        $(byText("Забронировать")).click();
        $("[data-test-id='date'] .input__sub").shouldHave(exactText("Заказ на выбранную дату невозможен"));
    }
    @Test
    public void shouldFaildWhenNameOnLatinica() {


        $("[data-test-id='city'] input").setValue("Москва");
        $("[data-test-id='date'] input").setValue("11.08.2022");
        $("[data-test-id='name'] input").setValue("Lebedeva Mariia");
        $("[data-test-id='phone'] input").setValue("+79060410000");
        $("[data-test-id='agreement']").click();
        $(byText("Забронировать")).click();
        $("[data-test-id='name'] .input__sub").shouldHave(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }
    

    @Test
    public void shouldWhenRongNamber() {


        $("[data-test-id='city'] input").setValue("Москва");
        $("[data-test-id='date'] input").setValue("11.08.2022");
        $("[data-test-id='name'] input").setValue("Лебедева Мария");
        $("[data-test-id='phone'] input").setValue("79060410000");
        $("[data-test-id='agreement']").click();
        $(byText("Забронировать")).click();
        $("[data-test-id='phone'] .input__sub").shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

}
