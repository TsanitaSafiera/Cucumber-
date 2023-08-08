package API;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class HardCodedExamples {

    // baseURI= base URL+endpoints
    // given = preparation
    // when - hitting end point
    // base URI = Base URL
    String baseURI= RestAssured.baseURI="http://hrm.syntaxtechs.net/syntaxapi/api";

    String token = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE2OTExMDcwNzcsImlzcyI6ImxvY2FsaG9zdCIsImV4cCI6MTY5MTE1MDI3NywidXNlcklkIjoiNTY0MCJ9.1sM1814-3VVnZgGuVqXl_FL4nx7ht1m2dDkjhdHyp20";

    static String employee_id;
    // we try to do add employee request
    // we need header, body to prepare for the request

    @Test
    public void acreateEmployee(){
        // preparing the request
        RequestSpecification request = given().header("Content-Type","application/json").
                header("Authorization",token).body("{\n" +
                        "  \"emp_firstname\": \"nina\",\n" +
                        "  \"emp_lastname\": \"Sara\",\n" +
                        "  \"emp_middle_name\": \"ms\",\n" +
                        "  \"emp_gender\": \"F\",\n" +
                        "  \"emp_birthday\": \"1995-07-23\",\n" +
                        "  \"emp_status\": \"married\",\n" +
                        "  \"emp_job_title\": \"QA\"\n" +
                        "}");

        // hitting the endpoint

        Response response= request.when().post("/createEmployee.php");

        // verifying the response

        response.then().assertThat().statusCode(201);
        //System.out.println(response);
        //this method we use to print response of API in console
        response.prettyPrint();

        // verify all the values and headers from response
        response.then().assertThat().body("Employee.emp_firstname",equalTo("nina"));
        response.then().assertThat().body("Employee.emp_middle_name",equalTo("ms"));
        response.then().assertThat().body("Message", equalTo("Employee Created"));
        response.then().assertThat().header("X-Powered-By","PHP/7.2.18");

        // it will return the employee id and saved it in a variable

        employee_id = response.jsonPath().getString("Employee.employee_id");
        System.out.println(employee_id);


    }

        @Test
        public void bgetCreatedEmployee(){
            RequestSpecification request = given().header("Authorization",token)
                    .queryParam("employee_id", employee_id);

        Response response = request.when().get("/getOneEmployee.php");
        response.then().assertThat().statusCode(200);
        response.prettyPrint();

        String tempEmpId= response.jsonPath().getString("employee.employee_id");
            Assert.assertEquals(employee_id,tempEmpId);

        }
        @Test
        public void cUpdateEmployee(){

            RequestSpecification request = given().header("Content-Type","application/json").
                    header("Authorization",token).body("{\n" +
                            "  \"employee_id\": \""+employee_id+"\",\n" +
                            "  \"emp_firstname\": \"Diana\",\n" +
                            "  \"emp_lastname\": \"Tabita\",\n" +
                            "  \"emp_middle_name\": \"Melia\",\n" +
                            "  \"emp_gender\": \"F\",\n" +
                            "  \"emp_birthday\": \"2003-07-29\",\n" +
                            "  \"emp_status\": \"single\",\n" +
                            "  \"emp_job_title\": \"singer\"\n" +
                            "}");
            Response response= request.when().put("/updateEmployee.php");
            response.then().assertThat().statusCode(200);
            response.then().assertThat().body("Message",equalTo("Employee record Updated"));


        }
    @Test
    public void dgetCreatedEmployee(){
        RequestSpecification request = given().header("Authorization",token)
                .queryParam("employee_id", employee_id);

        Response response = request.when().get("/getOneEmployee.php");
        response.then().assertThat().statusCode(200);
        response.prettyPrint();

        //String tempEmpId= response.jsonPath().getString("employee.employee_id");
        //Assert.assertEquals(employee_id,tempEmpId);

    }

    @Test
    public void ePartiallyUpdateEmployee(){

        RequestSpecification request = given().header("Content-Type","application/json").
                header("Authorization",token).body("{\n" +
                        "  \"employee_id\": \""+employee_id+"\",\n" +
                        "  \"emp_middle_name\": \"Lady\"\n" +
                        "}");
        Response response= request.when().patch("/updatePartialEmplyeesDetails.php");
        response.then().assertThat().statusCode(201);
        response.then().assertThat().body("Message",equalTo("Employee record updated successfully"));
        response.prettyPrint();


    }

    @Test
    public void fGetPartiallyUpdatedEmployee(){
        RequestSpecification request = given().header("Authorization",token)
                .queryParam("employee_id", employee_id);

        Response response = request.when().get("/getOneEmployee.php");
        response.then().assertThat().statusCode(200);
        response.prettyPrint();

        String tempEmpId= response.jsonPath().getString("employee.employee_id");
        Assert.assertEquals(employee_id,tempEmpId);

    }




}
