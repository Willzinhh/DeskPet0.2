package br.cspi.controller;

import br.cspi.model.Clientes;
import br.cspi.model.Pet;
import br.cspi.service.ClientesService;
import br.cspi.service.PetsService;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/Cliente")
public class ClienteController {

    @GetMapping
    public String Clientes(HttpSession session, Model model) {
        Integer id = (Integer) session.getAttribute("usuarioId");
        System.out.println(id);
        if (id == null) {
            return "redirect:/login";
        }else {
            List<Clientes> clientes = new ClientesService().listarClientes(id);
            model.addAttribute("clientes", clientes);
            return "pages/tabelaClientes";
        }
    }

    @GetMapping("/Cadastrar")
    public String Cadastrar(HttpSession session, Model model) {
        Integer id = (Integer) session.getAttribute("usuarioId");
        System.out.println(id);
        if (id == null) {
            return "redirect:/login";
        }else {
            model.addAttribute("idUsuario", id);
            return "pages/cadastroCliente";
        }
    }


    @PostMapping("/Cadastro")
    public String cadastrarCliente(
            @RequestParam("nome") String nome,
            @RequestParam(value = "telefone", required = false) String telefone,
            @RequestParam(value = "cpf", required = false) String cpf,
            @RequestParam(value = "endereco", required = false) String endereco,
            @RequestParam(value = "nomepet", required = false) List<String> nomesPet,
            @RequestParam(value = "especie", required = false) List<String> especies,
            @RequestParam(value = "raca", required = false) List<String> racas,
            @RequestParam(value = "sexo", required = false) List<String> sexos,
            @RequestParam(value = "descricao", required = false) List<String> descricoes,
            @RequestParam(value = "opcao", required = false) int id ){

        Clientes cliente = new Clientes();
        cliente.setNome(nome);
        cliente.setTelefone(telefone);
        cliente.setCpf(cpf);
        cliente.setEndereco(endereco);


        System.out.println(id+"oi");
        cliente.setCliente_usuario_id(id);

        System.out.println("Cliente: " + nome + " (Usuario ID: " + id + ")");

        List<Pet> pets = new ArrayList<Pet>();
        if (nomesPet != null && nomesPet.size() > 0) {
            for (int i = 0; i < nomesPet.size(); i++) {
                Pet pet = new Pet();
                pet.setNomepet(nomesPet.get(i));
                pet.setEspecie(especies.get(i));
                pet.setRaca(racas.get(i));
                pet.setSexo(sexos.get(i));
                pet.setDescricao(descricoes.get(i));
                pets.add(pet);

            }
        }
        if ( new ClientesService().cadastrarCliente(cliente ,id)){
            System.out.println("Cliente cadastrado com sucesso");
            if (nomesPet != null && nomesPet.size() > 0) {
                if (new PetsService().cadastrarPets(pets, cliente, id)) {
                    System.out.println("Pets cadastrado com sucesso");
                } else {
                    System.out.println("Erro ao cadastrar pets");
                }
            }
        }
        else {
            System.out.println("Erro ao cadastrar cliente");
        }







        return "redirect:/Cliente";
    }


    @PostMapping("/Excluir")
    public String excluirCliente(@RequestParam("opcao") int cliente_id) {
        new ClientesService().excluirCliente(cliente_id);
        return "redirect:/Cliente";
    }

    @PostMapping("/Edicao")
    public String edicaoCliente(@RequestParam("opcao") int cliente_id, HttpSession session,Model model) {
        Integer id = (Integer) session.getAttribute("usuarioId");
        System.out.println(id);
        Clientes cliente = new ClientesService().buscarCliente(cliente_id, id);

        model.addAttribute("cliente", cliente);
        return "pages/editar";
    }
    @PostMapping("/Editar")
    public String editarCliente(HttpSession session,
                                @RequestParam("opcao") int cliente_id,
                                @RequestParam("nome") String nome,
                                @RequestParam("telefone") String telefone,
                                @RequestParam("cpf") String cpf,
                                @RequestParam("endereco") String endereco){

        Clientes cliente = new Clientes();
        cliente.setNome(nome);
        cliente.setTelefone(telefone);
        cliente.setCpf(cpf);
        cliente.setEndereco(endereco);
        cliente.setId(cliente_id);

        if (new ClientesService().editarCliente(cliente ,cliente_id)) {}
        return "redirect:/Cliente";
    }


}
