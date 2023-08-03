package ru.netology.web;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

/*import java.time.Duration;*/
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Selenide.*;

public class NegativeTestCardOrder {

	private String generateDate(int addDays, String pattern) {
		return LocalDate.now().plusDays(addDays).format(DateTimeFormatter.ofPattern(pattern));
	}

	@Test
	public void shouldNotSuccessCity() {
		open("http://localhost:9999");
		$("[data-test-id='city'] input").setValue("Тольятти");
		String currentDate = generateDate(5, "dd.MM.yyyy");
		$("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.DELETE);
		$("[data-test-id='date'] input").sendKeys(currentDate);
		$("[data-test-id='name'] input").setValue("Владимиров-Сиванов Владимир");
		$("[data-test-id='phone'] input").setValue("+79613908874");
		$("[data-test-id='agreement']").click();
		$("button.button").click();
		$("[data-test-id='city'].input_invalid .input__sub")
				.shouldHave(Condition.exactText("Доставка в выбранный город недоступна"));
	}

	@Test
	public void shouldNotSuccessNameLatin() {
		open("http://localhost:9999");
		$("[data-test-id='city'] input").setValue("Волгоград");
		String currentDate = generateDate(5, "dd.MM.yyyy");
		$("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.DELETE);
		$("[data-test-id='date'] input").sendKeys(currentDate);
		$("[data-test-id='name'] input").setValue("Vladimirov Vladimir");
		$("[data-test-id='phone'] input").setValue("+79613908874");
		$("[data-test-id='agreement']").click();
		$("button.button").click();
		$("[data-test-id='name'].input_invalid .input__sub")
				.shouldHave(Condition.exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
	}

	@Test
	public void shouldNotSuccessNameFigure() {
		open("http://localhost:9999");
		$("[data-test-id='city'] input").setValue("Волгоград");
		String currentDate = generateDate(5, "dd.MM.yyyy");
		$("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.DELETE);
		$("[data-test-id='date'] input").sendKeys(currentDate);
		$("[data-test-id='name'] input").setValue("1367 5721");
		$("[data-test-id='phone'] input").setValue("+79613908874");
		$("[data-test-id='agreement']").click();
		$("button.button").click();
		$("[data-test-id='name'].input_invalid .input__sub")
				.shouldHave(Condition.exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
	}

	@Test
	public void shouldNotSuccessNameSpecialCharacter() {
		open("http://localhost:9999");
		$("[data-test-id='city'] input").setValue("Волгоград");
		String currentDate = generateDate(5, "dd.MM.yyyy");
		$("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.DELETE);
		$("[data-test-id='date'] input").sendKeys(currentDate);
		$("[data-test-id='name'] input").setValue("Владимиров&#$* Владимир");
		$("[data-test-id='phone'] input").setValue("+79613908874");
		$("[data-test-id='agreement']").click();
		$("button.button").click();
		$("[data-test-id='name'].input_invalid .input__sub")
				.shouldHave(Condition.exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
	}

	@Test
	public void shouldNotSuccessNameUnderscore() {
		open("http://localhost:9999");
		$("[data-test-id='city'] input").setValue("Волгоград");
		String currentDate = generateDate(5, "dd.MM.yyyy");
		$("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.DELETE);
		$("[data-test-id='date'] input").sendKeys(currentDate);
		$("[data-test-id='name'] input").setValue("Владимиров_Иванов Владимир");
		$("[data-test-id='phone'] input").setValue("+79613908874");
		$("[data-test-id='agreement']").click();
		$("button.button").click();
		$("[data-test-id='name'].input_invalid .input__sub")
				.shouldHave(Condition.exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
	}

	@Test
	public void shouldNotSuccessPhoneWithoutPlus() {
		open("http://localhost:9999");
		$("[data-test-id='city'] input").setValue("Волгоград");
		String currentDate = generateDate(5, "dd.MM.yyyy");
		$("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.DELETE);
		$("[data-test-id='date'] input").sendKeys(currentDate);
		$("[data-test-id='name'] input").setValue("Владимиров-Иванов Владимир");
		$("[data-test-id='phone'] input").setValue("89613908874");
		$("[data-test-id='agreement']").click();
		$("button.button").click();
		$("[data-test-id='phone'].input_invalid .input__sub")
				.shouldHave(Condition.exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
	}

	@Test
	public void shouldNotSuccessPhonePlusOneFigure() {
		open("http://localhost:9999");
		$("[data-test-id='city'] input").setValue("Волгоград");
		String currentDate = generateDate(5, "dd.MM.yyyy");
		$("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.DELETE);
		$("[data-test-id='date'] input").sendKeys(currentDate);
		$("[data-test-id='name'] input").setValue("Владимиров-Иванов Владимир");
		$("[data-test-id='phone'] input").setValue("+896139088740");
		$("[data-test-id='agreement']").click();
		$("button.button").click();
		$("[data-test-id='phone'].input_invalid .input__sub")
				.shouldHave(Condition.exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
	}

	@Test
	public void shouldNotSuccessPhoneMinusOneFigure() {
		open("http://localhost:9999");
		$("[data-test-id='city'] input").setValue("Волгоград");
		String currentDate = generateDate(5, "dd.MM.yyyy");
		$("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.DELETE);
		$("[data-test-id='date'] input").sendKeys(currentDate);
		$("[data-test-id='name'] input").setValue("Владимиров-Иванов Владимир");
		$("[data-test-id='phone'] input").setValue("+8961390887");
		$("[data-test-id='agreement']").click();
		$("button.button").click();
		$("[data-test-id='phone'].input_invalid .input__sub")
				.shouldHave(Condition.exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
	}

	@Test
	public void shouldNotSuccessPhonePlusFourFigures() {
		open("http://localhost:9999");
		$("[data-test-id='city'] input").setValue("Волгоград");
		String currentDate = generateDate(5, "dd.MM.yyyy");
		$("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.DELETE);
		$("[data-test-id='date'] input").sendKeys(currentDate);
		$("[data-test-id='name'] input").setValue("Владимиров-Иванов Владимир");
		$("[data-test-id='phone'] input").setValue("+896139088740000");
		$("[data-test-id='agreement']").click();
		$("button.button").click();
		$("[data-test-id='phone'].input_invalid .input__sub")
				.shouldHave(Condition.exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
	}

	@Test
	public void shouldNotSuccessPhoneText() {
		open("http://localhost:9999");
		$("[data-test-id='city'] input").setValue("Волгоград");
		String currentDate = generateDate(5, "dd.MM.yyyy");
		$("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.DELETE);
		$("[data-test-id='date'] input").sendKeys(currentDate);
		$("[data-test-id='name'] input").setValue("Владимиров-Иванов Владимир");
		$("[data-test-id='phone'] input").setValue("+Пример");
		$("[data-test-id='agreement']").click();
		$("button.button").click();
		$("[data-test-id='phone'].input_invalid .input__sub")
				.shouldHave(Condition.exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
	}

	@Test
	public void shouldNotSuccessPhoneDoublePlus() {
		open("http://localhost:9999");
		$("[data-test-id='city'] input").setValue("Волгоград");
		String currentDate = generateDate(5, "dd.MM.yyyy");
		$("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.DELETE);
		$("[data-test-id='date'] input").sendKeys(currentDate);
		$("[data-test-id='name'] input").setValue("Владимиров-Иванов Владимир");
		$("[data-test-id='phone'] input").setValue("++89613908874");
		$("[data-test-id='agreement']").click();
		$("button.button").click();
		$("[data-test-id='phone'].input_invalid .input__sub")
				.shouldHave(Condition.exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
	}

	@Test
	public void shouldNotSuccessCheckBox() {
		open("http://localhost:9999");
		$("[data-test-id='city'] input").setValue("Волгоград");
		String currentDate = generateDate(5, "dd.MM.yyyy");
		$("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.DELETE);
		$("[data-test-id='date'] input").sendKeys(currentDate);
		$("[data-test-id='name'] input").setValue("Владимиров-Иванов Владимир");
		$("[data-test-id='phone'] input").setValue("+89613908874");
/*		$("[data-test-id='agreement']").click();*/
		$("button.button").click();
		$("[data-test-id='agreement'].input_invalid").isDisplayed();
	}
}
