package database_lab_work.Model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Date;

/**
 * Created by gman0_000 on 14.04.2017.
 */
@Entity
@Table(name = "person")
@Data
public class Person {

    @Id
    @GeneratedValue(generator = "PerGen")
    @SequenceGenerator(name = "PerGen", sequenceName = "person_seq", allocationSize = 1)
    private long id;
    @NotNull
    private String name;
    @NotNull
    private String surname;
    private String patronymic;
    @NotNull
    @Column(name = "date_of_birth")
    private Date dateOfBirth;

    public Person() {};

    public Person(String name, String surname, String patronymic, Date dateOfBirth){
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
        this.dateOfBirth = dateOfBirth;
    }

    public Person(String name, String surname){
        this.name = name;
        this.surname = surname;
    }

}
