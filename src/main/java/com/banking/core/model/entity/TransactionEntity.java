package com.banking.core.model.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.banking.core.constant.TransactionType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

@Data
@Table(name = "transactions")
@Entity
public class TransactionEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "txn_id")
	private Long transactionId;

	@Enumerated(EnumType.STRING)
	@Column(name = "txn_type")
	private TransactionType transactionType;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "txn_date")
	private Date transactionDate;

	@Column(name = "txn_amount")
	private BigDecimal transactionAmount;

	@Column(name = "txn_account")
	private String transactionAccount;
}
