package dev.luisf.ProjetoRPG.Item;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/item")
public class ItemController {
    private ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    //POST - Manda uma requisição para criar um item
    @PostMapping
    public ResponseEntity<ItemModel> criaItem(@RequestBody ItemModel itemModel){
        ItemModel novoItem = itemService.criaItem(itemModel);
        return new ResponseEntity<>(novoItem, HttpStatus.CREATED);
    }

    //PUT - Manda uma requisição para alterar um item
    @PutMapping("/{id}")
    public ResponseEntity<ItemModel> alteraItem(@PathVariable Long id, @RequestBody ItemModel itemModel){
        ItemModel itemAtualizado = itemService.atualizaItem(id, itemModel);
        if (itemAtualizado != null){
            return new ResponseEntity<>(itemAtualizado, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    //DELETE - Manda uma requisição para deletar um item
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletaItem(@PathVariable Long id){
        if (itemService.listarItemPorId(id) != null){
            itemService.deletaItem(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //GET - Manda uma requisição para retornar todos os itens
    @GetMapping
    public ResponseEntity<List<ItemModel>> listarTodosItens(){
        List<ItemModel> itens = itemService.listarItens();
            return new ResponseEntity<>(itens, HttpStatus.OK);
    }

    //GET - Manda uma requisição que retorna um item pelo ID
    @GetMapping("/{id}")
    public ResponseEntity<ItemModel> listarItemPorId(@PathVariable Long id){
        ItemModel itemListado = itemService.listarItemPorId(id);
        if (itemListado != null){
            return new ResponseEntity<>(itemListado, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
