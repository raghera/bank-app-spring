/**
 * 
 */
package com.ravi.bank.domain.security.service.jpa.entities;

import java.util.List;
import java.util.Set;

import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.ravi.bank.domain.service.jpa.entities.Customer;

/**
 * Represents the Users Security table.
 * 
 * @author Ravi Aghera
 *
 */
@Entity
@Table(name="USERS")
public class User {

	private Long userId;
	private String userName;
	private String password;
	private Boolean enabled;
	private List<Authority> authorityList;

	//Required
	public User() {
	}
	
	public User(Customer customerDetails) {
		this.setEnabled(true);
		this.setUserName(customerDetails.getEmail());
		this.setPassword(customerDetails.getPassword());
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="user_seq")
	@SequenceGenerator(name="user_seq", sequenceName="USER_SEQ", allocationSize=1)
	@Column(name="USER_ID")
	public Long getUserId() {
		return userId;
	}
	
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	@Column(name="username")
	public String getUserName() {
		return userName;
	}
	public void setUserName(String username) {
		this.userName = username;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public Boolean getEnabled() {
		return enabled;
	}
	@Column(name="enabled", columnDefinition="NUMBER(1) NOT NULL")
	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy="user", targetEntity=Authority.class)
	public List<Authority> getAuthorityList() {
		return authorityList;
	}
	
	public void setAuthorityList(List<Authority> authorityList) {
		
		this.authorityList = authorityList;
		
		//Maintain Entity relationship
		for (Authority authority : authorityList) {
						
			//If not already there or different set it
			if( authority.getUser() == null || authority.getUser().getUserId() == null ) {
				authority.setUser(this);
			}
			else if( !authority.getUser().equals(this) ) {
				authority.setUser(this);
			}
		}
		
	}
	
	/**
	 * To add a new authority you should use this method rather than the set method.
	 * @param authority
	 */
	public void addAuthority(Authority authority) {
		
		this.authorityList.add(authority);
		
		//Maintain the relationship
		//If not already there or different set it
		if( authority.getUser() == null || authority.getUser().getUserId() == null ) {
			authority.setUser(this);
		}
		//I.e. the same object in memory - ensures that it works
		else if( !authority.getUser().equals(this) ) {
			authority.setUser(this);
		}
		
	}	
	
}
