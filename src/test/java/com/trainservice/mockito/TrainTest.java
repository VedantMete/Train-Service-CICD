//package com.trainservice.mockito;
//
//import com.trainservice.entity.Train;
//import com.trainservice.repository.TrainRepository;
//import com.trainservice.service.TrainService;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.*;
//import java.util.*;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.*;
//
//class TrainTest {
//
//    @InjectMocks
//    private TrainService trainService;
//
//    @Mock
//    private TrainRepository trainRepository;
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//    }
//
//    @Test
//    void testUpdateSeats_Success() {
//        Train train = new Train();
//        train.setTrainid("T123");
//       train.setAvailableSeats(50);
//
//        when(trainRepository.findById("T123")).thenReturn(Optional.of(train));
//        when(trainRepository.save(any(Train.class))).thenReturn(train);
//
//        trainService.updateSeats("T123", 10);
//
//        assertEquals(40, train.getAvailableSeats());
//        verify(trainRepository).save(train);
//    }
//
//    @Test
//    void testUpdateSeats_NotEnoughSeats() {
//        Train train = new Train();
//        train.setTrainid("T123");
//        train.setAvailableSeats(5);
//
//        when(trainRepository.findById("T123")).thenReturn(Optional.of(train));
//
//        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
//            trainService.updateSeats("T123", 10);
//        });
//
//        assertEquals("Not enough seats available", exception.getMessage());
//    }
//
//    @Test
//    void testSearchTrains() {
//        Train train1 = new Train();
//        train1.setTrainName("Express 1");
//        Train train2 = new Train();
//        train2.setTrainName("Express 2");
//
//        List<Train> mockTrains = Arrays.asList(train1, train2);
//
//        when(trainRepository.findBySourceAndDestination("Pune", "Delhi")).thenReturn(mockTrains);
//
//        List<Train> result = trainService.searchTrains("Pune", "Delhi");
//
//        assertEquals(2, result.size());
//        verify(trainRepository).findBySourceAndDestination("Pune", "Delhi");
//    }
//}
