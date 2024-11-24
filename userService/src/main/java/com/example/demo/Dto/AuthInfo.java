package com.example.demo.Dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthInfo {
   private String username;
   private String pwd;
}
