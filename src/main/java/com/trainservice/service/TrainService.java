package com.trainservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trainservice.entity.Train;
import com.trainservice.repository.TrainRepository;

@Service
public class TrainService {
	@Autowired
	private TrainRepository trainRepository;

	// get all train details
	public List<Train> getAllTrains() {
		return trainRepository.findAll();
	}

	public Train getTrainById(String trainId) {
		return trainRepository.findById(trainId)
				.orElseThrow(() -> new RuntimeException("Train not found with ID: " + trainId));
	}

	// add new train data
	public Train addTrain(Train train) {
		return trainRepository.save(train);
	}

	// search train by source, destination and journey date
	public List<Train> searchTrains(String source, String destination, String journeyDate) {
		List<Train> trains = trainRepository.findBySourceAndDestination(source, destination);
		
		// Convert journey date to day of week
		java.time.DayOfWeek dayOfWeek = java.time.LocalDate.parse(journeyDate).getDayOfWeek();
		String day = dayOfWeek.toString(); // Get full uppercase day name (e.g., "MONDAY")
		
		// Add logging to debug the search
		System.out.println("Searching for trains on day: " + day);
		System.out.println("Found " + trains.size() + " trains for route " + source + " to " + destination);
		
		// Filter trains that run on the specified day (case-insensitive)
		List<Train> filteredTrains = trains.stream()
				.filter(train -> {
					boolean matches = train.getRunningDays().stream()
							.anyMatch(runningDay -> runningDay.toUpperCase().equals(day));
					if (!matches) {
						System.out.println("Train " + train.getTrainid() + " does not run on " + day + 
								". Running days: " + train.getRunningDays());
					}
					return matches;
				})
				.toList();
		
		System.out.println("After filtering by day: " + filteredTrains.size() + " trains found");
		return filteredTrains;
	}

	// update existing train detail
	public Train updateTrain(String trainId, Train updatedTrain) {
		return trainRepository.findById(trainId).map(train -> {
			train.setTrainName(updatedTrain.getTrainName());
			train.setSource(updatedTrain.getSource());
			train.setDestination(updatedTrain.getDestination());
			train.setTotalSeats(updatedTrain.getTotalSeats());
			train.setAvailableSeats(updatedTrain.getAvailableSeats());
			train.setFare(updatedTrain.getFare());
			train.setDepartureTime(updatedTrain.getDepartureTime());
			train.setArrivalTime(updatedTrain.getArrivalTime());
			train.setRunningDays(updatedTrain.getRunningDays());
			return trainRepository.save(train);
		}).orElseThrow(() -> new RuntimeException("Train not found"));
	}

	// delete train details access- admit
	public void deleteTrain(String trainId) {
		trainRepository.deleteById(trainId);
	}

	public int getAvailableSeats(String trainId) {
		return trainRepository.findById(trainId).map(Train::getAvailableSeats)
				.orElseThrow(() -> new RuntimeException("Train not found"));
	}
	

   
	public void updateSeats(String trainId, int seatsToBook) {
	    Train train = trainRepository.findById(trainId)
	        .orElseThrow(() -> new RuntimeException("Train not found with ID: " + trainId));

	    int availableSeats = train.getAvailableSeats();

	    if (availableSeats < seatsToBook) {
	        throw new RuntimeException("Not enough seats available");
	    }

	    else {
	    train.setAvailableSeats(availableSeats - seatsToBook);
	    trainRepository.save(train);
	    }
	}

}
