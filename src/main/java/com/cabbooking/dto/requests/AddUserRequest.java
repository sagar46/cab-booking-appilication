package com.cabbooking.dto.requests;

import com.cabbooking.enums.Gender;
import lombok.Data;

@Data
public class AddUserRequest {
    private String name;
    private int age;
    private Gender gender;
}
