package com.banco.sangre.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "blood_inventory")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InventarioSangre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String tipoSangre;

    @Column(nullable = false)
    private Double cantidadMl;
}