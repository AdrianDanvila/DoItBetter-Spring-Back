package com.DoItBetter.app.model;

import java.sql.Date;
import java.util.Collection;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "Users")
public class User implements UserDetails {

	public User(String name, String email, String password, Number age, Number weight, Number height) {
		this.name = name;
		this.email = email;
		this.password = password;
		this.age = age;
		this.weight = weight;
		this.height = height;
	}

	public User() {
	}

	@Id
	@SequenceGenerator(name = "usuario_seq", sequenceName = "usuario_sequence", allocationSize = 1, initialValue = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "usuario_seq")
	private Long id;

	@Column(nullable = false)
	private String name;

	@Column()
	private String profilePictureName;

	@Column()
	private String profilePicturePath;

	@Column(name = "age", nullable = false)
	private Number age;

	@Column()
	private Number weight;

	@Column()
	private Number height;

	@Column(unique = true, length = 100, nullable = false)
	private String email;

	@Column(nullable = false)
	private String password;

	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
	private List<Routine> routines;

	@CreationTimestamp
	@Column(updatable = false, name = "created_at")
	private Date createdAt;

	@UpdateTimestamp
	@Column(name = "updated_at")
	private Date updatedAt;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return List.of();
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	// getters and setters

	public Date getCreatedAt() {
		return createdAt;
	}

	public String getEmail() {
		return email;
	}

	public String getName() {
		return name;
	}

	public Long getId() {
		return id;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public Number getAge() {
		return age;
	}

	public Number getHeight() {
		return height;
	}

	public List<Routine> getRoutines() {
		return routines;
	}

	public Number getWeight() {
		return weight;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public void setAge(Number age) {
		this.age = age;
	}

	public void setHeight(Number height) {
		this.height = height;
	}

	public void setRoutines(List<Routine> routines) {
		this.routines = routines;
	}

	public void setWeight(Number weight) {
		this.weight = weight;
	}

	public void setProfilePicturePath(String profilePicturePath) {
		this.profilePicturePath = profilePicturePath;
	}

	public void setProfilePictureName(String profilePictureName) {
		this.profilePictureName = profilePictureName;
	}

	public String getProfilePictureName() {
		return profilePictureName;
	}

	public String getProfilePicturePath() {
		return profilePicturePath;
	}
}
