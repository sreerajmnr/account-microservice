package com.banking.core.model.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import com.banking.core.constant.AccountStatus;
import com.banking.core.constant.AccountType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "accounts")
public class AccountEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "account_number")
	private String accountNumber;

	@Column(name = "branch_code")
	private String branchCode;

	@Enumerated(EnumType.STRING)
	@Column(name = "account_type")
	private AccountType accountType;

	@Enumerated(EnumType.STRING)
	@Column(name = "account_status")
	private AccountStatus accountStatus;

	@Column(name = "balance")
	private BigDecimal accountBalance;

	@ManyToOne
	@JoinColumn(name = "customer_id", referencedColumnName = "customer_id")
	private CustomerEntity customer;

}
