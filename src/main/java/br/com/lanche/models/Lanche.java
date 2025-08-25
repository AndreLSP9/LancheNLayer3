package br.com.lanche.models;

public class Lanche {
    private int id;
    private String nome;
    private float preco;
    private String caminhoImagem;

    public Lanche(int id, String nome, float preco, String caminhoImagem) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
        this.caminhoImagem = caminhoImagem;
    }

    public Lanche(int id, String nome, double preco, String caminhoImagem) {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public float getPreco() {
        return preco;
    }

    public void setPreco(float preco) {
        this.preco = preco;
    }

    public String getCaminhoImagem() {
        return caminhoImagem;
    }

    public void setCaminhoImagem(String caminhoImagem) {
        this.caminhoImagem = caminhoImagem;
    }

    public double calcularLanche(int quantidade) {
        return this.preco * quantidade;
    }

    @Override
    public String toString() {
        return "Nome: " + nome + "\n" +
                "Preço: " + preco + "\n" +
                "Imagens: " + caminhoImagem;
    }
}

