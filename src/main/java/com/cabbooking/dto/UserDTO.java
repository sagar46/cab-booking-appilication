package com.cabbooking.dto;

import com.cabbooking.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class UserDTO {
    private int id;
    private String name;
    private Gender gender;
    private int age;
}
