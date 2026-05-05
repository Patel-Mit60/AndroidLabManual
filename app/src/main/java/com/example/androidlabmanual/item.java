package com.example.androidlabmanual;

public class item {
    String name;
    String Email;
    int image;

    public item(String name, String email, int image) {
        this.name = name;
        this.Email = email;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
