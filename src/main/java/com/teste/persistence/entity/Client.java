package com.teste.persistence.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;

import com.teste.enums.ClientType;
import com.teste.enums.Status;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity(name = "client")
public class Client {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@NotBlank
	@Column(name = "name", length = 80)
	private String name;

	@NotNull
	@Column(name = "type")
	@Enumerated(EnumType.STRING)
	private ClientType type;

	@NotNull
	@NotBlank
	@Column(name = "cpg", length = 18)
	private String cpg;

	@Column(name = "identity", length = 15)
	private String identity;
	
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;
	
	@ManyToOne
    @JoinColumn(name="group_id", nullable=false)
	private Group group = new Group();

	@Column(name = "status")
	@Enumerated(EnumType.STRING)
	private Status status;
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "client_id")
	private Set<Phone> phones = new HashSet<Phone>();
	
}
