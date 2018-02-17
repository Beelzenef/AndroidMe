# Clases POJO

Las Clases POJO (Plain Old Java Object) son fragmentos de código dedicados exclusivamente a contener los datos de una entidad de forma ordenada.

Son las Clases de los clásicos ejemplos de Persona, Coche... o Usuarios, Mensajes. Cualquier dato que necesitemos categorizar de esta forma.

Serán la base para guardar entidades en bases de datos, ficheros HTML... o simplemente listas dinámicas, con las que operaremos para aprender.

```Java
class Libro {
 
    private int codigo;
    private String titulo;

    public int getCodigo () { return this.codigo; }
    public void setCodigo (int c) { this.codigo = c; }
    public String getTitulo () { return this.titulo; }
    public void setTitulo (String t) { return this.titulo = t; }
}
```

Con unos pocos datos, con respectivos _getters_ y _setters_ para cada campo, ya podemos operar.

## Repositorios

Siguiendo con el patrón Singleton, en el que se maneja una y solo una instancia de un elemento, en este caso una lista, generamos una lista dinámica para controlar todos los libros creados.

 ```Java
class RepositorioLibros {
    
    private RepositorioLibros repoLibros;
    private ArrayList<Libro> listaLibros;

    static {
        repoLibros = new RepositorioLibro();
    }

    public static RepositorioLibros getInstance() {
        return repoLibros();
    }

    private RepositorioLibros() {
        listaLibros = new ArrayList<Libro>();
    }

    public ArrayList<Libro> getLibros() {
        return listaLibros;
    }
}
```

---
#### [Volver a inicio](../../README.md)