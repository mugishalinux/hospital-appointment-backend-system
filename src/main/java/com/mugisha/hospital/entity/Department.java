package com.mugisha.hospital.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.Instant;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
@Table(name = "departments",
        uniqueConstraints = { @UniqueConstraint(
        name="name",
        columnNames="name"
)

})
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    @Column(name = "name",nullable=false)
    String name;
    @CreatedDate
    private Instant createdDate;
}
