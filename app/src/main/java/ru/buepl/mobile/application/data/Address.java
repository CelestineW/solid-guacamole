package ru.buepl.mobile.application.data;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(builderClassName = "Builder")
public final class Address {
    private String fullName;
    private String streetAddress;
    private String apartmentNumber;
    private String zipCode;
    private String phoneNumber;
}
