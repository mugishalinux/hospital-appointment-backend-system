package com.mugisha.hospital.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
@Table(name = "appointments")
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    String patientName;
    String patientEmail;
    String patientSicknessDetail;
    LocalDate appointmentDate;
    Integer appointmentHour;
    AppointmentStatus appointmentStatus;
    @OneToOne
    Doctor doctor;

}
