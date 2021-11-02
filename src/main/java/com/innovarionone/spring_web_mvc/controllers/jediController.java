package com.innovarionone.spring_web_mvc.controllers;

import com.innovarionone.spring_web_mvc.models.Personaje;
import com.innovarionone.spring_web_mvc.repositories.repositoryPersonaje;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.Optional;

//clase con la anotación @Controller para poder rederisar las vistas de templates*/
@Controller
public class jediController {

    //inyeccion de dependencias con @Autowired, es necesario para injectar dependencias
    /*
    * esto indica creame una instacia a ese repositorio
    * o instanciame a esa clase en particular
    * */
    @Autowired
    private repositoryPersonaje repoPersonaje;


    //------------------------------------metodos HTTP---------------------------------------

    //metodo get
    //endpint  para renderrisar la vista viewMensage
    @GetMapping("/viewMensage")  //ruta de acceso
    public String viewMensage(Model model){ //metodo que la manda a llamar

        //creacion y llenado de un array list para almcenar cadena de texto
        ArrayList <String>  nombres = new  ArrayList<String>();
        nombres.add("eduardo");
        nombres.add("Sandi");
        nombres.add("Took");

        //enviando un avariable de texto  ala vista
        model.addAttribute("mensaje","Hola mundo");//variable que sera enviada a vista

        //enviando un array a la vista
        //el primer parametro es el nombre de la variable, el segundo es la variable
        model.addAttribute("nombres",nombres);
        return "viewMensage";    //aqui se espeficica el nombre la vista que se quiere rendersar, pero se encuente en templtes

    }

    //metodo get
    //endpint  para renderrisar lista de jedis
    @GetMapping("/jedi")
    public String viewJedi(Model model){
        //manera normal de instaciar una clase
            /*
            ArrayList<Personaje> personajes = new ArrayList<>();
            Personaje personajeTmp = new Personaje();
            Personaje personajeTmp2 = new Personaje();

            personajeTmp.setId(12);
            personajeTmp.setName("Eduardo");
            personajeTmp.setLastName("isquierdo");

            personajeTmp2.setId(13);
            personajeTmp2.setName("Sandi");
            personajeTmp2.setLastName("Flores");

            personajes.add(personajeTmp);
            personajes.add(personajeTmp2);


            model.addAttribute("allJedi",personajes);

            */

        //manera con inyeccion dependecias -- pendiente
            ArrayList<Personaje> personajes = repoPersonaje.listPersonajes();

            model.addAttribute("allJedi",personajes);

        return "jedi";
    }

    //metodo get
    //endpint  para renderrisar la vista de nuevo jedi
    @GetMapping("/new_jedi")
    public String viewNewJedi(Model model){
        Personaje personajeTmp = new Personaje();
        /*
        personajeTmp.setId(12);
        personajeTmp.setName("Def");
        personajeTmp.setLastName("Caf");
        */
        model.addAttribute("personaje",personajeTmp);

        return "new_jedi";
    }



    //reciviendo paramentros desde la peticion hecha por el formulario
    //@ModelAttribute("jedi") solo es el objeto que viene desde el formulario
    //es el tipo de objeto en este cado Personaje personaje
    /*

    //otro metodo de validacion https://dev.to/reytech-lesson/using-lombok-in-spring-boot-i2b
    //validacion https://spring.io/guides/gs/validating-form-input/
    //https://dev.to/reytech-lesson/using-lombok-in-spring-boot-i2b

    * para usar @Valid
        usemos estas dependecias en el pom.xml

        <dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-validation</artifactId>
		</dependency>
		<dependency>
			<groupId>javax.validation</groupId>
			<artifactId>validation-api</artifactId>
		</dependency>


		------------------------exto funciona como express validator en nodejs--------------------------
		@Valid @ModelAttribute Personaje jedi : captura los atributos de entrada del formulario
		BindingResult result                  : verifica si hay errores de validasion si no son cumplidos
		RedirectAttributes redirectAttributes : sirve para mandar mensajes sobre los errores

    * */
    //https://programmerclick.com/article/532172368/    otro validacion
    //https://spring.io/guides/gs/validating-form-input/  mejor explicaicion
    //captura y validacion de datos de peticion de formulario por metodo http post
    //https://spring.io/guides    referencia pagina oficial
    //metodo post
    @PostMapping("/jedi")
    public String createNewJedi(@Valid @ModelAttribute Personaje personaje, BindingResult result, RedirectAttributes redirectAttributes){
        //public String createNewJedi(@Valid @ModelAttribute Personaje jedi , BindingResult result, RedirectAttributes redirectAttributes){
        //si exiten errores en el result
        if(result.hasErrors()){
            //redirecciona al formulario
            System.out.println("retornamos a la vista por errores");
            return "new_jedi";
        }
        //si no hay errores carga datos al array list
        repoPersonaje.addPersonaje(personaje);

        //mandamos un mensaje de confirmacion que se ha hecho la peticion correcta carga de los datos a la vista
        redirectAttributes.addFlashAttribute("message", "los datos han sido cargados exitomente.");

        //para redireccionar nuevemnte a la pagina
        return "redirect:jedi" ;
    }


    @GetMapping("/jedi/{id}/delete")
    public String deleteJedi(@PathVariable("id") final Long id, RedirectAttributes redirect) {

        //System.out.println("en metodo delete");
        final Personaje personaje = repoPersonaje.findById(id);
        //System.out.println(personaje);

        if(personaje != null){
            repoPersonaje.delete(personaje);

            redirect.addFlashAttribute("message", "Jedi successfully deleted.");

            return "redirect:/jedi";
        }else{
            redirect.addFlashAttribute("message", "Jedi no exist in databse.");

            return "redirect:/jedi";
        }

    }

    /*

    @GetMapping("/jedi/{id}/update")
    public String updatejedi(@PathVariable("id") final Long id, Model model, @Valid @ModelAttribute Personaje personaje) {

        final Personaje personaje = repoPersonaje.findById(id);

        if(personaje != null){

            repoPersonaje.update(personaje.getId(),personaje);

            redirect.addFlashAttribute("message", "Jedi successfully updated.");

            return "redirect:/jedi";
        }else{
            redirect.addFlashAttribute("message", "Jedi no exist in databse.");

            return "redirect:/jedi";
        }

    }
    */

}
