package com.jee00.IIITG.Model;

public class Faculty {
    String name,email,sub,pic;

    public Faculty(){

    }

    public Faculty(String name, String email, String sub, String pic) {
        this.name = name;
        this.email = email;
        this.sub = sub;
        this.pic = pic;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSub() {
        return sub;
    }

    public void setSub(String sub) {
        this.sub = sub;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }
}
