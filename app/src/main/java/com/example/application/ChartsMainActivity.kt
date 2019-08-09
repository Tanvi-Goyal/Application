package com.example.application

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.OnCompleteListener
import kotlinx.android.synthetic.main.activity_charts_main.*
import java.util.ArrayList

class ChartsMainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_charts_main)

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .build()

        // Build a GoogleSignInClient with the options specified by gso.
        var mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        Log.wtf("TAG", "test " + intent.getStringExtra("account_name"))
        val name :String = intent.getStringExtra("account_name")
        tv_name.text = name

        sign_out_button.setOnClickListener{ signOut(mGoogleSignInClient) }

        recycler_view.setHasFixedSize(true)

        val chartList = ArrayList<String>()

        chartList.add(resources.getString((R.string.pie_chart)))
        chartList.add(resources.getString((R.string.column_chart)))
        chartList.add(resources.getString((R.string.line_chart)))
        chartList.add(resources.getString((R.string.bar_chart)))
        chartList.add(resources.getString((R.string.venn_diagram)))

        val adapter = ChartsAdapter(this, chartList)

        recycler_view.setAdapter(adapter)
        recycler_view.setLayoutManager(LinearLayoutManager(this))

        form_btn.setOnClickListener{
            val intent =  Intent(this , FormActivity::class.java)
            startActivity(intent)
        }

        charts_btn.setOnClickListener{
            val intent =  Intent(this , ChartsActivity::class.java)
            startActivity(intent)
        }
    }

    private fun signOut(mGoogleSignInClient: GoogleSignInClient?) {
        if (mGoogleSignInClient != null) {
            mGoogleSignInClient
                .signOut()
                .addOnCompleteListener(this, OnCompleteListener<Void> {
                    // ...

                    val intent =  Intent(this , MainActivity::class.java)
                    startActivity(intent)
                })
        }
    }

}
