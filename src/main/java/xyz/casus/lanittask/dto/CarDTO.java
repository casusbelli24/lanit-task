package xyz.casus.lanittask.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;

public class CarDTO {

    @JsonIgnore
    private final String VENDOR_AND_MODEL_SEPARATOR = "-";

    @NotNull
    private Long id;

    @NotNull
    @Pattern(regexp = "[a-zA-Z0-9]+?-[a-zA-Z0-9-]+")
    @JsonProperty("model")
    private String vendorAndModel;

    @NotNull
    @Positive
    private Integer horsepower;

    @NotNull
    private Long ownerId;

    public CarDTO() {
    }

    public CarDTO(Long id, String vendorAndModel, Integer horsepower, Long ownerId) {
        this.id = id;
        this.vendorAndModel = vendorAndModel;
        this.horsepower = horsepower;
        this.ownerId = ownerId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getVendorAndModel() {
        return vendorAndModel;
    }

    public void setVendorAndModel(String vendorAndModel) {
        this.vendorAndModel = vendorAndModel;
    }

    public Integer getHorsepower() {
        return horsepower;
    }

    public void setHorsepower(Integer horsepower) {
        this.horsepower = horsepower;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    @JsonIgnore
    public String getVendor() {
        return vendorAndModel.substring(0, vendorAndModel.indexOf(VENDOR_AND_MODEL_SEPARATOR));
    }

    @JsonIgnore
    public String getModel() {
        return vendorAndModel.substring(vendorAndModel.indexOf(VENDOR_AND_MODEL_SEPARATOR) + 1);
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", model='" + vendorAndModel + '\'' +
                ", horsepower=" + horsepower +
                ", ownerId=" + ownerId +
                '}';
    }

}
