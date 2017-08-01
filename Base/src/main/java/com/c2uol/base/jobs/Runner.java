package com.c2uol.base.jobs;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class Runner {

    @Scheduled(cron = "0/5 * *  * * ?")
    public void execute() {
        System.out.println("hello");
    }

}
