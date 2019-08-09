package com.example.application

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.SignInButton
import kotlinx.android.synthetic.main.activity_main.*
import android.content.Intent
import android.util.Log
import android.widget.Toast
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.tasks.Task
import com.google.android.gms.common.api.ApiException
import com.google.android.material.snackbar.Snackbar
import androidx.annotation.NonNull
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.tasks.OnCompleteListener

const val RC_SIGN_IN = 123
const val provider = "embibe.com"
var isEmbibeAccount = true
lateinit var mGoogleSignInClient : GoogleSignInClient

class MainActivity : AppCompatActivity() {
    data class FieldM(val id: Int, val title: String , val hint: String , val type: String)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        // Configure sign-in to request the user's ID, email address, and basic
        // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .build()

        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        sign_in_button.setOnClickListener{
            val signInIntent = mGoogleSignInClient.signInIntent
            startActivityForResult(signInIntent, RC_SIGN_IN)
        }

        if (isEmbibeAccount){
            val acct = GoogleSignIn.getLastSignedInAccount(this)
            if (acct != null) {

                val intent =  Intent(this , ChartsMainActivity::class.java)
                intent.putExtra("account_name" , acct.displayName)
                Log.e("TAG" , acct.displayName)
                startActivity(intent)
            }
        }

    }


    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);

        if (requestCode == RC_SIGN_IN ) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.

            val task = GoogleSignIn.getSignedInAccountFromIntent(data)

//            handleSignInResult(task)
            var account = task.getResult(ApiException::class.java)

            if (account != null) {
                if (account.email!!.substring(account.email!!.indexOf("@") + 1).equals(provider)) {
                    handleSignInResult(task)
                } else {

                    signOut()
                    isEmbibeAccount = false
                }
            }
        }
    }

    private fun handleSignInResult(completedTask: Task<GoogleSignInAccount>) {

        try {
            val account = completedTask.getResult(ApiException::class.java)

            val intent =  Intent(this , ChartsMainActivity::class.java)
            if (account != null) {
                intent.putExtra("account_name" , account.displayName)
            }

            startActivity(intent)

        } catch (e: ApiException) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information
        }


    }



    private fun signOut() {
        if (mGoogleSignInClient != null) {
            mGoogleSignInClient
                .signOut()
                .addOnCompleteListener(this, OnCompleteListener<Void> {
                    // ...

                    val snack = Snackbar.make(
                        root_layout,
                        "Sorry, this Email Id is not registered with Embibe.",
                        Snackbar.LENGTH_LONG
                    )
                    snack.show()
                })
        }
    }



}

