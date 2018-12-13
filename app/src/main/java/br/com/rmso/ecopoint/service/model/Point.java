package br.com.rmso.ecopoint.service.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

@Entity(tableName = "point")
public class Point implements Parcelable{
    @PrimaryKey(autoGenerate = true)
    private int id;
    @Ignore
    @SerializedName("records")
    private List<Point> pointList;
    @SerializedName("tiporesiduo")
    private String typeWaste;
    @SerializedName("bairro")
    private String neighborhood;
    @SerializedName("observacao")
    private String note;
    @SerializedName("longitude")
    private String longitude;
    @SerializedName("latitude")
    private String latitude;
    @SerializedName("endereco")
    private String address;
    @SerializedName("complemento")
    private String complement;

    @Ignore
    public Point(){

    }

    public Point (String typeWaste, String neighborhood, String note, String longitude, String latitude, String address, String complement){
        this.typeWaste = typeWaste;
        this.neighborhood = neighborhood;
        this.note = note;
        this.longitude = longitude;
        this.latitude = latitude;
        this.address = address;
        this.complement = complement;
    }

    private Point (Parcel p) {
        typeWaste = p.readString();
        neighborhood = p.readString();
        note = p.readString();
        longitude = p.readString();
        latitude = p.readString();
        address = p.readString();
        complement = p.readString();
    }

    public static final Parcelable.Creator<Point> CREATOR = new Parcelable.Creator<Point>(){
        @Override
        public Point createFromParcel (Parcel in){
            return new Point(in);
        }

        @Override
        public Point[] newArray (int size) {
            return new Point[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Point> getPointList() {
        return pointList;
    }

    public void setPointList(List<Point> pointList) {
        this.pointList = pointList;
    }

    public String getTypeWaste() {
        return typeWaste;
    }

    public void setTypeWaste(String typeWaste) {
        this.typeWaste = typeWaste;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getComplement() {
        return complement;
    }

    public void setComplement(String complement) {
        this.complement = complement;
    }

    @Override
    public int describeContents() {
        return hashCode();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(typeWaste);
        dest.writeString(neighborhood);
        dest.writeString(note);
        dest.writeString(longitude);
        dest.writeString(latitude);
        dest.writeString(address);
        dest.writeString(complement);
    }
}
