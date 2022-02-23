package evgenulianov.task01_property;

import evgenulianov.task01_property.Profile.DevProfile;
import evgenulianov.task01_property.Profile.ProductionProfile;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class Task01PropertyApplicationTests {

    @Autowired
    private TestRestTemplate restTemplate;

    @Container
    public static GenericContainer<?> prodapp = new GenericContainer<>("app:latest")
            .withExposedPorts(9090)
            .withEnv("NETOLOGY_PROFILE_DEV", "false");

    @Container
    public static GenericContainer<?> devapp = new GenericContainer<>("app:latest")
            .withExposedPorts(9090)
            .withEnv("NETOLOGY_PROFILE_DEV", "true");

    @Test
    void contextLoadsDev() {
        int devPort = devapp.getMappedPort(9090);

        ResponseEntity<String> forEntityDev = restTemplate.getForEntity("http://localhost:" + devPort + "/profile", String.class);
        Assertions.assertEquals(new DevProfile().getProfile(), forEntityDev.getBody());
    }

    @Test
    void contextLoadsProd() {
        int prodPort = prodapp.getMappedPort(9090);

        ResponseEntity<String> forEntityProd = restTemplate.getForEntity("http://localhost:" + prodPort + "/profile", String.class);
        Assertions.assertEquals(new ProductionProfile().getProfile(), forEntityProd.getBody());

    }


}