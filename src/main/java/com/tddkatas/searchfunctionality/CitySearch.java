package com.tddkatas.searchfunctionality;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class CitySearch {

    private List<String> cities;

    public CitySearch(Collection<String> cities) {
        this.cities = List.copyOf(cities);
    }

    public Collection<String> search(String searchText) {
        if (searchText.equals("*"))
            return cities;
        if (searchText.length() < 2)
            return Collections.emptyList();
        return cities.stream()
                .filter(city -> city.toLowerCase().contains(searchText.toLowerCase()))
                .toList();

    }

}
