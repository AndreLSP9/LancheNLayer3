package br.com.lanche.facades;

import br.com.lanche.applications.LancheApplication;
import br.com.lanche.models.Lanche;

import java.io.IOException;
import java.util.List;

public class LancheFacade {
    private final LancheApplication lancheApplication;

    public LancheFacade(LancheApplication lancheApplication) {
        this.lancheApplication = lancheApplication;
    }

    public List<Lanche> buscarTodos() throws IOException {
        return this.lancheApplication.buscarTodos();
    }

    public Lanche buscarPorId(Lanche lanche) throws IOException {
        return this.lancheApplication.buscarPorId(lanche);
    }

    public void adicionar(Lanche lanche) throws IOException {
        this.lancheApplication.adicionar(lanche);
    }

    public void excluir(Lanche lanche) throws IOException {
        this.lancheApplication.excluir(lanche);
    }

    public void atualizar(int id, Lanche lanche) throws IOException {
        this.lancheApplication.atualizar(lanche.getId(), lanche);
    }

    public double calcularTotal(Lanche lanche, int quantidade) {
        return this.lancheApplication.calcularTotal(lanche, quantidade);
    }

    public boolean remover(int id) {
        return false;
    }

    public Lanche buscarPorId(int id) {
        return null;
    }
}