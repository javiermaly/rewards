package uy.maly.rewards.models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "transaction")
public class Transaction {
	@Id
	@SequenceGenerator(name = "transaction_generator", sequenceName = "transction_seq", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "transaction_generator")
	private Long id;
	@ManyToOne
	@JoinColumn(name = "transaction_id", nullable = false)
	private Customer customer;
	@Temporal(TemporalType.TIMESTAMP)
	private Date transactionDate;
	private float amount;

}
