package dev.luisf.ProjetoRPG.Personagem;

import dev.luisf.ProjetoRPG.Inventario.InventarioModel;
import dev.luisf.ProjetoRPG.Inventario.InventarioRepository;
import dev.luisf.ProjetoRPG.Item.ItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonagemService {
    private PersonagemRepository personagemRepository;
    private InventarioRepository inventarioRepository;
    public PersonagemService(PersonagemRepository personagemRepository, InventarioRepository inventarioRepository){
        this.personagemRepository = personagemRepository;
        this.inventarioRepository = inventarioRepository;
    }

    //Cria personagem
    public PersonagemModel criaPersonagem(PersonagemModel personagem){
        PersonagemModel personagemNovo = personagemRepository.save(personagem);

        InventarioModel inventarioNovo = new InventarioModel();
        inventarioNovo.setCapacidadeMaxima(50);
        inventarioNovo.setPersonagem(personagemNovo);

        personagemNovo.setInventario(inventarioNovo);

        inventarioRepository.save(inventarioNovo);
        
        return personagemNovo;
    }

    //Deleta personagem
    public void deletaPersonagem(Long id){
        personagemRepository.deleteById(id);
    }

    //Listar personagens
    public List<PersonagemModel> listarPersonagens(){
        return personagemRepository.findAll();
    }

    //Listar por id
    public PersonagemModel listarPersonagemPorId (Long id){
        Optional<PersonagemModel> personagemPorId = personagemRepository.findById(id);
        return personagemPorId.orElse(null);
    }

    //Altera personagem
    public PersonagemModel alteraPersonagem(Long id, PersonagemModel personagemAtualizado){
        return personagemRepository.findById(id).map(personagemNovo -> {
            if (personagemAtualizado.getNome() != null && !personagemAtualizado.getNome().isBlank()){
                personagemNovo.setNome(personagemAtualizado.getNome());
            }
            if (personagemAtualizado.getNivel() >= 0){
                personagemNovo.setNivel(personagemAtualizado.getNivel());
            }
            if (personagemAtualizado.getClasse() != null){
                personagemNovo.setClasse(personagemAtualizado.getClasse());
            }
            if (personagemAtualizado.getPontosDeVida() >= 0){
                personagemNovo.setPontosDeVida(personagemAtualizado.getPontosDeVida());
            }
            return personagemRepository.save(personagemNovo);
        }).orElse(null);
    }

}
