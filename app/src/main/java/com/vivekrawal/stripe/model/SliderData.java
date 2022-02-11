package com.vivekrawal.stripe.model;

public class SliderData {

    // image url is used to
    // store the url of image
    private String imgUrl,date,mname;

    // Constructor method.
    public SliderData(String imgUrl,String date,String mname) {
        this.imgUrl = imgUrl;
        this.date = date;
        this.mname = mname;
    }



    // Getter method
    public String getImgUrl() {
        return imgUrl;
    }

    // Setter method
    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }





    public String getdate() {
        return date;
    }

    // Setter method
    public void setdate(String date) {
        this.date = date;
    }


    public String getmname() {
        return mname;
    }

    // Setter method
    public void setmname(String mname) {
        this.mname = mname;
    }
}

