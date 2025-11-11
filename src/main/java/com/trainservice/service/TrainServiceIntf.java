package com.trainservice.service;

import java.util.List;

import com.trainservice.entity.Train;

public interface TrainServiceIntf {
	
	public List<Train> getAllTrains();
	public Train addTrain(Train train);
	public List<Train> searchTrains(String source, String destination);
}
