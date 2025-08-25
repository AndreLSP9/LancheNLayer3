package br.com.lanche.applications;

import br.com.lanche.models.Lanche;
import br.com.lanche.repositories.LancheRepository;
import br.com.lanche.services.LancheService;

import java.io.IOException;
import java.util.List;

public class LancheApplication {
    private LancheService lancheService;
    private LancheRepository lancheRepository;

    public LancheApplication(LancheRepository lancheRepository, LancheService lancheService) {
        this.lancheService = lancheService;
        this.lancheRepository = lancheRepository;
    }

    public List<Lanche> buscarTodos() throws IOException {
        return this.lancheRepository.buscarTodos();
    }

    public Lanche buscarPorId(Lanche lanche) throws IOException {
        return this.lancheRepository.buscarPorId(lanche.getId());
    }

    public void adicionar(Lanche lanche) throws IOException {
        this.lancheRepository.adicionar(lanche);
        this.lancheService.salvarImagem(lanche);
    }

    public void excluir(Lanche lanche) throws IOException {
        this.lancheRepository.excluir(lanche.getId());
    }

    public void atualizar(int id, Lanche lanche) throws IOException {
        this.lancheRepository.atualizar(lanche.getId(), lanche);
    }

    public double calcularTotal(Lanche lanche, int quantidade) {
        return lanche.calcularLanche(quantidade);
    }
}
