package artgarden.server.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

class KopisServiceTest {

    @Test
    public void formatDate(){
        LocalDate currentDate = LocalDate.now().minusDays(1);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");

        Assertions.assertThat(currentDate.format(formatter)).isEqualTo("20231109");

    }

}