package com.cinemasystemspring.model;


import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
@Entity
@Table(name = "seats")
public class Seat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(name = "row_identifier", length = 3)
    private String rowIdentifier; // e.g A, B, C, D, E

    @Column(name = "seat_number")
    private Integer seatNumber; //e.g 1 ,2 ,3

    @ToString.Exclude
    @ManyToMany(mappedBy = "seats")
    private Set<Purchase> purchases = new HashSet<>();
}

