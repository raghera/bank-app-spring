/**
 * 
 */
package com.ravi.bank.domain.security.service.jpa.entities;

import java.util.List;

import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.ravi.bank.domain.service.jpa.entities.AccountImpl;

/**
 * 
 * 
 * @author Ravi Aghera
 *
 */
@Entity
@Table(name="AUTHORITIES")
public class Authority {

	
	private Long id;
	private String authority;
	private User user;
	
	@Id	
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="auth_seq")
	@SequenceGenerator(name="auth_seq", sequenceName="USER_SEQ", allocationSize=1)
	@Column(name="AUTH_ID")
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getAuthority() {
		return authority;
	}
	public void setAuthority(String authority) {
		this.authority = authority;
	}

	@ManyToOne(fetch=FetchType.LAZY, targetEntity=User.class ) //TargetEntity=User
	@JoinColumn(name="USER_ID", referencedColumnName="USER_ID")
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
		
		if( !user.getAuthorityList().contains(this) ) {
			user.addAuthority(this);
		}
		
	}
}
