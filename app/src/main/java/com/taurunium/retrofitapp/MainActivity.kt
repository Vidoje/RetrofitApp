package com.taurunium.retrofitapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.liveData
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val retrofitService = RetrofitInstance
            .getRetrofitInstance()
            .create(AlbumService::class.java)

        val responseLiveData : LiveData<Response<Albums>> =
            liveData{
                val response = retrofitService.getAlbums()
                emit(response)
            }

        responseLiveData.observe(this, Observer {
            val albumsList = it.body()?.listIterator()
            if(albumsList!=null){
                while(albumsList.hasNext()){
                    val albumItem = albumsList.next()
                    Log.d("Vidoje", ""+albumItem.title)
                }
            }
        })
    }
}