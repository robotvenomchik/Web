package org.example;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

@Controller
public class HelloController {
    private final RestTemplate restTemplate = new RestTemplate();

    String url = "https://ptable.com/?lang=uk#%D0%92%D0%BB%D0%B0%D1%81%D1%82%D0%B8%D0%B2%D0%BE%D1%81%D1%82%D1%96";
    @GetMapping("/")
    public String index(Model model) {
        return "index";
    }
    @PostMapping("/")
    public String index(@RequestParam String name, @RequestParam String email, @RequestParam String chem, Model model) {
        System.out.println("Received name: " + name + ", email: " + email);
        model.addAttribute("name", name);
        model.addAttribute("email", email);
        model.addAttribute("chem", chem);

        try {
            Document doc = Jsoup.connect(url).get();
            Elements listItems = doc.select("li");

            for (Element listItem : listItems) {
                Elements emElements = listItem.select("em");
                Elements dataElements = listItem.select("data");

                String finalChem = chem;
                boolean hasChem = emElements.stream().anyMatch(em -> em.text().equals(finalChem));
                boolean hasData = !dataElements.isEmpty();

                if (hasChem && hasData) {
                    chem = dataElements.first().text();
                    System.out.println("Found chemical: " + emElements.first().text());
                    System.out.println("Found data: " + chem);
                    break;
                }
            }

        }  catch (IOException e) {
            throw new RuntimeException(e);
        }

        model.addAttribute("chem", chem);

        return "index";
    }

    @GetMapping("myPage")
    public String myPage()  {
        return "myPage";
    }

    @GetMapping("/home")
    @ResponseBody
    public String home() {
        return "<a href='/'>go to main page</a><br><button>ok</button>";
    }


}
