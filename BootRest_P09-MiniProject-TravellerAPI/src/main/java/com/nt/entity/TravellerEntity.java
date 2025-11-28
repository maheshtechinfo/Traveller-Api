package com.nt.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Entity
@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class TravellerEntity {
	// Data Properties
	@Id
	@SequenceGenerator(name = "gen1", sequenceName = "TID_SEQ", initialValue = 1000, allocationSize = 1)
	@GeneratedValue(generator = "gen1", strategy = GenerationType.SEQUENCE)
	private Integer tId;

	@NonNull
	@Column(length = 30)
	private String name;

	@Column(length = 30)
	@NonNull
	private String country;

	@NonNull
	private Long mobileno;

	@NonNull
	private LocalDate dob;

	@NonNull
	@Column(length = 30)
	private String currentLocation;

	@NonNull
	@Column(length = 30)
	private String headingTo;

	@CollectionTable(name = "TRAVELLER_VISITED_COUNTRIES", // Table name for the collection
			joinColumns = @JoinColumn(name = "TRAVELLER_ID", referencedColumnName = "TID")) // foreign key column
	@ElementCollection
	@NonNull
	private List<String> visitedCountries;

	// metadata properties

	@Version
	private Integer updateCount;

	@CreationTimestamp
	private LocalDateTime registerOn;

	@UpdateTimestamp
	private LocalDateTime lastUpdatedOn;

	@Column(length = 30)
	private String createdBy;

	@Column(length = 30)
	private String updatedBy;

}
