package com.tcs.users.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsersEntry {

    private String name;

    private Integer age;

    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate dateOfBirth;
}
