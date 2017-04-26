package database_lab_work.Model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by gman0_000 on 21.04.2017.
 */

@Entity
@Table(name = "specialty")
@Data
public class Specialty {

    @Id
    @GeneratedValue(generator = "SpecGen")
    @SequenceGenerator(name = "SpecGen", sequenceName = "specialty_seq", allocationSize = 1)
    private long code;
    @NotNull
    private String name;

    public Specialty(){}

    public Specialty(String name){
        this.name = name;
    }

}
