package app.utility;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class DateTimeUtility {

	public LocalDateTime getNow() {
		return LocalDateTime.now();
	}
}
