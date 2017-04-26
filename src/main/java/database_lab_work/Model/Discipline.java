package database_lab_work.Model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by gman0_000 on 21.04.2017.
 */
@Entity
@Table(name = "discipline")
@Data
public class Discipline {

    @Id
    @GeneratedValue(generator = "DiscGen")
    @SequenceGenerator(name = "DiscGen", sequenceName = "discipline_seq", allocationSize = 1)
    private long id;
    @NotNull
    private String name;
    @Column(name = "study_hours")
    @NotNull
    private int studyHours;

    public Discipline() {};

    public Discipline(String name, int studyHours){
        this.name = name;
        this.studyHours = studyHours;
    }

}
