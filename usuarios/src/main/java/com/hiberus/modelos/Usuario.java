package com.hiberus.modelos;

import com.sun.istack.NotNull;
import lombok.*;
import javax.persistence.*;
import java.util.Set;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "usuarios")
@Entity
@Getter
@EqualsAndHashCode
@ToString
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Setter
    @NotNull
    @Column(name = "nombre")
    private String nombre;
    @Setter
    @ElementCollection
    @CollectionTable(name = "usuario_pizzas_fav", joinColumns = @JoinColumn(name = "usuario_id"))
    @Column(name = "pizza")
    private Set<Integer> pizzasFav;
}
