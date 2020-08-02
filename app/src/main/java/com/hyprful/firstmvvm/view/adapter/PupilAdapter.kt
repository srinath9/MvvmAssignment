package com.hyprful.firstmvvm.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.hyprful.firstmvvm.R
import com.hyprful.firstmvvm.databinding.PupilItemLayoutBinding
import com.hyprful.firstmvvm.view.fragment.MainFragment
import com.hyprful.firstmvvm.api.model.Pupil
import com.hyprful.firstmvvm.view.callback.PupilClickCallBack

class PupilAdapter(var mainFragment: MainFragment) : RecyclerView.Adapter<PupilAdapter.PupilViewHolder>() {

    lateinit var  pupilClickCallback : PupilClickCallBack
    var pupilsList : List<Pupil> = ArrayList();

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PupilViewHolder {
        val binding: PupilItemLayoutBinding = DataBindingUtil
            .inflate(
                LayoutInflater.from(parent.context), R.layout.pupil_item_layout,
                parent, false
            )

        binding.setCallback(pupilClickCallback)
        return PupilViewHolder(binding);
    }

    override fun getItemCount(): Int {
        if (pupilsList == null)
            return 0;
        return pupilsList.size
    }

    override fun onBindViewHolder(holder: PupilViewHolder, position: Int) {
        holder.binding.setPupil(pupilsList[position])
        holder.binding.executePendingBindings()
    }

    fun setPupilList(pupils: List<Pupil>) {
        if (this.pupilsList == null) {
            this.pupilsList = pupils
            notifyItemRangeInserted(0, pupilsList.size)
        } else {
            val result = DiffUtil.calculateDiff(object : DiffUtil.Callback() {
                override fun getOldListSize(): Int {
                    return pupilsList.size
                }

                override fun getNewListSize(): Int {
                    return pupilsList.size
                }

                override fun areItemsTheSame(
                    oldItemPosition: Int,
                    newItemPosition: Int
                ): Boolean {
                    return pupilsList.get(oldItemPosition).pupilId ===
                            pupilsList.get(newItemPosition).pupilId
                }

                override fun areContentsTheSame(
                    oldItemPosition: Int,
                    newItemPosition: Int
                ): Boolean {
                    val project: Pupil = pupilsList.get(newItemPosition)
                    val old: Pupil = pupilsList.get(oldItemPosition)
                    return project.pupilId === old.pupilId
                }
            })
            this.pupilsList = pupilsList
            result.dispatchUpdatesTo(this)
        }

    }


    inner class PupilViewHolder(var binding: PupilItemLayoutBinding) : ViewHolder(binding.root) {
    }
}