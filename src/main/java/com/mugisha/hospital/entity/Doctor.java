package com.mugisha.hospital.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import javax.validation.constraints.Email;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
@Table(name = "doctors",
        uniqueConstraints = { @UniqueConstraint(
                name="email_unique",
                columnNames="email_address"
        ),
                @UniqueConstraint(
                        name="phone_number",
                        columnNames = "phone_number"
                )

        }

)
public class Doctor {
    @Column(name = "doctor_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Column(name = "first_name",nullable=false)
    String firstName;

    @Column(name = "last_name",nullable=false)
    String lastName;

    @OneToOne
    Department department;

    @Column(name="email_address",
            nullable=false
    )
    @Email(message = "provide a valid email")
    String email;
    @Column(name = "start_hour")
    Integer startHour;
    @Column(name = "end_hour")
    Integer endHour;

    @Column(name = "phone_number")
    String phoneNumber;

    String gender;

    String age;

    byte[] profilePic;
}
