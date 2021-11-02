package com.innovarionone.spring_web_mvc.rest;

/*para la creacion de un modelo Rest es necesarioo usar un controlador REST*/
/*Eso se hace mediante el uno de la anotacion @RestController*/

import com.innovarionone.spring_web_mvc.models.Personaje;
import com.innovarionone.spring_web_mvc.repositories.repositoryPersonaje;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;


/*los controles rest por lo general son llamador recursos*/
@RestController
public class resoursePersonaje {
        /*Un recurso de personaje da la posiblidad recuperar los personajes
        *crear nuevos personajes, eliminar y actulisar tal recurso
        * atra ves de esta clase recurso oersnaje
        *
        * Asi como el modelo MVC se va a dar toda la interacion con el modelo de datos
        * */

        /*Primero vamos a intectar la dependdeia mediante el repositorio */
        @Autowired
        private repositoryPersonaje repoPersonaje;

        /*debemsos tener en cuenta comohacer las Operaciones CRUD*/


        /*Ddevmos tener en cuenta el metodo de lista todos los recursos */
        @GetMapping("/api/jedi")
        public ArrayList<Personaje> getAllPersonaje(){
                //este nos devuelve todolo los recuros almacenados en el banco de datos
                return repoPersonaje.listPersonajes();
        }

        //me quede en el minuto 9

}
