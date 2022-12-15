package com.home.ediaristas.controller;

import java.io.IOException;

import javax.validation.Valid;

import com.home.ediaristas.models.Diarista;
import com.home.ediaristas.repository.DiaristaRepository;
import com.home.ediaristas.service.FileService;
import com.home.ediaristas.service.ViaCepService;
import com.home.ediaristas.validator.CepValidator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/admin/diaristas")
public class DiaristaController {

    @Autowired
    private DiaristaRepository repository;

    @Autowired
    private FileService fileService;

    @Autowired
    private ViaCepService viaCepService;

    @Autowired
    private CepValidator cepValidator;

    // método para iniciar o cep validator
    // esse "diarista" é o mesmo lá dos métodos [ abaixo ]
    @InitBinder("diarista")
    private void initBinder(WebDataBinder binder) {
        binder.addValidators(cepValidator);
    }
    
    // carrega o formulário
    @GetMapping("/cadastrar")
    public ModelAndView cadastrar() {
        ModelAndView mv = new ModelAndView();
        // local onde está o formulário
        mv.setViewName("admin/diaristas/form");
        // envia o objeto "diarista" lá para o formulário
        mv.addObject("diarista", new Diarista());
        return mv;
    }

    // efetua o cadastro
    // o BindingResult deve vir imediatamente depois do dado a ser validado
    @PostMapping("/cadastrar")
    public String cadastrar(@RequestParam MultipartFile imagem, 
        @Valid Diarista diarista, BindingResult result) throws IOException {
    
        if (result.hasErrors()) {
            return "admin/diaristas/form";
        }

        // antes de salvar objeto, gravar foto
        var filename = fileService.gravar(imagem);
        diarista.setFoto(filename);

        // busca cep
        var cep = diarista.getCep();
        var endereco = viaCepService.buscarPorCep(cep);
        var codigoIbge = endereco.getIbge();
        diarista.setCodigoIbge(codigoIbge);

        repository.save(diarista);
        return "redirect:/admin/diaristas";
    }

    // carrega a página de lista
    @GetMapping
    public ModelAndView listar(@PageableDefault(size = 5) Pageable pageable) {
        // lista com paginação
        Page<Diarista> pageDiarista = repository.findAll(pageable);
        var mv = new ModelAndView("admin/diaristas/lista");
        mv.addObject("diaristas_view", pageDiarista);

        return mv;
    }

    // exclui registro
    @GetMapping("/{id}/excluir")
    public String remover(@PathVariable Long id) {
        repository.deleteById(id);
        return "redirect:/admin/diaristas";
    }

    // carrega o formulário para edição
    @GetMapping("/{id}/editar")
    public ModelAndView carregar(@PathVariable Long id) {
        ModelAndView mv = new ModelAndView();
        // local onde está o formulário
        mv.setViewName("admin/diaristas/form");
        // envia o objeto "diarista" lá para o formulário
        mv.addObject("diarista", repository.findById(id));
        return mv;
    }

    // efetua a atualização
    // o BindingResult deve vir imediatamente depois do dado a ser validado
    @PostMapping("/{id}/editar")
    public String editar(@RequestParam MultipartFile imagem, @PathVariable Long id, 
        @Valid Diarista diarista, BindingResult result) throws IOException {

        if (result.hasErrors()) {
            return "admin/diaristas/form";
        }

        // -------- tratamento do upload de imagem --------
        var diaristaAtual = repository.getById(id);

        // verifica se tem uma foto no cadastro
        if (imagem.isEmpty()) {
            diarista.setFoto(diaristaAtual.getFoto());
        } else {
            var filename = fileService.gravar(imagem);
            diarista.setFoto(filename);
        }
        // ----- fim tratamento de uploade de imagem ------

        // busca cep
        var cep = diarista.getCep();
        var endereco = viaCepService.buscarPorCep(cep);
        var codigoIbge = endereco.getIbge();
        diarista.setCodigoIbge(codigoIbge);

        // NÃO precisa enviar id pelo campo 'hidden'
        repository.save(diarista);
        return "redirect:/admin/diaristas";
    }
}
