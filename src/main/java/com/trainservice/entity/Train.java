package com.trainservice.entity;

import java.time.LocalTime;
import java.util.List;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Train {
    @Id
    @NotBlank(message = "[ Train Id Cannot be null or blank..! ]")
    private String trainid;

    @NotBlank(message = "[ Train Name Cannot be null or blank..! ]")
    private String trainName;
    
    @NotEmpty(message = "[ Enter Source Name ]")
    private String source;
    
    @NotEmpty(message = "[ Enter Destination Name ]")
    private String destination;
    
    @Positive(message = "[ Seats must be a positive number..! ]")
    private int totalSeats;
    
    private int availableSeats;
    
    @Positive(message = "[ Seats must be a positive number..! ]")
    private double fare;

    
    private LocalTime departureTime;
    
    private LocalTime arrivalTime;

    @ElementCollection
    @NotEmpty(message = "[ Running Days cannot be empty..! ]")
    private List<String> runningDays;
}
