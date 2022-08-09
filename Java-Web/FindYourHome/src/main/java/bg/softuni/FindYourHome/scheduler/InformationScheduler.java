package bg.softuni.FindYourHome.scheduler;

import bg.softuni.FindYourHome.service.OfferService;
import bg.softuni.FindYourHome.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class InformationScheduler {
    private final OfferService offerService;
    private final UserService userService;

    private static final Logger logger =
            LoggerFactory.getLogger(UserService.class);

    public InformationScheduler(OfferService offerService, UserService userService) {
        this.offerService = offerService;
        this.userService = userService;
    }

    @Scheduled(cron = "0 * * * * *")
    public void run() {

        logger.info("Current count offers is " + offerService.getCurrentOffersCount() + ".");
        logger.info("Current count users is " + userService.getCurrentUsersCount() + ".");
    }
}
