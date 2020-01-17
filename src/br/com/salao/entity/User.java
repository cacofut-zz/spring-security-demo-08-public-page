package br.com.salao.entity;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "user")
@Data
@NoArgsConstructor
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "username")
	private String username;
	
	@Column(name = "password")
	private String password;

	@Column(name = "email")
	@Email
	private String email;
	
	@Column(name = "enabled")
	private Integer enabled;
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "user_role", 
		joinColumns = @JoinColumn(name = "id"), 
		inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Collection<Role> roles;
	
	public User(String username, String password, String email, Integer enabled) {
		this(null, username, password, email, enabled);
	}

	public User(Long id, String username, String password, String email, Integer enabled) {		
		this.id		  = id;
		this.username = username;
		this.password = password;
		this.email    = email;
		this.enabled  = enabled;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", email=" + email
				+ ", enabled=" + enabled + "]";
	}
	
	public void addRole( Role role ) {
		if( roles != null ) {
			roles.add(role);
		}
	}
	
	public boolean isEnabled() {
		return getEnabled() == 1;
	}
		

}
