package com.trainservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.trainservice.entity.Train;
import com.trainservice.service.TrainService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/trains")
@CrossOrigin(origins = "*")
public class TrainController {

    @Autowired
    private TrainService trainService;

    @PostMapping
    public Train addTrain(@Valid @RequestBody Train train) {
        return trainService.addTrain(train);
    }

    @GetMapping("/{trainId}")
    public Train getTrainById(@PathVariable String trainId) {
        return trainService.getTrainById(trainId);
   }

    @GetMapping
    public List<Train> getAllTrains() {
        return trainService.getAllTrains();
    }

    @GetMapping("/search")
    public List<Train> searchTrains(
            @RequestParam String source, 
            @RequestParam String destination,
            @RequestParam String journeyDate) {
        return trainService.searchTrains(source, destination, journeyDate);
    }

    @PutMapping("/{trainId}")
    public Train updateTrain(@PathVariable String trainId, @RequestBody Train updatedTrain) {
        return trainService.updateTrain(trainId, updatedTrain);
    }

    @DeleteMapping("/{trainId}")
    public String deleteTrain(@PathVariable String trainId) {
        trainService.deleteTrain(trainId);
        return "Train deleted successfully.";
    }

    @GetMapping("/{trainId}/availability")
    public int getAvailableSeats(@PathVariable String trainId) {
        return trainService.getAvailableSeats(trainId);
    }

    @PutMapping("/{trainId}/updateSeats")
    public void updateSeats(@PathVariable String trainId, @RequestParam("seatsToBook") int seatsToBook) {
        trainService.updateSeats(trainId, seatsToBook);
    }
}
