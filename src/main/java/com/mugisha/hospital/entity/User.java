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
@Table(name = "users",uniqueConstraints = { @UniqueConstraint(
        name="email_unique",
        columnNames="email_address"
),

})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    Integer id;

    @Column(name = "first_name",nullable=false)
    String firstName;

    @Column(name = "last_name",nullable=false)
    String lastName;

    @Column(name = "email_address",nullable=false)
    String email;

    String username;

    String password;

}
