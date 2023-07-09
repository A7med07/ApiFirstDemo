package xml;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;

import static io.restassured.RestAssured.given;

public class Api_xml_test {
    private static RequestSpecification requestSpec;

    private static Response r;
    @BeforeClass
    public static void Req_Specif() {

        requestSpec = new RequestSpecBuilder().
                setBaseUri("https://parabank.parasoft.com/parabank/services/bank/customers/").
                build();
    }
    @Test
    public void Test_Status() {

        given().
                spec(requestSpec).
                when().
                get("12212").
                then().
                assertThat().
                statusCode(200);
    }
    @Test(dataProvider = "Test_Data")
    public void test( String customer_id, String firstName, String lastName) {
        int num = 12212;
        String xml = given().spec(requestSpec).when().get(String.valueOf(num)).andReturn().asString();
        XmlPath xmlPath =new XmlPath(xml).setRoot("customer");
        int id = xmlPath.getInt("id");
        String first_n = xmlPath.getString("firstName");
        String last_n = xmlPath.getString("lastName");
        double cust_id = Double.parseDouble(customer_id);
        int id_to_int = (int)cust_id;
        System.out.println(customer_id);
        Assert.assertEquals(id, id_to_int );
        Assert.assertEquals(first_n, firstName);
        Assert.assertEquals(last_n, lastName);
    }
    @Test
    public void Invalid_test(){
        given().
                spec(requestSpec).
                when().
                get("12213").
                then().
                assertThat().log();
    }
    @DataProvider(name = "Test_Data")
    public String[][] Test_Data_Xml() throws InvalidFormatException, IOException {
        read_sheet_xml_test td = new read_sheet_xml_test();
        return td.read_data();

    }

}
