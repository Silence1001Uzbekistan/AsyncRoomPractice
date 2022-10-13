package com.example.asyncroompractice

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.asyncroompractice.Adapter.UserAdapter
import com.example.asyncroompractice.Db.AppDatabase
import com.example.asyncroompractice.Entity.User
import com.example.asyncroompractice.databinding.ActivityMainBinding
import java.sql.Time
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    lateinit var appDatabase: AppDatabase

    private val TAG = "MainActivity"

    private lateinit var userAdapter: UserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        appDatabase = AppDatabase.getInstance(this)

        binding.btn.setOnClickListener {

            val user = User()

            user.username = binding.editOne.text.toString()
            user.password = binding.editTwo.text.toString()

            MyAsyncTask().execute(user)

        }

        RvTask().execute()


    }

    inner class RvTask : AsyncTask<Void, Void, List<User>>() {

        override fun onPreExecute() {
            super.onPreExecute()

            binding.progressBar.visibility = View.VISIBLE

        }

        override fun doInBackground(vararg p0: Void?): List<User> {

            try {

                TimeUnit.SECONDS.sleep(2)

            } catch (e: Exception) {
                e.printStackTrace()
            }

            return appDatabase.userDao().getAllUsers()

        }

        override fun onPostExecute(result: List<User>?) {
            super.onPostExecute(result)

            userAdapter = UserAdapter(result!!)

            binding.rv.adapter = userAdapter

            binding.progressBar.visibility = View.GONE

        }

    }

    inner class MyAsyncTask : AsyncTask<User, Void, Void>() {

        override fun onPreExecute() {
            super.onPreExecute()

            Log.d(TAG, "onPreExecute: ")

        }

        override fun doInBackground(vararg p0: User): Void? {

            appDatabase.userDao().addUser(p0[0])

            Log.d(TAG, "doInBackground: ")

            return null

        }

        override fun onPostExecute(result: Void?) {
            super.onPostExecute(result)

            Log.d(TAG, "onPostExecute: ")

            Toast.makeText(this@MainActivity, "Success", Toast.LENGTH_SHORT).show()

        }


    }


}