package com.example.waiterservice.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {
    private String name;
    private  String  email;
    private String phoneNumber;
    private  String password;
    private  String gender;
    private String occupation;
    private Date dateOfBirth;
    private String nationalIdCard;
    private String bkash;
    private String State;
}
