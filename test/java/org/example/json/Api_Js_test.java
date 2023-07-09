package org.example.json;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;

import static io.restassured.RestAssured.given;

public class Api_Js_test {
    private static RequestSpecification requestSpec;

    private static Response r;
    @BeforeClass
    public static void Req_Specif() {

        requestSpec = new RequestSpecBuilder().
                setBaseUri("https://reqres.in/api/users/").
                build();
    }
    @Test
    public void Test_Status() {
        given().
                spec(requestSpec).
                when().
                get("2").
                then().
                assertThat().
                statusCode(200);
    }
    @Test(dataProvider = "data")
    public void test( String first, String last, String email) {
        int num = 2;
        r = given().spec(requestSpec).when().get(String.valueOf(num));
        String f = r.body().jsonPath().getString("data.first_name");
        String l = r.body().jsonPath().getString("data.last_name");
        String em = r.body().jsonPath().getString("data.email");

      Assert.assertEquals(f, first);
        Assert.assertEquals(l, last);
        Assert.assertEquals(em, email);
    }
    @DataProvider(name = "data")
    public String[][] Test_Data() throws InvalidFormatException, IOException {
        read_sheet td = new read_sheet();
        return td.read_data();

    }

}
