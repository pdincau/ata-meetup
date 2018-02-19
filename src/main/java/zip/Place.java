package zip;

import com.google.gson.annotations.SerializedName;

public class Place {

    @SerializedName("place name")
    String name;

    String state;

    @SerializedName("state abbreviation")
    String stateAbbreviation;

    public Place(String name, String state, String stateAbbreviation) {
        this.name = name;
        this.state = state;
        this.stateAbbreviation = stateAbbreviation;
    }
}
