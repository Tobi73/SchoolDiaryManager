package database_lab_work.Model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by gman0_000 on 14.04.2017.
 */

@Entity
@Table(name =  "teacher")
@Data
public class Teacher {

    @Id
    @GeneratedValue(generator = "TeachGen")
    @SequenceGenerator(name = "TeachGen", sequenceName = "teacher_seq", allocationSize = 1)
    private long id;
    @JoinColumn(name = "personal_info")
    @NotNull
    @OneToOne
    private Person personalData;
    @JoinColumn(name = "discipline")
    @NotNull
    @ManyToOne
    private Discipline discipline;

    public Teacher(){};

    public Teacher(Person personalData, Discipline discipline){
        this.personalData = personalData;
        this.discipline = discipline;
    }

}
