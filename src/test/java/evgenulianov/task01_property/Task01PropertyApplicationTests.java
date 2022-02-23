package evgenulianov.task01_property;

import com.github.dockerjava.api.DockerClient;
import evgenulianov.task01_property.Profile.DevProfile;
import evgenulianov.task01_property.Profile.ProductionProfile;
import evgenulianov.task01_property.Profile.SystemProfile;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.shaded.com.github.dockerjava.core.DefaultDockerClientConfig;

//@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class Task01PropertyApplicationTests {

    @Autowired
    private TestRestTemplate restTemplate;

//    @Container
    static private GenericContainer<?> prodapp; //= new GenericContainer<>("prodapp:latest")
//            .withExposedPorts(8080)
//            .withEnv("","TRUE")
//            .withEnv("NETOLOGY_PROFILE_DEV","FALSE");

//    @Container
    static private GenericContainer<?> devapp;// = new GenericContainer<>("devapp:latest")
//            .withExposedPorts(8081)
////            .withEnv("TESTCONTAINERS_RYUK_DISABLED","TRUE")
//            .withEnv("NETOLOGY_PROFILE_DEV","TRUE");

    static {

    }

    @BeforeAll
    static public void setUp() {

//        prodapp = new GenericContainer<>("prodapp:latest")
//                .withExposedPorts(9090)
//                .withEnv("NETOLOGY_PROFILE_DEV","FALSE");
        devapp = new GenericContainer<>("devapp:latest")
                .withExposedPorts(9090)
//            .withEnv("TESTCONTAINERS_RYUK_DISABLED","TRUE")
                .withEnv("NETOLOGY_PROFILE_DEV","TRUE");

//        prodapp.getDockerClient()
//                .pullImageCmd("prodapp:latest").withRegistry("registryurl").withRepository("repo").withTag("tag");
//        devapp.getDockerClient()
//                .pullImageCmd("devapp:latest").withRegistry("registryurl").withRepository("repo").withTag("tag");
//        prodapp.getDockerClient()
//                .authConfig().withUsername("").withPassword("");
//        devapp.getDockerClient()
//                .authConfig().withUsername("").withPassword("");

//        prodapp.start();
        devapp.start();
    }

//    @Test
//    void contextLoadsProd() {
//
////        System.out.print("prodapp.getMappedPort(8080):" );
////        System.out.println(prodapp.getMappedPort(8080) );
//
//        int mappedPort = prodapp.getMappedPort(9090);
//
//
//        Assertions.assertTrue(true);
//
//        ResponseEntity<String> forEntityProd = restTemplate.getForEntity("http://localhost:" + String.valueOf(mappedPort) + "/profile", String.class);
////        System.out.println(forEntityProd.getBody());
//        SystemProfile profile = new DevProfile();
//        String expectedBody = profile.getProfile();
//        Assertions.assertEquals(expectedBody, forEntityProd.getBody());
//    }

    @Test
    void contextLoadsDev() {

       System.out.print("devapp.getMappedPort(9090):" );
       System.out.println(devapp.getMappedPort(9090) );
        int mappedPort = devapp.getMappedPort(9090);

        ResponseEntity<String> forEntityDev = restTemplate.getForEntity("http://localhost:" + String.valueOf(mappedPort) + "/profile", String.class);
        SystemProfile profile = new ProductionProfile();
        String expectedBody = profile.getProfile();
        Assertions.assertEquals(expectedBody, forEntityDev.getBody());
    }



}
