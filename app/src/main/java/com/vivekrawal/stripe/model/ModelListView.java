package com.vivekrawal.stripe.model;

public class ModelListView {

    private String original_title, original_language, release_date, backdrop_path,vote_average,overview,id;

    public String getid(){
        return id;
    }

    public void setid(String overview){
        this.id = id;
    }




    public String getoverview(){
        return overview;
    }

    public void setoverview(String overview){
        this.overview = overview;
    }

    public String getvote_average(){
        return vote_average;
    }

    public void setvote_average(String vote_average){
        this.vote_average = vote_average;
    }

    public String getbackdrop_path(){
        return backdrop_path;
    }

    public void setbackdrop_path(String backdrop_path){
        this.backdrop_path = backdrop_path;
    }

    public String getoriginal_title() {
        return original_title;
    }

    public void setoriginal_title(String original_title) {
        this.original_title = original_title;
    }

    public String getoriginal_language() {
        return original_language;
    }

    public void setoriginal_language(String original_language) {
        this.original_language = original_language;
    }

    public String getrelease_date() {
        return release_date;
    }

    public void setrelease_date(String release_date) {
        this.release_date = release_date;
    }
}
