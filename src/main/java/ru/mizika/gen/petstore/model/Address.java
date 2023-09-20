package ru.mizika.gen.petstore.model;

import java.util.Objects;
import java.util.Arrays;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonTypeName;

/**
 * Address
 */
@JsonPropertyOrder({
  Address.JSON_PROPERTY_STREET,
  Address.JSON_PROPERTY_CITY,
  Address.JSON_PROPERTY_STATE,
  Address.JSON_PROPERTY_ZIP
})
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2023-09-20T11:59:00.089894600+03:00[Europe/Moscow]")
public class Address {
  public static final String JSON_PROPERTY_STREET = "street";
  private String street;

  public static final String JSON_PROPERTY_CITY = "city";
  private String city;

  public static final String JSON_PROPERTY_STATE = "state";
  private String state;

  public static final String JSON_PROPERTY_ZIP = "zip";
  private String zip;

  public Address() {
  }

  public Address street(String street) {
    
    this.street = street;
    return this;
  }

   /**
   * Get street
   * @return street
  **/
  @javax.annotation.Nullable
  @JsonProperty(JSON_PROPERTY_STREET)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public String getStreet() {
    return street;
  }


  @JsonProperty(JSON_PROPERTY_STREET)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setStreet(String street) {
    this.street = street;
  }


  public Address city(String city) {
    
    this.city = city;
    return this;
  }

   /**
   * Get city
   * @return city
  **/
  @javax.annotation.Nullable
  @JsonProperty(JSON_PROPERTY_CITY)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public String getCity() {
    return city;
  }


  @JsonProperty(JSON_PROPERTY_CITY)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setCity(String city) {
    this.city = city;
  }


  public Address state(String state) {
    
    this.state = state;
    return this;
  }

   /**
   * Get state
   * @return state
  **/
  @javax.annotation.Nullable
  @JsonProperty(JSON_PROPERTY_STATE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public String getState() {
    return state;
  }


  @JsonProperty(JSON_PROPERTY_STATE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setState(String state) {
    this.state = state;
  }


  public Address zip(String zip) {
    
    this.zip = zip;
    return this;
  }

   /**
   * Get zip
   * @return zip
  **/
  @javax.annotation.Nullable
  @JsonProperty(JSON_PROPERTY_ZIP)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public String getZip() {
    return zip;
  }


  @JsonProperty(JSON_PROPERTY_ZIP)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setZip(String zip) {
    this.zip = zip;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Address address = (Address) o;
    return Objects.equals(this.street, address.street) &&
        Objects.equals(this.city, address.city) &&
        Objects.equals(this.state, address.state) &&
        Objects.equals(this.zip, address.zip);
  }

  @Override
  public int hashCode() {
    return Objects.hash(street, city, state, zip);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Address {\n");
    sb.append("    street: ").append(toIndentedString(street)).append("\n");
    sb.append("    city: ").append(toIndentedString(city)).append("\n");
    sb.append("    state: ").append(toIndentedString(state)).append("\n");
    sb.append("    zip: ").append(toIndentedString(zip)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }

}
