package com.example.proyprueba2.fragment
import ExpandableFaqsListAdapter
import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import com.example.proyprueba2.Producto
import com.example.proyprueba2.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class ComparaFragment : Fragment() {
    lateinit var faqsView: View
    private lateinit var mibase: FirebaseDatabase
    private lateinit var miReferencia: DatabaseReference
    lateinit var listaProductos: ArrayList<String>
    lateinit var adaptador: ArrayAdapter<String>
    lateinit var spinnerProdarr: Spinner
    lateinit var spinnerPrueba: Spinner
    lateinit var listaArroz: ArrayList<String>
    lateinit var listaLeche: ArrayList<String>

    
    @SuppressLint("UseRequireInsteadOfGet")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        faqsView = inflater.inflate(R.layout.fragment_compara, container, false)
        //faqsListView = faqsView?.findViewById(R.id.rvFaqs) as ExpandableListView
        spinnerProdarr= faqsView.findViewById(R.id.spinnerPrueba)
        spinnerPrueba= faqsView.findViewById(R.id.spinnerLeche)
        mibase = FirebaseDatabase.getInstance()
        miReferencia = Firebase.database.reference
        listaLeche=ArrayList()
        listaArroz=ArrayList()

        miReferencia.child("Arroz").addValueEventListener(object: ValueEventListener{
            override fun onDataChange(dataSnapshot: DataSnapshot){
                listaArroz.clear()
                for(snapshot in dataSnapshot.children){
                    val unProducto=snapshot.getValue(Producto::class.java)
                    val nombre= unProducto?.tipo + " " + unProducto?.marca
                    listaArroz.add(nombre)
                    Log.e("Producto Final Arroz: ", ""+nombre)
                    val arrayAdapters = ArrayAdapter(requireContext(),R.layout.support_simple_spinner_dropdown_item,listaArroz)
                    spinnerProdarr.adapter = arrayAdapters
                    arrayAdapters.notifyDataSetChanged()
                }
            }
            override fun onCancelled(databaseError: DatabaseError) {
            }
        })

        miReferencia.child("Leche").addValueEventListener(object: ValueEventListener{
            override fun onDataChange(dataSnapshot: DataSnapshot){
                listaLeche.clear()
                for(snapshot in dataSnapshot.children){
                    val unProducto=snapshot.getValue(Producto::class.java)
                    val nombre= unProducto?.nombre
                    val marca=unProducto?.marca
                    val peso=unProducto?.peso
                    if (nombre != null) {
                        listaLeche.add(nombre)
                    }
                    val arrayAdapter = ArrayAdapter(requireContext(),R.layout.support_simple_spinner_dropdown_item,listaLeche)
                    spinnerPrueba.adapter = arrayAdapter
                    arrayAdapter.notifyDataSetChanged()
                    Log.e("Producto Final: ", ""+nombre)
                }

            }
            override fun onCancelled(databaseError: DatabaseError) {
                // Getting Post failed, log a message
                //Log.w(TAG, "loadPost:onCancelled", databaseError.toException())
                // ...
            }
        })

        /*
        faqsListView.setOnGroupClickListener { expandableListView, view, i, l ->
            if (expandableListView.isGroupExpanded(i)) {
                //var imgView = view.findViewById<ImageView>(R.id.imgIonNextFaq)
               // imgView.setImageDrawable(ContextCompat.getDrawable(this@ComparaFragment.context!!, R.drawable.supermercadosperuanos))
            } else {
                //var imgView = view.findViewById<ImageView>(R.id.imgIonNextFaq)
                //imgView.setImageDrawable(ContextCompat.getDrawable(this@ComparaFragment.context!!, R.drawable.jack_foto))
            }
            return@setOnGroupClickListener false
        }
         */
       return faqsView
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
}

