package usuario;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import page.DadosUsuario;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

@DisplayName("Testes de API Rest para o modulo de Usuarios")
public class UsuarioTest {

    @BeforeEach
    public void beforeEach() {
        baseURI = "https://reqres.in/api/";
    }

    @Test
    @DisplayName("Validar o cadastro de novo usuario")
    public void testValidarNovoUsuario() {

        DadosUsuario usuario = new DadosUsuario();
        usuario.setName("Alan");
        usuario.setJob("leader");

        given()
                .contentType(ContentType.JSON)
                .body(usuario)
            .when()
                .post("/users")
            .then()
                .statusCode(201);

    }

        @Test
        @DisplayName("Validar mensagem de erro para registro de novo usuario")
        public void testValidarMensagemDeErroDoRegistro(){
            given()
                    .contentType(ContentType.JSON)
                    .body("{\n" +
                            "    \"email\": \"sydney@fife\"\n" +
                            "}")
                .when()
                    .post("/register")
                .then()
                    .assertThat()
                        .body("error", equalTo("Missing password"))
                        .statusCode(400);
        }
    }

