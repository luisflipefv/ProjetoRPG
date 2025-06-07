package dev.luisf.ProjetoRPG.Inventario;

import dev.luisf.ProjetoRPG.Item.ItemModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/inventario")
public class InventarioController {
    private InventarioService inventarioService;
    public InventarioController(InventarioService inventarioService) {
        this.inventarioService = inventarioService;
    }

    //post - para criar o inventario
    @PostMapping("/{idInventario}/itens")
    public ResponseEntity<?> adicionaItemAoInventario(@PathVariable Long idInventario, @RequestBody ItemModel novoItem){
        ItemModel itemAdicionado = inventarioService.adicionaItemInventario(idInventario, novoItem);
        if (itemAdicionado != null) {
            return new ResponseEntity<>(itemAdicionado, HttpStatus.CREATED);
        } else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //put - para atualizar item no inventario
    @PutMapping("/{idInventario}/itens/{idItem}")
    public ResponseEntity<ItemModel> atualizaItemInventario(@PathVariable Long idInventario, @PathVariable Long idItem, @RequestBody ItemModel itemAtualizado){
        ItemModel atualizaItem = inventarioService.atualizaItemNoInventario(idInventario, idItem, itemAtualizado);
        if (atualizaItem != null){
            return new ResponseEntity<>(atualizaItem, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //delete - para deletar o inventario
    @DeleteMapping("/{idInventario}/itens/{idItem}")
    public ResponseEntity<?> deletarItemInventario(@PathVariable Long idInventario, @PathVariable Long idItem){
        InventarioModel inventario = inventarioService.procuraInventarioPorId(idInventario);

        if (inventario == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        boolean itemRemovido = inventarioService.removeItemInventario(idInventario, idItem);

        if (itemRemovido){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //get - para buscar um inventario por id
    @GetMapping("/{id}")
    public ResponseEntity<InventarioModel> procurarInventarioPorId(@PathVariable Long id){
        InventarioModel inventarioEncontrado = inventarioService.procuraInventarioPorId(id);
        if (inventarioEncontrado != null){
            return new ResponseEntity<>(inventarioEncontrado, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //get - para listar todos os inventarios
    @GetMapping("/{idInventario}/itens")
    public ResponseEntity<List<ItemModel>> listarTodosItensInventario(@PathVariable Long idInventario){
        List<ItemModel> listaItens = inventarioService.listarItens(idInventario);
        if (listaItens != null){
            return new ResponseEntity<>(listaItens, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
