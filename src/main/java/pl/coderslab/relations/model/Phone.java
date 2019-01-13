package pl.coderslab.relations.model;

import javax.persistence.*;

@Entity
public class Phone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(mappedBy = "phone", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private PhoneDetails details;

    @ManyToOne(cascade = {CascadeType.MERGE})
    @JoinColumn(name = "person_id")
    private Person person;

    // ---------------------------------------------------------------------------------------------------------------
    // -- Getters and setters
    // ---------------------------------------------------------------------------------------------------------------

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public PhoneDetails getDetails() {
        return details;
    }

    public void setDetails(PhoneDetails details) {
        this.details = details;
    }
}