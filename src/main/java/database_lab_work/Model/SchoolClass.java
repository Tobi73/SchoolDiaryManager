package database_lab_work.Model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by gman0_000 on 14.04.2017.
 */

@Entity
@Table(name = "school_class")
@Data
public class SchoolClass {

    @Id
    @GeneratedValue(generator = "ClassGen")
    @SequenceGenerator(name = "ClassGen", sequenceName = "class_seq", allocationSize = 1)
    private long id;
    @NotNull
    private String name;
    @JoinColumn(name = "classroom_teacher")
    @NotNull
    @ManyToOne
    private Teacher teacher;
    @JoinColumn(name = "specialty")
    @NotNull
    @ManyToOne
    private Specialty specialty;

    public SchoolClass() {}

    public SchoolClass(String name, Teacher teacher, Specialty specialty){
        this.name = name;
        this.teacher = teacher;
        this.specialty = specialty;
    }
}
