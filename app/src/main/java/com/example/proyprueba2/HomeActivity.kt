package com.example.proyprueba2

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.proyprueba2.fragment.ComparaFragment
import com.example.proyprueba2.fragment.InicioFragment
import com.example.proyprueba2.fragment.MiPerfilFragment
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {

    private val inicioFragment = InicioFragment()
    private val comparaFragment = ComparaFragment()
    private val miPerfilFragment = MiPerfilFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        //--------------- FRAGMENTS ---------------
        replaceFragment(inicioFragment)

        bottom_navigation.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.ic_inicio -> replaceFragment(inicioFragment)
                R.id.ic_compara -> replaceFragment(comparaFragment)
                R.id.ic_miperfil -> replaceFragment(miPerfilFragment)
            }
            true
        }



    }

    private fun replaceFragment(fragment: Fragment){
        if(fragment != null){
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_container, fragment)
            transaction.commit()
        }
    }

}