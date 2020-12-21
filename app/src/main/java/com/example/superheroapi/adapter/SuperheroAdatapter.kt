package com.example.superheroapi.adapter

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.superheroapi.R
import com.example.superheroapi.model.charecter.ResultModel
import com.example.superheroapi.views.ProfileActivity
import io.armcha.elasticview.ElasticView


class SuperheroAdatapter: RecyclerView.Adapter<SuperheroAdatapter.ViewHolder>() {

    private var superheroList = emptyList<ResultModel>()
    private lateinit var context: Context
    var intent: Intent? = null


    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        val heroDec: TextView
        val heroName: TextView
        val imageView: ImageView
        val elasticView: ElasticView

        init {
            heroDec = itemView.findViewById<View>(R.id.heroDec) as TextView
            heroName = itemView.findViewById<View>(R.id.heroName) as TextView
            imageView = itemView.findViewById<View>(R.id.imageView) as ImageView
            elasticView = itemView.findViewById<View>(R.id.elasticView) as ElasticView
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SuperheroAdatapter.ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_layout,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: SuperheroAdatapter.ViewHolder, position: Int) {

        holder.heroName.text = superheroList[position].name
        holder.heroDec.text = superheroList[position].name


        holder.elasticView.setOnClickListener(View.OnClickListener {

            intent = Intent(context, ProfileActivity::class.java)
            intent!!.putExtra("hero_id", superheroList[position].id)
            context.startActivity(intent);


        })

        //Loading image from url into imageView
        Glide.with(context)
            .load(superheroList[position].image.url.toString())
            .placeholder(R.drawable.no_image_holder)
            .error(R.drawable.error_image)
            .into(holder.imageView);

    }

    override fun getItemCount(): Int {

        return superheroList.size

    }


    fun setData(newList: List<ResultModel>, context: Context){
        superheroList = newList
        this.context = context
        notifyDataSetChanged()
    }


}