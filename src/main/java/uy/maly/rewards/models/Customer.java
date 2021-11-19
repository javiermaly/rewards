package uy.maly.rewards.models;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Table(name = "customer", indexes = {@Index(name = "i_customer_username", columnList = "username")})
public class Customer {

	@Id
	@SequenceGenerator(name = "customer_generator", sequenceName = "customer_seq", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customer_generator")
	private Long id;
	@NonNull
	@Column(unique = true)
	private String username;
	@NonNull 
	private String familyName;
	@NonNull
	private String name;
    @OneToMany(mappedBy="customer")
	private Set<Transaction> transactions;
	
	
}
