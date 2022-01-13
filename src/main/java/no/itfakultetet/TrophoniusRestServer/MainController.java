package no.itfakultetet.TrophoniusRestServer;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    @GetMapping("/")
    public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
        return String.format("<h1>Hei %s!</h1>", name);
    }

    @GetMapping("/user")
    public String user(@RequestParam(value = "name", defaultValue = "World") String name) {
        return String.format("<h1>User Data for %s</h1>", name);

    }

}
