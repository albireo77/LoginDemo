package org.dmx.demo.domain;

import java.util.Date;

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.dmx.demo.utils.BooleanYNConverter;

@Entity
@Table(name = "USERS")
public class User {

    @Id
    @SequenceGenerator(name="USER_SEQ", sequenceName="USER_SEQ", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="USER_SEQ")
    protected Integer id;
    
    @Size(max=25, message="First Name must not have more than 25 characters")
	private String firstName;
    
    @Size(max=25, message="Last Name must not have more than 25 characters")
	private String lastName;
    
    @Pattern(regexp="^[a-zA-Z0-9._-]{8,16}$", message="Username must have between 8 and 16 characters. Allowed characters: a-z A-Z 0-9 . _ -")
	private String username;
    
	@Size(min=8, max=16, message="Password must have between 8 and 16 characters")
	private String password;
	
	private byte[] image;
	
	private Date birthDate;
	
	@Convert(converter=BooleanYNConverter.class)
	private boolean admin;
	
    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {  	
    	if (isNew()) {
    		this.id = id;
    	}
    }
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public Date getBirthDate() {
		return birthDate;
	}
	
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
	
	public boolean isAdmin() {
		return this.admin;
	}
	
	public void setAdmin(boolean admin) {
		this.admin = admin;
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
    
	public boolean isNew() {
		return (id == null);
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}
}
