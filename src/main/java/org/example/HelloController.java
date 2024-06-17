// File path: src/main/java/org/example/HelloController.java
package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Controller
public class HelloController {

    @Autowired
    private ChemistryService chemistryService;

    @GetMapping("/")
    public String index(Model model) {
        return "index";
    }

    @PostMapping("/")
    public String index(@RequestParam String name, @RequestParam String email, @RequestParam String chem, Model model) {
        System.out.println("Received name: " + name + ", email: " + email);
        model.addAttribute("name", name);
        model.addAttribute("email", email);

        String chemData = chemistryService.getChemicalData(chem);
        model.addAttribute("chem", chemData);

        chemistryService.saveUserSearch(name, email, chem);

        return "index";
    }

    @GetMapping("/elements")
    @ResponseBody
    public ArrayList<String> getElements() {
        return chemistryService.getAllElements();
    }

    @GetMapping("/myPage")
    public String myPage()  {
        return "myPage";
    }

    @GetMapping("/home")
    @ResponseBody
    public String home() {
        return "<a href='/'>go to main page</a><br><button>ok</button>";
    }
}
