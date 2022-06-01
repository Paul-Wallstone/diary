package by.school.diary.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "positions")
@Data
@Table
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString(callSuper = true)
public class PositionEntity extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(nullable = false, length = 70)
    private String title;
}
