package bg.softuni.FindYourHome.config;
import bg.softuni.FindYourHome.service.OfferService;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;


@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class FindYourHomeMethodSecurityConfig extends GlobalMethodSecurityConfiguration{


    private final OfferService offerService;

    public FindYourHomeMethodSecurityConfig(OfferService offerService) {
        this.offerService = offerService;
    }

    @Override
    protected MethodSecurityExpressionHandler createExpressionHandler() {
        return new FindYourHomeSecurityExpressionHandler(offerService);
    }
}
