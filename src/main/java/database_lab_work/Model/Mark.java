package database_lab_work.Model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Date;

/**
 * Created by gman0_000 on 14.04.2017.
 */

@Entity
@Table(name = "mark")
@Data
public class Mark{

    @Id
    @GeneratedValue(generator = "MarkSeq")
    @SequenceGenerator(name = "MarkSeq", sequenceName = "mark_seq", allocationSize = 1)
    private long id;
    @NotNull
    private Date date;
    @NotNull
    @Size(min = 1, max = 5)
    private int mark;
    @NotNull
    @ManyToOne
    @JoinColumn(name = "student")
    private Student student;
    @NotNull
    @ManyToOne
    @JoinColumn(name = "teacher")
    private Teacher teacher;

    public Mark(){};

    public Mark(Student student, Teacher teacher){
        this.student = student;
        this.teacher = teacher;
    }



}
