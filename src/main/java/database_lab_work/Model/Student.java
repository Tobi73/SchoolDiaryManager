package database_lab_work.Model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by gman0_000 on 14.04.2017.
 */
@Entity
@Table(name = "student")
@Data
public class Student {

    @Id
    @GeneratedValue(generator = "StudGen")
    @SequenceGenerator(name = "StudGen", sequenceName = "student_seq", allocationSize = 1)
    private long id;
    @NotNull
    @Column(name = "parents_phone_number")
    private String phoneNumber;
    @NotNull
    @JoinColumn(name = "class")
    @ManyToOne
    private SchoolClass schoolClass;
    @NotNull
    @JoinColumn(name = "personal_data")
    @OneToOne
    private Person personalData;

    public Student() {}

    public Student(String phoneNumber, SchoolClass schoolClass, Person personalData){
        this.phoneNumber = phoneNumber;
        this.schoolClass = schoolClass;
        this.personalData = personalData;
    }
}
