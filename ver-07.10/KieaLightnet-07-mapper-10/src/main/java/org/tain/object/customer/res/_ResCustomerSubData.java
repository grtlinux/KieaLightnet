package org.tain.object.customer.res;

import java.util.ArrayList;
import java.util.List;

import org.tain.annotation.StreamAnnotation;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class _ResCustomerSubData {

	@StreamAnnotation(length = 5)
	@JsonProperty(value = "birthCountryCode")
	private String birthCountryCode;
	
	@StreamAnnotation(length = 10)
	@JsonProperty(value = "dob")
	private String dob;
	
	
	// name information
	@StreamAnnotation(length = 20)
	@JsonProperty(value = "firstName")
	private String firstName;
	
	@StreamAnnotation(length = 20)
	@JsonProperty(value = "middleName")
	private String middleName;
	
	@StreamAnnotation(length = 20)
	@JsonProperty(value = "lastName")
	private String lastName;
	
	@StreamAnnotation(length = 20)
	@JsonProperty(value = "secondLastName")
	private String secondLastName;
	
	
	@StreamAnnotation
	@JsonProperty(value = "address")
	private _ResCustomerAddress address = new _ResCustomerAddress();
	
	@StreamAnnotation
	@JsonProperty(value = "homePhone")
	private _ResCustomerPhone phone = new _ResCustomerPhone();
	
	@StreamAnnotation
	@JsonProperty(value = "receiver")
	private List<_ResCustomerReceiver> receiver = new ArrayList<>();
}
