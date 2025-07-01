package br.cspi.controller;

import br.cspi.model.Cliente_Usuario;
import br.cspi.model.Usuario;
import br.cspi.service.CadastrarService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/Cadastro")
public class CadastroController {

    @GetMapping
    public String cadastroPage() {
        return "cadastrar";
    }

    @PostMapping("/Cadastrar")
    public String cadastrar(@RequestParam("email") String email,
                            @RequestParam("senha")String senha,
                            @RequestParam("nome") String nome,
                            @RequestParam("cpf")String cpf,
                            @RequestParam("telefone")String telefone,
                            @RequestParam("endereco") String endereco,
                            @RequestParam("cnpj") String cnpj,
                            @RequestParam("nome_empresa")String nome_empresa,
                            @RequestParam("plano")String plano){
        Cliente_Usuario cliente = new Cliente_Usuario();
        cliente.setNome(nome);
        cliente.setCpf(cpf);
        cliente.setTelefone(telefone);
        cliente.setEndereco(endereco);
        cliente.setCnpj(cnpj);
        cliente.setNome_empresa(nome_empresa);
        cliente.setPlano(plano);

        Usuario usuario = new Usuario();
        usuario.setEmail(email);
        usuario.setSenha(senha);

        if (new CadastrarService().cadastrar(usuario, cliente)){
            return "redirect:/login";
        }
        else{
            return "redirect:/Cadastrar";
        }


    }
}
