package Tests;

import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class TestGet {

    public String lerJson(String caminhoArquivo) throws IOException {
        return new String(Files.readAllBytes(Paths.get(caminhoArquivo)));
    }

    //Define que o método é um teste
    @Test
    public void GetReservs(){

        // Configura a url base para as requisições da API
        RestAssured.baseURI = "https://restful-booker.herokuapp.com";

        //Configura e executa a requisição GET para o endpoint "/products"
        given() //Define as configurações da requisiçåo
                .header("Aceppt", "*/*") //adiciona o header accept
                .when() // indica o inicio da execução da requisição
                .get("/booking")  //especifica o endpoint a ser chamado
                .then() //Define as validações da resposta do teste
                .statusCode(200)
                .log().all(); // Loga no console todos os detalhes da resposta (body, headers, etc)
    }

    @Test
    public void GetID(){
         RestAssured.baseURI = "https://restful-booker.herokuapp.com";

         given()
                 .header("Aceppt", "application/json")
                 .when()
                .get("/booking/951")
                 .then()
                .statusCode(200)
                 .body("firstname", equalTo("Josh"))
                 .body("lastname", equalTo("Allen"))
                 .body("totalprice", equalTo(111))
                 .body("depositpaid", is(true))
                 .body("bookingdates.checkin", equalTo("2018-01-01"))
                 .body("bookingdates.checkout", equalTo("2019-01-01"));
    }

    @Test // como no lerjson tem uma exception, obrigatoriamente aqui tem que ter também
    public void CriarReserva() throws IOException {

        RestAssured.baseURI = "http://restful-booker.herokuapp.com";

        String jsonbody = lerJson("src/test/resources/payloads/reserva.json");

        given()
                .header("content-type", "application/json")
                .body(jsonbody)

                .when()
                .post("/booking")

                .then()
                .statusCode(200)
                .body("booking.firstname", equalTo("Bruno"))
                .body("booking.lastname", equalTo("Nathan"))
                .body("booking.totalprice", equalTo(3))
                .body("booking.depositpaid", is(true))
                .body("booking.bookingdates.checkin", equalTo("2026-01-21"))
                .body("booking.bookingdates.checkout", equalTo("2026-01-28"));




    }
}



