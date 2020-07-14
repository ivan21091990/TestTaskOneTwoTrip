package apiTests;

import io.qameta.allure.Description;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.TestBase;
import java.util.List;

import static io.restassured.RestAssured.given;

public class BusDepartureApiTests extends TestBase {

    @Test
    @Description("Проверка статуса кода при запросе")
    public void checkStatusCode() {
        given()
                .accept(ContentType.JSON)
                .queryParam("query", "Омск")
                .get(properties.getProperty("baseUrlApiMethods"))
                .then()
                .statusCode(200);
    }

    @Test
    @Description("Проверка лимита вернувшихся значений")
    public void checkLimitOfReturnValues() {
        Response response = given()
                .accept(ContentType.JSON)
                .queryParam("query", "О")
                .queryParam("limit", "10")
                .get(properties.getProperty("baseUrlApiMethods"))
                .then()
                .contentType(ContentType.JSON)
                .extract()
                .response();

        Assert.assertEquals(response.getBody().jsonPath().getList("data.name").size(), 10, "Фактическое значение отлично от ожидаемого");
    }

    @Test
    @Description("Проверка вернувшихся значений при вводе части названия населенного пункта")
    public void checkOfReturnValueWhenEnteringPartOfSettlementName() {
        Response response = given()
                .accept(ContentType.JSON)
                .queryParam("query", "Нижний")
                .get(properties.getProperty("baseUrlApiMethods"))
                .then()
                .contentType(ContentType.JSON)
                .extract()
                .response();

        List<String> populatedAreas = response.getBody().jsonPath().getList("data.name");
        for (String locality : populatedAreas) {
            Assert.assertTrue(locality.contains("Нижний"), "Вернувшееся значение населенного пункта " + locality + " не содержит букву О");
        }
    }

    @Test
    @Description("Проверка возвращаемого значения при вводе валидного города")
    public void checkOfReturnValueAtInputExistingPopulatedAreas() {
        Response response = given()
                .accept(ContentType.JSON)
                .queryParam("query", "Сызрань")
                .get(properties.getProperty("baseUrlApiMethods"))
                .then()
                .contentType(ContentType.JSON)
                .extract()
                .response();

        Assert.assertEquals(response.getBody().asString(), properties.getProperty("value"), "Содержимое отлично от ожмдаемого");
    }

    @Test
    @Description("Проверка возвращаемого значения при вводе несуществующего населенного пункта")
    public void checkOfReturnValueAtInputDefunctPopulatedAreas() {
        Response response = given()
                .accept(ContentType.JSON)
                .queryParam("query", "123Валывсывам")
                .get(properties.getProperty("baseUrlApiMethods"))
                .then()
                .contentType(ContentType.JSON)
                .extract()
                .response();

        Assert.assertEquals(response.getBody().asString(), properties.getProperty("valueEmpty"), "Фактический результат отличен от ожмдаемого");
    }
}

