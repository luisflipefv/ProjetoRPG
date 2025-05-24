package dev.luisf.ProjetoRPG.Inventario;

import dev.luisf.ProjetoRPG.Item.ItemModel;
import dev.luisf.ProjetoRPG.Personagem.PersonagemModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_inventario_personagem")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class InventarioModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "capacidade_maxima")
    private int capacidadeMaxima;

    @OneToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "personagem_id")
    private PersonagemModel personagem;

    @OneToMany(mappedBy = "inventario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItemModel> itens = new ArrayList<>();
}
