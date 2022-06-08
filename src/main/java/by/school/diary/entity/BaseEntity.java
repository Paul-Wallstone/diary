package by.school.diary.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@MappedSuperclass
public abstract class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;


    @JsonFormat(pattern = "yyyy-mm-dd HH:mm:ss")
    @Column(name = "create_at", nullable = false, updatable = false)
    @CreationTimestamp
    protected LocalDateTime createdAt;


    @UpdateTimestamp
    @Column(name = "modified_at", nullable = false)
    protected LocalDateTime modifiedAt;
}
