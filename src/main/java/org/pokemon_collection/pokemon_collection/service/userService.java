package org.pokemon_collection.pokemon_collection.service;

import java.util.List;
import java.util.Optional;

import org.pokemon_collection.pokemon_collection.dto.myUser;
import org.pokemon_collection.pokemon_collection.dto.pokemonData;
import org.pokemon_collection.pokemon_collection.repository.pokemonRepository;
import org.pokemon_collection.pokemon_collection.repository.userRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Service
public class userService {

    @Autowired
    userRepository repository;

    @Autowired
    pokemonRepository pokemonRepository;

    public void removeMessage() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        request.getSession().removeAttribute("message");
    }

    public String register(myUser user, HttpSession session) {
        if (user.getPassword().equals(user.getConfirmPassword())) {
            if (repository.existsById(user.getUsername())) {
                session.setAttribute("message", "Username Already Exists");
                return "redirect:/register";
            } else {
                repository.save(user);
                session.setAttribute("message", "Account Created Success");
                return "redirect:/";
            }
        }
         else {
            session.setAttribute("message", "Password and Confirm Password Missmatch");
            return "redirect:/register";
        }
    }

    public String login(String username, String password, HttpSession session) {
        Optional<myUser> user = repository.findById(username);
        if (user.isEmpty()) {
            session.setAttribute("message", "Invalid Username");
            return "redirect:/";
        } else {
            if (password.equals(user.get().getPassword())) {
                session.setAttribute("message", "Login Success");
                session.setAttribute("user", user.get());
                return "redirect:/home";
            } else {
                session.setAttribute("message", "Invalid Password");
                return "redirect:/";
            }
        }
    }

    public String LogOut(HttpSession session) {
        session.removeAttribute("user");
        session.setAttribute("message", "Logout Success");
        return "redirect:/";
    }

    public String add(pokemonData pokemon, HttpSession session) {
        myUser user = (myUser) session.getAttribute("user");
        if (user == null) {
            session.setAttribute("message", "Invalid Session, Login Again");
            return "redirect:/";
        } else {
            pokemon.setImageData(pokemon.getImage());
            pokemon.setUser(user);
            pokemonRepository.save(pokemon);
            session.setAttribute("message", "Product Added Success");
            return "redirect:/home";
        }
    }

    public String loadHome(HttpSession session, ModelMap map) {
        myUser user = (myUser) session.getAttribute("user");
        if (user == null) {
            session.setAttribute("message", "Invalid Session, Login Again");
            return "redirect:/";
        } else {
            List<pokemonData> pokemons=pokemonRepository.findByUser(user);
            if(!pokemons.isEmpty())
                map.put("pokemons", pokemons);
                else{
                    session.setAttribute("message", "No Products Available");
                }
            return "home.html";
        }
    }

    public String DeleteProduct(HttpSession session, int id) {
        myUser user = (myUser) session.getAttribute("user");
        if (user == null) {
            session.setAttribute("message", "Invalid Session, Login Again");
            return "redirect:/";
        } else {
            pokemonRepository.deleteById(id);
            session.setAttribute("message", "Product Deleted Success");
            return "redirect:/home";
        }
            
    }

    public String loadEdit(HttpSession session, int id, ModelMap map) {
        myUser user = (myUser) session.getAttribute("user");
        if (user == null) {
            session.setAttribute("message", "Invalid Session, Login Again");
            return "redirect:/";
        } else {
            pokemonData pokemon = pokemonRepository.findById(id).get();
            map.put("pokemons", pokemon);
            session.setAttribute("message", "Data Retrieved Success");
            return "edit.html";
        }
    }

    public String EditData(int id, HttpSession session, pokemonData pokemon) {
        myUser user = (myUser) session.getAttribute("user");
        if (user == null) {
            session.setAttribute("message", "Invalid Session, Login Again");
            return "redirect:/";
        } else {
            pokemon.setImageData(pokemon.getImage());
            pokemonRepository.findById(id);
            pokemon.setUser(user);
            pokemonRepository.save(pokemon);
            session.setAttribute("message", "Product Added Success");
            return "redirect:/home";
        }
    }


    
}
