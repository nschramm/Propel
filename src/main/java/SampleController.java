/**
 * Created by Nick Schramm on 8/7/16.
 */
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.stereotype.*;

@Controller
@EnableAutoConfiguration
public class SampleController {

//    @RequestMapping("/")
//    @ResponseBody
//    String home() {
//        return "index";
//    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(SampleController.class, args);
    }
}
