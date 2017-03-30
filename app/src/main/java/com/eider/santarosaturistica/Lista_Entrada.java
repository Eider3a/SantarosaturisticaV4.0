package com.eider.santarosaturistica;

/**
 * Created by Eider Arango on 14/03/2017.
 */

public class Lista_Entrada {
    private int idimagen;
//    private String nombre,descripcion,direccion;

    //Declaracion de nuevos atributos.
    private String nombre,descripcion,precio;

    public Lista_Entrada(int idimagen, String nombre, String descripcion, String precio){
        this.idimagen = idimagen;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
    }

    public int getIdimagen() {
        return idimagen;
    }

    public void setIdimagen(int idimagen) {
        this.idimagen = idimagen;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }
    //    public Lista_Entrada(int idimagen, String nombre, String descripcion, String direccion) {
//        this.idimagen = idimagen;
//        this.nombre = nombre;
//        this.descripcion = descripcion;
//        this.direccion = direccion;
//    }

    //Click derecho->generar ->setter and getter.
//    public int getIdimagen() {
//        return idimagen;
//    }
//
//    public void setIdimagen(int idimagen) {
//        this.idimagen = idimagen;
//    }
//
//    public String getNombre() {
//        return nombre;
//    }
//
//    public void setNombre(String nombre) {
//        this.nombre = nombre;
//    }
//
//    public String getDescripcion() {
//        return descripcion;
//    }
//
//    public void setDescripcion(String descripcion) {
//        this.descripcion = descripcion;
//    }
//
//    public String getDireccion() {
//        return direccion;
//    }
//
//    public void setDireccion(String direccion) {
//        this.direccion = direccion;
//    }


}
