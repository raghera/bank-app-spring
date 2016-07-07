/**
 * 
 */
package com.ravi.bank.domain.service.jpa.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * A Data Domain Object implementation 
 * for the Customer database table.
 * 
 * @author Ravi Aghera
 *
 */
@Entity
@Table(name="CUSTOMER")
public class CustomerImpl implements Customer {

	private Long customerId;
	
	private Long customerType;
	
	@Size(min=2, max=25, message="*Your first name should be between 2 and 25 characters")
	@Pattern(regexp="[A-Za-z]+$", message="*Your first name should contain only alphanumeric characters and no spaces")
	private String firstName;
	
	@Size(min=2, max=25, message="*Your surname should be between 2 and 25 characters")
	@Pattern(regexp="[A-Za-z]+$", message="*Your surname must contain only alphanumeric characters and no spaces")
	private String surname;
	
	@Pattern(regexp="[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+.[A-Za-z]{2,4}", message="*Please enter a valid e-mail address.")
	private String email;
	
	@Size(min=2, max=2, message="*Please enter a valid 2 character country code")
	@Pattern(regexp="[A-Za-z]{2}", message="*Country code must be of 2 alphanumeric characters")
	private String countryCode;
	
	private String password;

	//Mappings to other Entities
	private List<Account> accountsList;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="cust_seq")
	@SequenceGenerator(name="cust_seq", sequenceName="CUSTOMER_SEQ", initialValue=1, allocationSize=1)
	@Column(name="CUSTOMER_ID")
	@Override
	public Long getCustomerId() {
		return customerId;
	}
	
	@Override
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	
	@Column(name="CUSTOMER_TYPE")
	@Override
	public Long getCustomerType() {
		return customerType;
	}
	
	@Override
	public void setCustomerType(Long customerType) {
		this.customerType = customerType;
	}
	
	@Override
	public String getFirstName() {
		return firstName;
	}
	
	@Override
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	@Override
	public String getSurname() {
		return surname;
	}
	
	@Override
	public void setSurname(String surname) {
		this.surname = surname;
	}
	
	@Override
	public String getEmail() {
		return email;
	}
	
	@Override
	public void setEmail(String email) {
		this.email = email;
	}
	
	@Override
	public String getCountryCode() {
		return countryCode;
	}
	
	@Override
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	//Could have eager FetchType as likely to be just few Accounts
	@OneToMany(fetch=FetchType.LAZY, orphanRemoval=true, mappedBy="customer", targetEntity=AccountImpl.class, cascade=CascadeType.ALL)
	@Override
	public List<Account> getAccountsList() {
		return accountsList;
	}

	@Override
	public void setAccountsList(List<Account> accountsList) {
		this.accountsList = accountsList;		
	}
	
	@Transient
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CustomerImpl [customerId=");
		builder.append(customerId);
		builder.append(", customerType=");
		builder.append(customerType);
		builder.append(", firstName=");
		builder.append(firstName);
		builder.append(", surname=");
		builder.append(surname);
		builder.append(", email=");
		builder.append(email);
		builder.append(", countryCode=");
		builder.append(countryCode);
		builder.append(", accountsList=");
		builder.append(accountsList);
		builder.append("]");
		return builder.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((accountsList == null) ? 0 : accountsList.hashCode());
		result = prime * result
				+ ((countryCode == null) ? 0 : countryCode.hashCode());
		result = prime * result
				+ ((customerId == null) ? 0 : customerId.hashCode());
		result = prime * result
				+ ((customerType == null) ? 0 : customerType.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result
				+ ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((surname == null) ? 0 : surname.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CustomerImpl other = (CustomerImpl) obj;
		if (accountsList == null) {
			if (other.accountsList != null)
				return false;
		} else if (!accountsList.equals(other.accountsList))
			return false;
		if (countryCode == null) {
			if (other.countryCode != null)
				return false;
		} else if (!countryCode.equals(other.countryCode))
			return false;
		if (customerId == null) {
			if (other.customerId != null)
				return false;
		} else if (!customerId.equals(other.customerId))
			return false;
		if (customerType == null) {
			if (other.customerType != null)
				return false;
		} else if (!customerType.equals(other.customerType))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (surname == null) {
			if (other.surname != null)
				return false;
		} else if (!surname.equals(other.surname))
			return false;
		return true;
	}

	
}
