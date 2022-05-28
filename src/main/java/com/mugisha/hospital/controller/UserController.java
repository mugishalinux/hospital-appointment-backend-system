package com.mugisha.hospital.controller;

import com.mugisha.hospital.entity.Appointment;
import com.mugisha.hospital.entity.Constants;
import com.mugisha.hospital.entity.User;
import com.mugisha.hospital.exception.DbConstraintException;
import com.mugisha.hospital.exception.DoctorExistException;
import com.mugisha.hospital.exception.DoctorInputException;
import com.mugisha.hospital.exception.DoctorNotFoundException;
import com.mugisha.hospital.service.AppointmentService;
import com.mugisha.hospital.service.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/auth/user")
@Api(value = "User Endpoints")
public class UserController {
    private final UserService userService;

    private final AppointmentService appointmentService;

    public UserController(UserService userService, AppointmentService appointmentService) {
        this.userService = userService;
        this.appointmentService = appointmentService;
    }

    @ApiOperation(value="Returns List of Employees Details")
    @GetMapping("")
    public ResponseEntity<List<User>> getAllUsers() throws DoctorNotFoundException {
        List<User>users=userService.getUsers();
        if(users.isEmpty())throw new DoctorNotFoundException("List is Empty");

        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @ApiOperation(value = "register a user and returns a confirmation Message to that registered user")
    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody User user ) throws DoctorInputException, DoctorExistException, DbConstraintException, DoctorNotFoundException {
        String hashedPassword= BCrypt.hashpw(user.getPassword(),BCrypt.gensalt(10));
        user.setPassword(hashedPassword);
        User createdUser=userService.createUser(user);

        return new ResponseEntity<User>( createdUser,HttpStatus.CREATED);

    }


    @PostMapping("/login")
    @ApiOperation(value = "a Manager logs in by providing email and password")
    public ResponseEntity<Map<String,String>> managerLogin(@RequestBody Map<String,Object> admin)
            throws DoctorNotFoundException{
        String email=(String)admin.get("email");
        String password=(String)admin.get("password");
        if(!email.trim().isEmpty())email=email.toLowerCase();
        User user=userService.findUserByEmail(email);
        if(user==null)throw new DoctorNotFoundException("Invalid email/password");
        if(!BCrypt.checkpw(password,user.getPassword()))
            throw new DoctorNotFoundException("Invalid email/password");

        return new ResponseEntity<>(generateJWTToken(user),HttpStatus.OK);
    }



    private Map<String ,String> generateJWTToken(User user){
        long timestamp=System.currentTimeMillis();
        String token= Jwts.builder().signWith(SignatureAlgorithm.HS256, Constants.API_SECRET_KEY)
                .setIssuedAt(new Date(timestamp))
                .setExpiration(new Date(timestamp+ Constants.TOKEN_VALIDITY))
                .claim("id",user.getId())
                .claim("email",user.getEmail())
                .claim("firstName",user.getFirstName())
                .claim("email",user.getEmail())
                .claim("username",user.getUsername())
                .compact();
        Map<String ,String> map=new HashMap<>();
        map.put("token", token);
        return map;

    }
}
