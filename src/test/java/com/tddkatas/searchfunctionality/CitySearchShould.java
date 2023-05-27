package com.tddkatas.searchfunctionality;

import java.util.Collection;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CitySearchShould {

    private static CitySearch citySearch;

    @BeforeAll
    private static void setup() {
        List<String> cities = List.of("Paris", "Budapest", "Skopje", "Rotterdam", "Valencia", "Vancouver", "Amsterdam",
                "Vienna", "Sydney", "New York City", "London", "Bangkok", "Hong Kong", "Dubai", "Rome", "Istanbul");
        citySearch = new CitySearch(cities);
    }

    @Test
    void return_empty_collection_when_text_length_is_less_than_2() {
        Collection<String> searchResult = citySearch.search("a");
        assertThat(searchResult).isEmpty();
    }

    @Test
    void return_all_cities_starting_with_given_text_if_text_length_is_greater_than_2() {
        Collection<String> searchResult = citySearch.search("Va");
        assertThat(searchResult).containsExactlyInAnyOrder("Valencia", "Vancouver");
    }

    @Test
    void return_all_cities_starting_with_given_text_case_insensitive() {
        Collection<String> searchResult = citySearch.search("va");
        assertThat(searchResult).containsExactlyInAnyOrder("Valencia", "Vancouver");
    }

    @Test
    void return_all_cities_that_contains_given_text() {
        Collection<String> searchResult = citySearch.search("an");
        assertThat(searchResult)
                .containsExactlyInAnyOrder("Vancouver", "Bangkok", "Istanbul")
                .doesNotContain("Paris", "Budapest", "Skopje", "Rotterdam", "Valencia", "Amsterdam", "Vienna", "Sydney",
                        "New York City", "London", "Hong Kong", "Dubai", "Rome");
    }

    @Test
    void return_all_cities_if_text_is_asterisk() {
        Collection<String> searchResult = citySearch.search("*");
        assertThat(searchResult).containsExactlyInAnyOrder("Paris", "Budapest", "Skopje", "Rotterdam", "Valencia",
                "Vancouver", "Amsterdam", "Vienna", "Sydney", "New York City", "London", "Bangkok", "Hong Kong",
                "Dubai", "Rome", "Istanbul");
    }

}
