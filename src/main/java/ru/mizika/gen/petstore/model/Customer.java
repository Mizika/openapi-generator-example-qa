package ru.mizika.gen.petstore.model;

import java.util.Objects;
import java.util.Arrays;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.annotation.JsonValue;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import ru.mizika.gen.petstore.model.Address;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonTypeName;

/**
 * Customer
 */
@JsonPropertyOrder({
  Customer.JSON_PROPERTY_ID,
  Customer.JSON_PROPERTY_USERNAME,
  Customer.JSON_PROPERTY_ADDRESS
})
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2023-09-20T11:59:00.089894600+03:00[Europe/Moscow]")
public class Customer {
  public static final String JSON_PROPERTY_ID = "id";
  private Long id;

  public static final String JSON_PROPERTY_USERNAME = "username";
  private String username;

  public static final String JSON_PROPERTY_ADDRESS = "address";
  private List<Address> address;

  public Customer() {
  }

  public Customer id(Long id) {
    
    this.id = id;
    return this;
  }

   /**
   * Get id
   * @return id
  **/
  @javax.annotation.Nullable
  @JsonProperty(JSON_PROPERTY_ID)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public Long getId() {
    return id;
  }


  @JsonProperty(JSON_PROPERTY_ID)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setId(Long id) {
    this.id = id;
  }


  public Customer username(String username) {
    
    this.username = username;
    return this;
  }

   /**
   * Get username
   * @return username
  **/
  @javax.annotation.Nullable
  @JsonProperty(JSON_PROPERTY_USERNAME)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public String getUsername() {
    return username;
  }


  @JsonProperty(JSON_PROPERTY_USERNAME)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setUsername(String username) {
    this.username = username;
  }


  public Customer address(List<Address> address) {
    
    this.address = address;
    return this;
  }

  public Customer addAddressItem(Address addressItem) {
    if (this.address == null) {
      this.address = new ArrayList<>();
    }
    this.address.add(addressItem);
    return this;
  }

   /**
   * Get address
   * @return address
  **/
  @javax.annotation.Nullable
  @JsonProperty(JSON_PROPERTY_ADDRESS)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public List<Address> getAddress() {
    return address;
  }


  @JsonProperty(JSON_PROPERTY_ADDRESS)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setAddress(List<Address> address) {
    this.address = address;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Customer customer = (Customer) o;
    return Objects.equals(this.id, customer.id) &&
        Objects.equals(this.username, customer.username) &&
        Objects.equals(this.address, customer.address);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, username, address);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Customer {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    username: ").append(toIndentedString(username)).append("\n");
    sb.append("    address: ").append(toIndentedString(address)).append("\n");
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
