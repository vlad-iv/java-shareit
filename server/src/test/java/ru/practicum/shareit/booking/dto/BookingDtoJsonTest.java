package ru.practicum.shareit.booking.dto;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.json.JsonContent;

import lombok.RequiredArgsConstructor;
import ru.practicum.shareit.booking.BookingStatus;

@JsonTest
@RequiredArgsConstructor(onConstructor_ = @Autowired)
class BookingDtoJsonTest {
    private final JacksonTester<BookingDto> json;

    @Test
    void testSerialize() throws Exception {
        BookingDto.Booker booker = new BookingDto.Booker(2L, "Booker");
        BookingDto.Item item = new BookingDto.Item(3L, "Item");
        BookingDto dto = new BookingDto(1L, LocalDateTime.now(), LocalDateTime.now(), booker, item, BookingStatus.WAITING);

        JsonContent<BookingDto> result = json.write(dto);
        assertThat(result).hasJsonPath("$.id")
                .hasJsonPath("$.start")
                .hasJsonPath("$.end")
                .hasJsonPath("$.booker")
                .hasJsonPath("$.item")
                .hasJsonPath("$.status")
                .hasJsonPathValue("$.start")
                .hasJsonPathValue("$.end");

        assertThat(result).extractingJsonPathNumberValue("$.id")
                .satisfies(bookingId -> assertThat(bookingId.longValue()).isEqualTo(dto.getId()));

        assertThat(result).extractingJsonPathNumberValue("$.booker.id")
                .satisfies(bookerId -> assertThat(bookerId.longValue()).isEqualTo(booker.getId()));

        assertThat(result).extractingJsonPathStringValue("$.booker.name")
                .satisfies(bookerName -> assertThat(bookerName).isEqualTo(booker.getName()));

        assertThat(result).extractingJsonPathNumberValue("$.item.id")
                .satisfies(itemId -> assertThat(itemId.longValue()).isEqualTo(item.getId()));

        assertThat(result).extractingJsonPathStringValue("$.item.name")
                .satisfies(itemName -> assertThat(itemName).isEqualTo(item.getName()));

        assertThat(result).extractingJsonPathStringValue("$.status")
                .satisfies(status -> assertThat(status).isEqualToIgnoringCase(dto.getStatus().name()));
    }
}