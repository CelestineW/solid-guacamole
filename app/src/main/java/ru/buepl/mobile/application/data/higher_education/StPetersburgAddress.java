package ru.buepl.mobile.application.data.higher_education;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(builderClassName = "Builder")
public final class StPetersburgAddress {
    private String streetAddress;
    private String apartmentNumber;
    private String city; // ???? it's in St. Pertersburg ???????
    private String zipCode;
}
