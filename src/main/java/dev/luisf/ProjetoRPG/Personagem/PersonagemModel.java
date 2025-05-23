package dev.luisf.ProjetoRPG.Personagem;

import dev.luisf.ProjetoRPG.Inventario.InventarioModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_personagem")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class PersonagemModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nome", nullable = false, unique = true)
    private String nome;

    @Column(name = "nivel", nullable = false)
    private int nivel;

    @Column(name = "classe", nullable = false)
    private PersonagemEnum classe;

    @Column(name = "pontos_de_vida", nullable = false)
    private int pontosDeVida;

    @OneToOne(mappedBy = "personagem", orphanRemoval = true, cascade = CascadeType.ALL)
    private InventarioModel inventario;

}
