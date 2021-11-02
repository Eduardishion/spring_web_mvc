package com.innovarionone.spring_web_mvc.repositories;

import com.innovarionone.spring_web_mvc.models.Personaje;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;

@Repository
public class repositoryPersonaje {

    private ArrayList<Personaje> listPersonajes = null;

    //inicialisar lista de personajes
    public repositoryPersonaje() {

        listPersonajes = new ArrayList<>();
        listPersonajes.add(new Personaje(31,"pef","chof"));
        listPersonajes.add(new Personaje(42,"cof", "ner"));
        listPersonajes.add(new Personaje(44,"crf", "chaf"));

    }
    //mosrtar la lista de personajes
    public ArrayList listPersonajes(){
        return  listPersonajes;
    }
    //aggregar a la lista un personaje
    public void addPersonaje(final Personaje personaje){
        //acceso al arreglo intenno de la clase
        this.listPersonajes.add(personaje);
    }

    //busacar un personaje por id
    public Personaje findById(final Long id){

        //objeto temporal
        Personaje personajeE = null;

        for (int i = 0; i < listPersonajes.size(); i++) {
            if(listPersonajes.get(i).getId() == id){
                personajeE = listPersonajes.get(i);
            }
        }


        //System.out.println(personajeE);
        //retornamos  el objeto temporal encontrado
        return personajeE;
    }

    public void delete(final Personaje personaje){
        listPersonajes.remove(personaje);
    }


    public void update(final int id, final Personaje personaje){
        listPersonajes.set(id,personaje);
    }


}
