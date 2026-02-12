DTO
---
  package com.lkm.entity;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class StudentDTO {
    private Long id;

    @NotBlank
    @Size(min = 4, max = 16)
    @Pattern(regexp = "^[A-Za-z]+( [A-Za-z]+){0,3}$",
            message = "Name must be 4-16 letters")
    private String name;

    @NotBlank
    @Email(message = "Invalid email format")
    @Pattern(
            regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.(com|in|org)$",
            message = "Email must end with .com, .in, or .org"
    )
    private String email;

    @NotNull
    private String course;
}


Entity
  ---
  package com.lkm.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "students")
public class StudentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String email;
    private String course;
}

