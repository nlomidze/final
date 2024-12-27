package com.example.FinalAssignment.job;

import com.example.FinalAssignment.model.domain.database.MusicLogger;
import com.example.FinalAssignment.service.MusicLoggerService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MusicLoggerScheduler {

    @Autowired
    private MusicLoggerService musicLoggerService;

    private static final Logger LOGGER = LoggerFactory.getLogger(MusicLoggerScheduler.class);
    @Scheduled(cron = "0 0 * * * *")
    public void printLogs(){
        List<MusicLogger> logs = musicLoggerService.getAll();
        logs.forEach(l->{
            LOGGER.info(String.format("username %s , music %s - count %d",
                    l.getUser().getUsername(),
                    l.getMusic().getMusicName(),
                    l.getListenCount()));
        });
    }

}
