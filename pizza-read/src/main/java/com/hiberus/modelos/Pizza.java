package com.hiberus.modelos;

import lombok.*;

import javax.persistence.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "pizza_read")
@Entity
@Getter
public class Pizza {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Setter
    @Column(name = "nombre")
    private String nombre;
}
