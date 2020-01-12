package sw.user.dto;

import java.util.ArrayList;
import java.util.List;

public class GradAndMostInterestsDTO {
    private List<String> graduated = new ArrayList<>();
    private List<String> interests = new ArrayList<>();

    public List<String> getGraduated() {
        return graduated;
    }

    public GradAndMostInterestsDTO setGraduated(List<String> graduated) {
        this.graduated = graduated;
        return this;
    }

    public List<String> getInterests() {
        return interests;
    }

    public GradAndMostInterestsDTO setInterests(List<String> interests) {
        this.interests = interests;
        return this;
    }
}
