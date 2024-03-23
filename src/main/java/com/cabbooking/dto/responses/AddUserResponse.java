package com.cabbooking.dto.responses;

import com.cabbooking.enums.Gender;
import lombok.Data;

@Data
public class AddUserResponse {
    private int id;
    private String name;
    private int age;
    private Gender gender;
}
