package Tests;

import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;

public class ListarReservas {
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
}
