// File path: src/main/java/org/example/ChemistryService.java
package org.example;

import org.example.repository.ElementRepository;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.example.Model.UserSearch;
import org.example.repository.UserSearchRepository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

@Service
public class ChemistryService {

    @Autowired
    private UserSearchRepository userSearchRepository;

    @Autowired
    private ElementRepository elementRepository;
    private final String url = "https://ptable.com/?lang=uk#%D0%92%D0%BB%D0%B0%D1%81%D1%82%D0%B8%D0%B2%D0%BE%D1%81%D1%82%D1%96";

    public String getChemicalData(String chem) {
        try {
            Document doc = Jsoup.connect(url).get();
            Elements listItems = doc.select("li");

            for (Element listItem : listItems) {
                Elements emElements = listItem.select("em");
                Elements dataElements = listItem.select("data");

                boolean hasChem = emElements.stream().anyMatch(em -> em.text().equals(chem));
                boolean hasData = !dataElements.isEmpty();

                if (hasChem && hasData) {
                    return dataElements.first().text();
                }
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return "No data found";
    }

    public ArrayList<String> getAllElements() {
        ArrayList<String> elementsList = new ArrayList<>();
        try {
            Document doc = Jsoup.connect(url).get();
            Elements listItems = doc.select("li");

            for (Element listItem : listItems) {
                Elements emElements = listItem.select("em");
                Elements dataElements = listItem.select("data");

                if (!emElements.isEmpty() && !dataElements.isEmpty()) {
                    elementsList.add(emElements.first().text() + ": " + dataElements.first().text());
                }
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return elementsList;
    }

    public UserSearch saveUserSearch(String name, String email, String chem) {
        Optional<UserSearch> existingSearch = userSearchRepository.findByEmailAndChem(email, chem);

        UserSearch userSearch;
        if (existingSearch.isPresent()) {
            userSearch = existingSearch.get();
            userSearch.setName(name);
        } else {
            userSearch = new UserSearch();
            userSearch.setName(name);
            userSearch.setEmail(email);
            userSearch.setChem(chem);

            Optional<org.example.Model.Element> element = elementRepository.findById(1L); // Припустимо, що шукаємо елемент з ID = 1
            element.ifPresent(userSearch::setElement);
        }

        return userSearchRepository.save(userSearch);
    }
}
