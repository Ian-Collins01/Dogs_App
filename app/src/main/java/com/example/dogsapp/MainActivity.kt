package com.example.dogsapp

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.StringRequestListener
import com.example.dogsapp.Adapter.DogsAdapter
import com.example.dogsapp.Model.DogsApi
import com.example.dogsapp.ui.theme.DogsAppTheme
import com.squareup.picasso.Picasso.Priority
import org.json.JSONObject
import java.io.StringReader
import java.lang.Error


class MainActivity : AppCompatActivity() {

    val imageList = ArrayList<DogsApi>()

    private lateinit var dogsRV:RecyclerView
    private lateinit var dogNameText:EditText
    private lateinit var searchBtn:Button

    /*Se inicializan el RecyclerView, el EditText y el Button, y se establece un ClickListener para el botón de búsqueda.*/
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        dogsRV = findViewById(R.id.dogsRecyclerView)
        dogNameText = findViewById(R.id.dogsNameET)
        searchBtn = findViewById(R.id.searchBtn)

        dogsRV.layoutManager = StaggeredGridLayoutManager(2,LinearLayoutManager.VERTICAL)

        searchBtn.setOnClickListener{

            var name = dogNameText.text.toString()

            searchDogs(name)
        }

    }

    /*Este método se encarga de realizar una solicitud de red a la API de Dog utilizando la biblioteca AndroidNetworking.
     Envía una solicitud GET al punto final de la API especificado según el nombre de perro proporcionado.
      La respuesta se procesa y se extraen las URL de las imágenes de perros del JSON de respuesta. */
    private fun searchDogs(name: String) {

        imageList.clear()
        AndroidNetworking.initialize(this)

        AndroidNetworking.get("https://dog.ceo/api/breed/$name/images")
            .build()
            .getAsString(object : StringRequestListener {
                override fun onResponse(response: String?){

                    val result = JSONObject(response)
                    val image = result.getJSONArray("message")

                    for (i in 0 until image.length()){
                        val list = image.get(i)
                        imageList.add(
                            DogsApi(list.toString())
                        )
                    }
                    dogsRV.adapter = DogsAdapter(this@MainActivity,imageList)
                }

                override fun onError(anError: ANError?){

                }
            })
    }
}
