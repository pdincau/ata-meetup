package zip;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class ZipInfo {

    @SerializedName("post code")
    String postCode;

    @SerializedName("country abbreviation")
    String countryAbbreviation;

    String country;
    List<Place> places;

    public ZipInfo(String postCode, String country, String countryAbbreviation, List<Place> places) {
        this.postCode = postCode;
        this.country = country;
        this.countryAbbreviation = countryAbbreviation;
        this.places = places;
    }
}

