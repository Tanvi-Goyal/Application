package com.example.a

import com.example.application.ChartsActivity
import com.example.application.R

import android.content.Context
import android.graphics.Color
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import de.hdodenhof.circleimageview.CircleImageView
import com.amulyakhare.textdrawable.TextDrawable
import com.bumptech.glide.Glide
import com.amulyakhare.textdrawable.util.ColorGenerator
import com.bumptech.glide.request.RequestOptions


class ListAdapter(private val list: List<ChartsActivity.Team>) : RecyclerView.Adapter<TeamViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return TeamViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: TeamViewHolder, position: Int) {
        val team: ChartsActivity.Team = list[position]
        holder.bind(team, holder.itemView.context)

    }

    override fun getItemCount(): Int = list.size

}

class TeamViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.recycler_view_item, parent, false)) {
    private var mName: TextView? = null
    private var mSkills: TextView? = null
    private var mImage: CircleImageView? = null
    private var mLetterImage: ImageView


    init {
        mName = itemView.findViewById(R.id.name_text)
        mSkills = itemView.findViewById(R.id.skills_text)
        mImage = itemView.findViewById(R.id.circle_image)
        mLetterImage = itemView.findViewById(R.id.profile_image)
    }

    fun bind(team: ChartsActivity.Team, context: Context) {
        mName?.text = team.name
        mSkills?.text = team.skills

        mImage?.let {
            Glide.with(context)
                .load(team.image)
                .into(it)
        }

        mLetterImage.visibility = View.GONE
        mImage?.visibility = View.VISIBLE

//        mImage?.let { Glide.with(context).load(team.image).into(it) };

        if (team.image == "") {
            val generator = ColorGenerator.MATERIAL // or use DEFAULT
            val color = generator.randomColor

            var letters: String
            if(team.name.contains(" "))  letters = team.name.substring(0, 1) + team.name.substring(team.name.indexOf(" ") + 1, team.name.indexOf(" ") + 2)
            else letters = team.name.substring(0, 1)

            val drawable = TextDrawable.builder()
                .beginConfig()
                .withBorder(4) /* thickness in px */
                .endConfig()
                .buildRound(letters , color)

            mLetterImage?.setImageDrawable(drawable)
            mLetterImage.visibility = View.VISIBLE
            mImage?.visibility = View.GONE
        }


    }

}