package com.example.tp1article.bo;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Classe représentant un article
 */
@Entity
public class Article implements Parcelable {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;
    private Float price;
    private Float rating;
    private String description;
    private Boolean isBought;
    private String link;

    public Article(int id, String name, Float price, String description, Float rating, Boolean isBought, String link) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.rating = rating;
        this.isBought = isBought;
        this.link = link;
    }
    public Article(String name, Float price, String description, Float rating, Boolean isBought, String link) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.rating = rating;
        this.isBought = isBought;
        this.link = link;
    }

    public Article() {
    }

    protected Article(Parcel in) {
        id = in.readInt();
        name = in.readString();
        if (in.readByte() == 0) {
            price = null;
        } else {
            price = in.readFloat();
        }
        if (in.readByte() == 0) {
            rating = null;
        } else {
            rating = in.readFloat();
        }
        description = in.readString();
        byte tmpIsBought = in.readByte();
        isBought = tmpIsBought == 0 ? null : tmpIsBought == 1;
        link = in.readString();
    }

    public static final Creator<Article> CREATOR = new Creator<Article>() {
        @Override
        public Article createFromParcel(Parcel in) {
            return new Article(in);
        }

        @Override
        public Article[] newArray(int size) {
            return new Article[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getRating() {
        return rating;
    }

    public void setRating(Float rating) {
        this.rating = rating;
    }

    public Boolean getIsBought() {
        return isBought;
    }

    public void setIsBought(Boolean isBought) {
        this.isBought = isBought;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", rating=" + rating +
                ", description='" + description + '\'' +
                ", isBought=" + isBought +
                ", link='" + link + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(name);
        if (price == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeFloat(price);
        }
        if (rating == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeFloat(rating);
        }
        parcel.writeString(description);
        parcel.writeByte((byte) (isBought == null ? 0 : isBought ? 1 : 2));
        parcel.writeString(link);
    }
}
