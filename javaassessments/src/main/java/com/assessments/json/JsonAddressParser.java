package com.assessments.json;

import java.util.ArrayList;
import java.util.List;

import com.assessments.json.pojo.Address;
import com.assessments.json.pojo.AddressLineDetail;
import com.assessments.utils.FileUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JsonAddressParser {

	public static void main(String[] args) throws Exception {
		JsonAddressParser jsonAddressParser = new JsonAddressParser();
		Address[] oldRespnose = jsonAddressParser.getListOfAddress("addresses.json");
		jsonAddressParser.prettyPrintAllAddresses(oldRespnose);
	}

	/**
	 * This method is used to get all {@code Address} objects from the json file
	 * given
	 * 
	 * @param filePath it should be inside src/main/resources
	 * @return an {@code Array} of {@code Address} objects
	 * @throws Exception
	 */
	public Address[] getListOfAddress(String filePath) throws Exception {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String uglyJsonString = FileUtil.readFromFile(filePath);
		Address[] oldRespnose = gson.fromJson(uglyJsonString, Address[].class);
		return oldRespnose;
	}

	/**
	 * This method is used to pretty print all given addresses.
	 * 
	 * @param addresses
	 */
	public void prettyPrintAllAddresses(Address[] addresses) {
		for (Address address : addresses) {
			System.out.println(prettyPrintAddress(address));
			List<String> errors = new ArrayList<String>();
			if (!isValidAddress(address, errors)) {
				System.out.println("===== Address NOT Valid ======");
				for (String error : errors) {
					System.out.println(error);
				}
			}
		}
	}

	/**
	 * This method is used to pretty print the address separated with {@code -}
	 * 
	 * @param address
	 * @return {@code String} which contains pretty printed format of address.
	 */
	public String prettyPrintAddress(Address address) {
		StringBuilder builder = new StringBuilder();
		if (address.getType() != null) {
			if (address.getType().getName() != null && !address.getType().getName().trim().isEmpty()) {
				builder.append("Type: " + address.getType().getName() + "-");
			}
		}
		if (address.getAddressLineDetail() != null) {
			AddressLineDetail addressLineDetail = address.getAddressLineDetail();
			if (addressLineDetail.getLine1() != null && !addressLineDetail.getLine1().trim().isEmpty()) {
				builder.append(addressLineDetail.getLine1() + "-");
			}
			if (addressLineDetail.getLine2() != null && !addressLineDetail.getLine2().trim().isEmpty()) {
				builder.append(addressLineDetail.getLine2() + "-");
			}
		}
		if (address.getCityOrTown() != null && !address.getCityOrTown().trim().isEmpty()) {
			builder.append(address.getCityOrTown() + "-");
		}
		if (address.getPostalCode() != null && !address.getPostalCode().trim().isEmpty()) {
			builder.append(address.getPostalCode() + "-");
		}
		if (address.getCountry() != null && address.getCountry().getName() != null
				&& !address.getCountry().getName().trim().isEmpty()) {
			builder.append(address.getCountry().getName());
		}
		return builder.toString().trim();
	}

	/**
	 * This method is used to find if the address is valid or not. If the address is
	 * not valid, it will be adding list of errors.
	 * 
	 * @param address
	 * @param errors
	 * @return {@code boolean} that indicates if the address is valid or not
	 */
	private boolean isValidAddress(Address address, List<String> errors) {
		boolean result = true;
		String postalCode = address.getPostalCode();
		if (!(postalCode.matches("[0-9]+") && postalCode.length() > 2)) {
			errors.add("Invalid postal Code");
			result = false;
		}
		if (address.getCountry() == null || address.getCountry().getName() == null) {
			result = false;
			errors.add("Country not found");
		} else if (address.getCountry().getCode().trim().equals("ZA")) {
			if (address.getProvinceOrState() == null || address.getProvinceOrState().getName() == null
					|| address.getProvinceOrState().getName().trim().isEmpty()) {
				result = false;
				errors.add("Country is ZA but province is required.");
			}
		}
		return result;
	}

}
