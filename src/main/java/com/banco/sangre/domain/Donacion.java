package com.banco.sangre.domain;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "donations")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Donacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "donor_id", nullable = false)
    private Donante donante;

    @Column(nullable = false, unique = true)
    private String codigoDonacion;

    @Column(nullable = false)
    private Double cantidadMl;

    @Column(nullable = false)
    private LocalDateTime fechaDonacion;

    @Column(columnDefinition = "TEXT")
    private String observaciones;
}