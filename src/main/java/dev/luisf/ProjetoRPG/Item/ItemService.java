package dev.luisf.ProjetoRPG.Item;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemService {
    private ItemRepository itemRepository;

    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    //Criar itens
    public ItemModel criaItem(ItemModel item){
        return itemRepository.save(item);
    }

    //Deletar itens
    public void deletaItem(Long id){
        itemRepository.deleteById(id);
    }

    //Listar itens
    public List<ItemModel> listarItens(){
        return itemRepository.findAll();
    }

    //Listar Por ID
    public ItemModel listarItemPorId(Long id){
        Optional<ItemModel> itemPorId = itemRepository.findById(id);
        return itemPorId.orElse(null);
    }

    //Alterar itens
    public ItemModel atualizaItem(Long itemId, ItemModel itemAtualizado){
        return itemRepository.findById(itemId).map(itemExistente -> {
            if (itemAtualizado.getNome() != null && !itemAtualizado.getNome().isBlank()){
                itemExistente.setNome(itemAtualizado.getNome());
            }
            if (itemAtualizado.getClasse() != null){
                itemExistente.setClasse(itemAtualizado.getClasse());
            }
            if (itemAtualizado.getQuantidade() >= 0){
                itemExistente.setQuantidade(itemAtualizado.getQuantidade());
            }
            return itemRepository.save(itemExistente);
        }).orElse(null);
    }
}
