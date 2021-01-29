package com.nasinha.digitalspace.quiz.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.nasinha.digitalspace.R
import com.nasinha.digitalspace.quiz.entity.Score

class ScoreAdapter (
    private val dataSet: List<Score>,
    private val positions: List<String>,
    private val trophies: List<Int>
): RecyclerView.Adapter<ScoreAdapter.ScoreViewHolder>() {

    inner class ScoreViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        private var imgTrophy = itemView.findViewById<ImageView>(R.id.imgTrophy)
        private var txtNumberScore = itemView.findViewById<TextView>(R.id.txtNumberScore)
        private var txtNumberDate = itemView.findViewById<TextView>(R.id.txtNumberDate)
        private var txtNumberPoints = itemView.findViewById<TextView>(R.id.txtNumberPoints)

        fun bind(score: Score) {
            when (score) {
                dataSet[0] -> {
                    imgTrophy.setImageResource(trophies[0])
                    txtNumberScore.text = positions[0]
                }
                dataSet[1] -> {
                    imgTrophy.setImageResource(trophies[1])
                    txtNumberScore.text = positions[1]
                }
                dataSet[2] -> {
                    imgTrophy.setImageResource(trophies[2])
                    txtNumberScore.text = positions[2]
                }
                dataSet[3] -> {
                    txtNumberScore.text = positions[3]
                }
                dataSet[4] -> {
                    txtNumberScore.text = positions[4]
                }
            }
            txtNumberDate.text = score.date
            txtNumberPoints.text = score.points.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScoreViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_score, parent, false)
        return ScoreViewHolder(view)
    }

    override fun getItemCount() = dataSet.size

    override fun onBindViewHolder(holder: ScoreViewHolder, position: Int) {
        holder.bind(dataSet[position])
    }
}