package br.cspi.controller;

import br.cspi.model.Clientes;
import br.cspi.model.Pet;
import br.cspi.service.ClientesService;
import br.cspi.service.PetsService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/Pets")
public class PetController {

    @GetMapping
    public String Pets(HttpSession session, Model model) {
        Integer id = (Integer) session.getAttribute("usuarioId");
        System.out.println(id);

            List<Pet> pets = new PetsService().listarPets(id);
            model.addAttribute("pets", pets);
            return "pages/tabelaPets";

    }

    @PostMapping("/Excluir")
    public String excluirPet(@RequestParam("opcao") int pet_id) {
        new PetsService().excluirPet(pet_id);
        return "redirect:/Pets";
    }

    @PostMapping("/Edicao")
    public String edicaoPet(@RequestParam("opcao") int pet_id, HttpSession session,Model model) {
        Integer id = (Integer) session.getAttribute("usuarioId");
        System.out.println(id);
        Pet pet = new PetsService().buscarPet(pet_id,id);

        model.addAttribute("pet", pet);
        return "pages/editarPet";
    }
    @PostMapping("/Editar")
    public String editarCliente(HttpSession session,
                                @RequestParam("opcao") int pet_id,
                                @RequestParam("nomepet") String nomepet,
                                @RequestParam("especie") String especie,
                                @RequestParam("raca") String raca,
                                @RequestParam("sexo") String sexo,
                                @RequestParam("descricao") String descricao
    ){

        Pet pet = new Pet();
        pet.setId(pet_id);
        pet.setNomepet(nomepet);
        pet.setEspecie(especie);
        pet.setRaca(raca);
        pet.setSexo(sexo);
        pet.setDescricao(descricao);


        if (new PetsService().editarPet(pet, pet_id)){

            return "redirect:/Pets";
        }
        else {
            return "redirect:/Edicao";
        }
    }

    @PostMapping("/AdicionarPet")
    public String adicionarPet(@RequestParam("opcao") int cliente_id, HttpSession session, Model model) {
        Integer id = (Integer) session.getAttribute("usuarioId");
        System.out.println(id);

        model.addAttribute("idUsuario", id);
        model.addAttribute("idCliente", cliente_id);
        return "pages/addPet";
    }

    @PostMapping("/Adicionar")
    public String adicionar(@RequestParam("idUsuario") int id_Usuario,
                            @RequestParam("opcao") int id_Cliente,
                            @RequestParam("nomepet") String nomepet,
                            @RequestParam("especie") String especie,
                            @RequestParam("raca") String raca,
                            @RequestParam("sexo") String sexo,
                            @RequestParam("descricao") String descricao){

        Pet pet = new Pet();
        pet.setNomepet(nomepet);
        pet.setEspecie(especie);
        pet.setRaca(raca);
        pet.setSexo(sexo);
        pet.setDescricao(descricao);
        pet.setCliente_usuario_id(id_Usuario);
        pet.setTutor_id(id_Cliente);

        if (new PetsService().adicionarPets(pet, pet.getTutor_id(), pet.getCliente_usuario_id())){
            return "redirect:/Pets";
        }else {
            return "redirect:/AdicionarPet";
        }

    }
}
