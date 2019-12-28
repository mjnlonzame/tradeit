package tradeit.config;

import javax.validation.constraints.NotNull;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import lombok.Data;

@Data
@ConfigurationProperties(prefix = "tradeit")
@Component
@Validated
public class WelcomeProps {
	@NotNull
	private String welcome;
}
