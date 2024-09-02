package com.project.transactions.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "ACCOUNT")
public class Account {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column(name = "document_number", unique = true, nullable = false)
  private String documentNumber;
}
