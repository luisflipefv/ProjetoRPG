package dev.luisf.ProjetoRPG.Inventario;

import dev.luisf.ProjetoRPG.Item.ItemModel;
import dev.luisf.ProjetoRPG.Item.ItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InventarioService {
    private InventarioRepository inventarioRepository;
    private ItemRepository itemRepository;

    public InventarioService(InventarioRepository inventarioRepository, ItemRepository itemRepository){
        this.inventarioRepository = inventarioRepository;
        this.itemRepository = itemRepository;
    }
    //Encontrar um inventario por ID
    public InventarioModel procuraInventarioPorId (Long id){
        Optional<InventarioModel> inventarioPorId = inventarioRepository.findById(id);
        return inventarioPorId.orElse(null);
    }

    //Adiciona item no inventario
    public ItemModel adicionaItemInventario(Long idInventario, ItemModel novoItem){
        return inventarioRepository.findById(idInventario).map(inventario -> {
            if (inventario.getItens().size() >= inventario.getCapacidadeMaxima()) {
                System.out.println("Inventário cheio! Não foi possível adicionar o item ao inventario");
                return null;
            }
            novoItem.setInventario(inventario);
            inventario.getItens().add(novoItem);
            inventarioRepository.save(inventario);

            return novoItem;
        }).orElse(null);
    }

    //Remove item no inventario
    public boolean removeItemInventario(Long inventarioId, Long itemId){
        return inventarioRepository.findById(inventarioId).map(inventario -> {
            Optional<ItemModel> itemParaRemover = inventario.getItens().stream()
                    .filter(item -> item.getId().equals(itemId))
                    .findFirst();
            if (itemParaRemover.isPresent()){
                inventario.getItens().remove(itemParaRemover.get());
                itemParaRemover.get().setInventario(null);
                inventarioRepository.save(inventario);
                return true;
            }
            return false;
        }).orElse(false);
    }

    //Atualiza intem no inventario
    public ItemModel atualizaItemNoInventario(Long inventarioId, Long itemId, ItemModel itemAtualizado){
        return inventarioRepository.findById(inventarioId).map(inventario -> {
            Optional<ItemModel> itemExistenteOptional = inventario.getItens().stream()
                    .filter(item -> item.getId().equals(itemId))
                    .findFirst();
            if (itemExistenteOptional.isPresent()){
                ItemModel itemExistente = itemExistenteOptional.get();

                if (itemAtualizado.getNome() != null && !itemAtualizado.getNome().isBlank()){
                    itemExistente.setNome(itemAtualizado.getNome());
                }
                if (itemAtualizado.getClasse() != null){
                    itemExistente.setClasse(itemAtualizado.getClasse());
                }
                if (itemAtualizado.getQuantidade() >= 0){
                    itemExistente.setQuantidade(itemAtualizado.getQuantidade());
                }

                itemExistente.setInventario(inventario);

                return itemRepository.save(itemExistente);
            }
            return null;
        }).orElse(null);
    }

    //Lista Itens do inventario
    public List<ItemModel> listarItens(Long inventarioId){
        return inventarioRepository.findById(inventarioId)
                .map(InventarioModel::getItens)
                .orElse(null);
    }
}
