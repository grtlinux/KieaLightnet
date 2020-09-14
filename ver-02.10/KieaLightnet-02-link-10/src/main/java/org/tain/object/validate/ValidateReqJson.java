package org.tain.object.validate;

public class ValidateReqJson {

	public static String get_20200913() {
		return "{\n" + 
				"    \"deliveryMethod\": \"cash\",\n" + 
				"    \"remark\": \"This is MGI test remark\",\n" + 
				"    \"promotionCodes\": [\n" + 
				"        \"FLATFEE\"\n" + 
				"    ],\n" + 
				"    \"receiver\": {\n" + 
				"        \"accountNumber\": \"MG-0012345\",\n" + 
				"        \"email\": \"receiverEmail@test.com\",\n" + 
				"        \"firstName\": \"receiverFirstName\",\n" + 
				"        \"lastName\": \"receiverLastName\",\n" + 
				"        \"notification\": {\n" + 
				"            \"transactionSMSOptIn\": false,\n" + 
				"            \"transactionEmailOptIn\": false\n" + 
				"        },\n" + 
				"        \"address\": {\n" + 
				"            \"city\": \"Bangkok\",\n" + 
				"            \"line1\": \"MG-0012345\"\n" + 
				"        },\n" + 
				"        \"phone\": {\n" + 
				"            \"number\": \"345364566\",\n" + 
				"            \"countryCode\": \"33\"\n" + 
				"        }\n" + 
				"    },\n" + 
				"    \"sender\": {\n" + 
				"        \"idType\": \"GOV\",\n" + 
				"        \"idCountryCode\": \"MYS\",\n" + 
				"        \"idNumber\": \"P83245384\",\n" + 
				"\n" + 
				"        \"dob\": \"1992-03-23\",\n" + 
				"        \"purpose\": \"PURCHASE_GOODS\",\n" + 
				"        \"birthCountryCode\": \"THA\",\n" + 
				"        \"relationshipToReceiver\": \"BUSINESS_PARTNER\",\n" + 
				"        \"citizenshipCountryCode\": \"THA\",\n" + 
				"\n" + 
				"        \"email\": \"senderEmail@test.com\",\n" + 
				"        \"firstName\": \"IQLZSOKANG\",\n" + 
				"        \"middleName\": \"senderMiddleName\",\n" + 
				"        \"lastName\": \"senderLastName\",\n" + 
				"        \"secondLastName\": \"senderSecondLastName\",\n" + 
				"\n" + 
				"        \"notification\": {\n" + 
				"            \"transactionSMSOptIn\": false,\n" + 
				"            \"transactionEmailOptIn\": false,\n" + 
				"            \"marketingSMSOptIn\": false,\n" + 
				"            \"marketingEmailOptIn\": false\n" + 
				"        },\n" + 
				"        \"address\": {\n" + 
				"            \"city\": \"Bangkok\",\n" + 
				"            \"postalCode\": \"10400\",\n" + 
				"            \"line1\": \"Temp address\"\n" + 
				"        },\n" + 
				"        \"homePhone\": {\n" + 
				"            \"number\": \"45645645666\",\n" + 
				"            \"countryCode\": \"66\"\n" + 
				"        }\n" + 
				"    },\n" + 
				"    \"destination\": {\n" + 
				"        \"country\": \"IDN\",\n" + 
				"        \"receive\": {\n" + 
				"            \"amount\": \"43366.3500\",\n" + 
				"            \"currency\": \"IDR\"\n" + 
				"        },\n" + 
				"        \"operatorCode\": \"mgi\"\n" + 
				"    },\n" + 
				"    \"source\": {\n" + 
				"        \"country\": \"KOR\",\n" + 
				"        \"send\": {\n" + 
				"            \"currency\": \"THB\"\n" + 
				"        },\n" + 
				"        \"transactionId\": \"4324682635157306\"\n" + 
				"    }\n" + 
				"}";
	}
}
