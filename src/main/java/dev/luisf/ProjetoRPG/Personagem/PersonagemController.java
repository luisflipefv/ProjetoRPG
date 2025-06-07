package dev.luisf.ProjetoRPG.Personagem;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/personagem")
public class PersonagemController {

    private PersonagemService personagemService;
    public PersonagemController(PersonagemService personagemService){
        this.personagemService = personagemService;
    }

    //post - para criar o personagem
    @PostMapping
    public ResponseEntity<PersonagemModel> criaPersonagem(@RequestBody PersonagemModel personagem){
        PersonagemModel personagemCriado = personagemService.criaPersonagem(personagem);
        return new ResponseEntity<>(personagemCriado, HttpStatus.CREATED);
    }

    //put - para atualizar um personagem existente
    @PutMapping("/{id}")
    public ResponseEntity<PersonagemModel> atualizaPersonagem(@PathVariable Long id, @RequestBody PersonagemModel persongemNovo){
        PersonagemModel personagemAtualizado = personagemService.alteraPersonagem(id, persongemNovo);
        if (personagemAtualizado != null){
            return new ResponseEntity<>(personagemAtualizado, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //delete - para deletar o personagem
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletaPersonagem(@PathVariable Long id){
        if (personagemService.listarPersonagemPorId(id) != null){
            personagemService.deletaPersonagem(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //get - para listar todos os personagens
    @GetMapping
    public ResponseEntity<List<PersonagemModel>> listarTodosPersonagens(){
        List<PersonagemModel> personagens = personagemService.listarPersonagens();
        return new ResponseEntity<>(personagens, HttpStatus.OK);
    }

    //get - listar personagem por id
    @GetMapping("/{id}")
    public ResponseEntity<PersonagemModel> listaPersonagemPorId(@PathVariable Long id){
        PersonagemModel personagemListado = personagemService.listarPersonagemPorId(id);
        if (personagemListado != null){
            return new ResponseEntity<>(personagemListado, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }




}
