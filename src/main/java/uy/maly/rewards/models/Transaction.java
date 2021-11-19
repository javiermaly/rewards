package uy.maly.rewards.models;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
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
public class Transaction implements Scored {
	@Id
	@SequenceGenerator(name = "transaction_generator", sequenceName = "transction_seq", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "transaction_generator")
	private Long id;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "customer_id", nullable = false)
	private Customer customer;
	@Temporal(TemporalType.TIMESTAMP)
	private Date transactionDate;
	private float amount;

	
	public int getMonth() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(this.getTransactionDate());
		int month = cal.get(Calendar.MONTH) + 1;
		return month;
	}


	@Override
	public int getScore() {
		int points = (int) (this.amount > 100 ? 2 * (Math.round(this.amount) - 100) : 0);
		points += this.amount > 50 ? Math.round(this.amount) - 50 : 0;
		
//		int points = this.amount > 50 ? Math.min(50, Math.round( 1 * (this.amount - 50) ) ) : 0;
//		points += this.amount > 100 ? 2 * (this.amount - 100) : 0;
		return points;
	}
	
}
