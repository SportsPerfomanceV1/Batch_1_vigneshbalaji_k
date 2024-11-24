package com.example.demo.Enities;

import com.example.demo.Enum.Gender;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {

   private String firstName;
   private String lastName;
   @Enumerated(EnumType.STRING)
   private Gender gender;

   private int age;
   private String dob;
   private String role;
   @Id
   private String email;
   private String password;
   private String athleteId;
}
