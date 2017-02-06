package com.example.henry.htruong1_sizebook;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Henry on 2017-02-02.
 */
public class getData implements Serializable {
    private String showString;
    private String name;
    private String date;
    private String neck;
    private String bust;
    private String chest;
    private String waist;
    private String hip;
    private String inseam;
    private String comment;
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getNeck() {
        return neck;
    }

    public void setNeck(String neck) {
        this.neck = neck;
    }

    public String getBust() {
        return bust;
    }

    public void setBust(String bust) {
        this.bust = bust;
    }

    public String getChest() {
        return chest;
    }

    public void setChest(String chest) {
        this.chest = chest;
    }

    public String getWaist() {
        return waist;
    }

    public void setWaist(String waist) {
        this.waist = waist;
    }

    public String getHip() {
        return hip;
    }

    public void setHip(String hip) {
        this.hip = hip;
    }

    public String getInseam() {
        return inseam;
    }

    public void setInseam(String inseam) {
        this.inseam = inseam;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString(){
        showString =  "";
        if(this.name!= null){
            showString +="Name: "+this.name;
        }
        if(this.date!=null){
            showString +="\nDate:";
            String date = this.date;
            date.replace("/","-");
            showString += date;

        }
        if(this.neck!=null) {
            showString +="\nNeck: "+this.neck;
        }
        if(this.bust!=null) {
            showString +="\nBust: "+this.bust;
        }
        if(this.chest!=null){
            showString+="\nChest: " +this.chest;
        }
        if(this.waist!=null){
            showString+="\nWaist: " + this.waist;
        }
        if(this.hip != null){
            showString += "\nHip: "  + this.hip;
        }
        if(this.inseam != null){
            showString += "\nInseam: " + this.inseam;
        }
        if(this.comment != null){
            showString += "\nComment: " + this.comment;
        }
        return showString;
    }
}
