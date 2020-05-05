package com.utn.phones.model;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
@ToString
@Table(name = "cities")
public class City {

  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Id
  private Integer id;

  @NotNull
  private Integer prefix;

  @NotNull
  private String name;

  @NotNull
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "province")
  private Province province;

  @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "city")
  @ToString.Exclude
  private List<User> users;

  @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "originCity")
  @ToString.Exclude
  private List<Call> originCalls;

  @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "destinyCity")
  @ToString.Exclude
  private List<Call> destinyCalls;

  @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "originCity")
  @ToString.Exclude
  private List<Tariff> originCity;

  @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "destinyCity")
  @ToString.Exclude
  private List<Tariff> destinyCity;
}
