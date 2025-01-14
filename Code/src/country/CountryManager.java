package country;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CountryManager {
    private ArrayList<Country> countryArrayList;
    private HashMap<String, ArrayList<Country>> countryGroupByTimeZone;

    public CountryManager(ArrayList<Country> countryArrayList) {
        this.countryArrayList = countryArrayList;
        divideByTimeZone();
    }

    private void divideByTimeZone() {
        countryGroupByTimeZone = new HashMap<>();

        for (int i = -12; i < 15; i++) {
            String timeZone = "";

            // Transform the index in to time zone annotation
            if (i < 0) {
                if (i*(-1) < 10) {
                    timeZone = String.format("UTC-0%d:00", i*(-1));
                } else {
                    timeZone = String.format("UTC%d:00", i);
                }
            } else {
                if (i < 10) {
                    timeZone = String.format("UTC+0%d:00", i);
                } else {
                    timeZone = String.format("UTC+%d:00", i);
                }
            }

            String finalTimeZone = timeZone;
            List<Country> countriesWithTimeZone = countryArrayList.stream().filter(country -> country.getTimeZone().equals(finalTimeZone)).toList();

            countryGroupByTimeZone.put(timeZone, new ArrayList<>(countriesWithTimeZone));
        }
    }
}
