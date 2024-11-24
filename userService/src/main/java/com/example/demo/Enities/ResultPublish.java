package com.example.demo.Enities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResultPublish {
    private String firstPlayer;
    private String secondPlayer;
    @Id
    private int eventId;
}
