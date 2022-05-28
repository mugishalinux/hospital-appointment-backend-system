package com.mugisha.hospital.controller;

import com.mugisha.hospital.entity.AppointTimeDate;
import com.mugisha.hospital.entity.Appointment;
import com.mugisha.hospital.entity.AppointmentStatus;
import com.mugisha.hospital.entity.Doctor;
import com.mugisha.hospital.exception.DbConstraintException;
import com.mugisha.hospital.exception.DoctorExistException;
import com.mugisha.hospital.exception.DoctorInputException;
import com.mugisha.hospital.exception.DoctorNotFoundException;
import com.mugisha.hospital.mail.MailService;
import com.mugisha.hospital.service.AppointmentService;
import com.mugisha.hospital.service.DoctorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequestMapping("/api/v1/apt")
@Api(value = "Appointment Endpoints")
public class AppointmentController {

    private final AppointmentService appointmentService;
    private final DoctorService doctorService;

    private final MailService mailService;




    public AppointmentController(AppointmentService appointmentService, DoctorService doctorService, MailService mailService) {
        this.appointmentService = appointmentService;
        this.doctorService = doctorService;
        this.mailService = mailService;
    }

    @ApiOperation(value = "user make an  a appointment and returns a confirmation Message to that registered user")
    @PostMapping("/{id}")
    public ResponseEntity<Appointment> makeAppointment(@RequestBody Appointment appointment, @PathVariable Integer id) throws DoctorInputException, DoctorExistException, DbConstraintException, DoctorNotFoundException, IOException {
        Doctor doctor = doctorService.findDoctorById(id);
        Appointment existingAppointment = appointmentService.findAppointmentByDoctorId(id, appointment.getAppointmentDate(), appointment.getAppointmentHour());

        if (existingAppointment != null) {
            // return already booked
            return new ResponseEntity<>(new Appointment(),HttpStatus.CONFLICT);
        }
        appointment.setDoctor(doctor);
        appointment.setAppointmentStatus(AppointmentStatus.PENDING);
        Appointment createdAppointment = appointmentService.createAppointment(appointment);
        mailService.sendTextEmail(createdAppointment.getPatientName(),appointment.getPatientEmail(),appointment.getAppointmentStatus().name());
        return new ResponseEntity<Appointment>(createdAppointment, HttpStatus.CREATED);

    }

    @ApiOperation(value = "Returns List of available hours doctor has")
    @GetMapping("/doc/{doctorId}/date/{appointmentDate}")
    public ResponseEntity<List<AppointTimeDate>> getDoctorAvailableHours(@PathVariable Integer doctorId, @PathVariable String appointmentDate) throws DoctorNotFoundException {
        Doctor doctor=doctorService.findDoctorById(doctorId);
        List<AppointTimeDate> appointTimeDateList=new ArrayList<>();
        LocalDate aptDate=LocalDate.parse(appointmentDate);
        String hourFormatString=":00";
        Integer starHour=doctor.getStartHour();
        Integer endHour=doctor.getEndHour();
        Appointment apoints=appointmentService.findAppointmentByAppointmentDate(doctorId,aptDate);
        if (apoints==null){
            if (LocalDate.now().equals(aptDate)){
                starHour=LocalDateTime.now().getHour();
                System.out.println("starHour:"+starHour);
            }
            for (int i=starHour;i<=endHour;i++){
                AppointTimeDate appointTimeDate=new AppointTimeDate();
                appointTimeDate.setDate(aptDate);
                appointTimeDate.setTime(i+hourFormatString);
                appointTimeDateList.add(appointTimeDate);
            }
        }else {
            if (LocalDate.now().equals(aptDate)){
                starHour=LocalDateTime.now().getHour();

            }
            for (int i=starHour;i<=endHour;i++){
                Appointment appointment=appointmentService.findAppointmentByDoctorId(doctorId,aptDate,i);
                if (appointment==null){
                    AppointTimeDate appointTimeDate=new AppointTimeDate();
                    appointTimeDate.setDate(aptDate);
                    appointTimeDate.setTime(i+hourFormatString);
                    appointTimeDateList.add(appointTimeDate);
                }
            }
        }
        return new ResponseEntity<>(appointTimeDateList, HttpStatus.OK);
    }

    @ApiOperation(value="Returns List of Appointments ")
    @GetMapping("/all")
    public ResponseEntity<List<Appointment>> getAllAppointments() throws DoctorNotFoundException {
        List<Appointment>appointments=appointmentService.getAppointments();
        if(appointments.isEmpty())throw new DoctorNotFoundException("List is Empty");

        return new ResponseEntity<>(appointments,HttpStatus.OK);
    }

    @ApiOperation(value="Returns status ok ")
    @PutMapping("/{id}/status/{statusId}")
    public ResponseEntity<Appointment> appointmentApproveOrDeny(@PathVariable Integer id,@PathVariable Integer statusId) throws DoctorNotFoundException, IOException {
        Appointment existingAppointment=appointmentService.findAppointmentById(id);
        Appointment updatedAppointment=null;
        Integer approvalStatus=1;
        Integer rejectStatus=0;
        if (existingAppointment==null){
            //here we return
        }
        if (Objects.equals(statusId, rejectStatus)){

            assert existingAppointment != null;
            existingAppointment.setAppointmentStatus(AppointmentStatus.REJECTED);
            updatedAppointment=appointmentService.updateAppointment(existingAppointment);
            mailService.sendTextEmail(existingAppointment.getPatientName(),existingAppointment.getPatientEmail(),existingAppointment.getAppointmentStatus().name());
        }else if (Objects.equals(statusId, approvalStatus)){
            assert existingAppointment != null;
            updatedAppointment=appointmentService.updateAppointment(existingAppointment);
            existingAppointment.setAppointmentStatus(AppointmentStatus.APPROVED);
            mailService.sendTextEmail(existingAppointment.getPatientName(),existingAppointment.getPatientEmail(),existingAppointment.getAppointmentStatus().name());

        }



        return new ResponseEntity<>(updatedAppointment,HttpStatus.OK);
    }

}
