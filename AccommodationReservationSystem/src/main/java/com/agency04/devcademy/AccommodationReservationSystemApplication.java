package com.agency04.devcademy;

import com.agency04.devcademy.controllers.AccommodationController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@Slf4j
@SpringBootApplication
public class AccommodationReservationSystemApplication {

    public static void main(String[] args) {
        log.info("APPLICATION START!");
        ApplicationContext ctx = SpringApplication.run(AccommodationReservationSystemApplication.class, args);

        AccommodationController accommodationController = (AccommodationController) ctx.getBean("accommodationController");



    }

}
