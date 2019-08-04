package com.example.purplenotes.ui.createnotes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.purplenotes.R
import com.example.purplenotes.ui.customview.SquareImageView

/**
 * Author: Jayden Nguyen
 * Created date: 8/4/2019
 */
class PickImageAdapter: RecyclerView.Adapter<PickImageAdapter.PickImageViewHolder>(){
    private var mPhotoPathList = arrayListOf<String>()
    private var mListener: OnImageClickedListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PickImageViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_square_image_view, parent, false)
        return PickImageViewHolder(view)
    }

    override fun getItemCount(): Int = mPhotoPathList.size

    override fun onBindViewHolder(holder: PickImageViewHolder, position: Int) {
        holder.bind(mPhotoPathList[position])
    }

    fun setPhotoPathList(list: List<String>) {
        mPhotoPathList = list as ArrayList<String>
        notifyDataSetChanged()
    }

    fun setOnImageClickedListener(listener: OnImageClickedListener) {
        mListener = listener
    }

    interface OnImageClickedListener {
        fun onImageClicked(photoPath: String)
    }

    inner class PickImageViewHolder(val view: View): RecyclerView.ViewHolder(view) {
        var imgItem = view.findViewById<SquareImageView>(R.id.imgItem)
        fun bind(photoPath: String) {
            Glide.with(itemView.context).load(photoPath).into(imgItem)

            view.setOnClickListener {
                mListener?.onImageClicked(photoPath)
            }
        }
    }
}