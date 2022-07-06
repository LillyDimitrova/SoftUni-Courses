package bg.softuni.exam.init;

import bg.softuni.exam.service.StyleService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DBInit implements CommandLineRunner {
    private  final StyleService service;

    public DBInit(StyleService service) {
        this.service = service;
    }

    @Override
    public void run(String... args) throws Exception {
        service.initStyle();
    }
}
