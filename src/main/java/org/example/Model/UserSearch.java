package org.example.Model;

import jakarta.persistence.*;

@Entity
public class UserSearch {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String chem;

    @ManyToMany
    @JoinColumn(name = "element_id")
    private Element element;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getChem() {
        return chem;
    }

    public void setChem(String chem) {
        this.chem = chem;
    }

    public void setElement(org.jsoup.nodes.Element element) {
    }

    public void setElement(Element element) {
    }
}
