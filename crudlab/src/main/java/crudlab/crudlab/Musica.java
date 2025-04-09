package crudlab.crudlab;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Musica {
    @Id
    @GeneratedValue
    private Long id;

    private String titulo;
    private String compositor;
    private int ano;

    public Musica() {}

    public Musica(String titulo, String compositor, int ano) {
        this.titulo = titulo;
        this.compositor = compositor;
        this.ano = ano;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getCompositor() {
        return compositor;
    }

    public void setCompositor(String compositor) {
        this.compositor = compositor;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }
} 
