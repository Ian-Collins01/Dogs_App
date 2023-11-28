package com.example.dogsapp.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.dogsapp.Model.DogsApi
import com.example.dogsapp.R
import com.squareup.picasso.Picasso

/*
  Adaptador personalizado para el RecyclerView que muestra imágenes de perros.

  context El contexto de la aplicación.
  dogsImages La lista de objetos DogsApi que contiene las URL de las imágenes de perros.
 */
class DogsAdapter (private val dogsImages: ArrayList<DogsApi>):RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    /*
     Crea y devuelve un ViewHolder que infla el diseño del elemento de la vista del RecyclerView.
     parent El grupo al que se añadirá la nueva vista.
     viewType El tipo de vista del nuevo elemento.
     Un nuevo ViewHolder que contiene la vista del elemento.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val v = LayoutInflater.from(parent.context).inflate(R.layout.dogs_rv_layout, parent, false)

        return ViewHolder(v)

    }

    /*
    Devuelve el número total de elementos en el conjunto de datos que posee el adaptador.
    return El número total de elementos en el conjunto de datos.
    */
    override fun getItemCount(): Int {

        return dogsImages.size

    }

    /*
    Llena la vista del elemento en la posición dada con los datos del objeto DogsApi correspondiente.
    holder El ViewHolder que debe ser actualizado para representar el contenido del elemento en la posición dada.
    position La posición del elemento en el conjunto de datos.
    */
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        Picasso.get().load(dogsImages[position].message).into((holder as ViewHolder).dogImage)
    }


    /*
    Clase interna que representa el ViewHolder para los elementos de la vista del RecyclerView.
    itemView La vista del elemento del RecyclerView.
    */
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        init {
            itemView.setOnClickListener(this)
        }

        /*
        Implementación del método onClick para manejar los clics en los elementos del RecyclerView.
        p0 La vista que se ha hecho clic.
        */
        val dogImage: ImageView = itemView.findViewById(R.id.dogImage)
        override fun onClick(p0: View?) {
            TODO("Not yet implemented")
        }
    }

}