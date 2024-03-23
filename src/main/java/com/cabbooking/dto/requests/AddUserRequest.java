package com.cabbooking.dto.requests;

import com.cabbooking.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class AddUserRequest {
    private String name;
    private int age;
    private Gender gender;
}
