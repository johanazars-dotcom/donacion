package com.banco.sangre.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "consents")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Consentimiento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "donor_id", nullable = false)
    private Donante donante;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String firmaDocumento;
}