package dev.luisf.ProjetoRPG.Item;

import dev.luisf.ProjetoRPG.Inventario.InventarioModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_item")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ItemModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "classe")
    private ItemEnum classe;

    @Column(name = "qtde")
    private int quantidade;

    @ManyToOne
    @JoinColumn(name = "inventario_id")
    private InventarioModel inventario;
}
