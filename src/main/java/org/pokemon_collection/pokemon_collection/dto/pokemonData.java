package org.pokemon_collection.pokemon_collection.dto;

import java.io.IOException;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.web.multipart.MultipartFile;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Transient;

@Entity
public class pokemonData {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int Id;
    private String name;
    private String type;
    private String description;
    private String strength;
    private String weakness;

    @ManyToOne
    private myUser user;

    @Transient
    private MultipartFile image;
    @Lob
    private byte[] imageData;

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStrength() {
        return strength;
    }

    public void setStrength(String strength) {
        this.strength = strength;
    }

    public String getWeakness() {
        return weakness;
    }

    public void setWeakness(String weakness) {
        this.weakness = weakness;
    }

    public myUser getUser() {
        return user;
    }

    public void setUser(myUser user) {
        this.user = user;
    }

    public MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }

    public String getImageData() {
        return Base64.encodeBase64String(imageData);
    }

    public void setImageData(MultipartFile image) {
        try {
            imageData = new byte[image.getInputStream().available()];
            image.getInputStream().read(imageData);
        } catch (IOException e) {
        }
    }

    

}
