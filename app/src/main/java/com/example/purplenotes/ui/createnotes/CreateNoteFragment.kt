package com.example.purplenotes.ui.createnotes

import android.graphics.drawable.Drawable
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.get
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.purplenotes.R
import com.example.purplenotes.ViewModelFactory
import com.example.purplenotes.base.BaseActivity
import com.example.purplenotes.base.BaseFragment
import com.example.purplenotes.ui.MainActivity
import kotlinx.android.synthetic.main.fragment_create_note.*
import kotlinx.android.synthetic.main.snippet_create_note_options.*
import kotlinx.android.synthetic.main.snippet_pick_image.*
import javax.inject.Inject

/**
 * Author: Jayden Nguyen
 * Created date: 7/31/2019
 */
class CreateNoteFragment : BaseFragment(), View.OnClickListener, PickImageAdapter.OnImageClickedListener {

    private var viewModel: CreateNoteViewModel? = null

    private var mImageAdapter: PickImageAdapter = PickImageAdapter()

    @Inject
    lateinit var factory: ViewModelFactory

    companion object {
        @JvmStatic
        fun newInstance() = CreateNoteFragment()
    }

    override fun initView() {
        tvSave.setOnClickListener(this)
        layoutPickImage.setOnClickListener(this)

        viewModel?.apply {
            mStatus?.observe(this@CreateNoteFragment, Observer {
                if (it) {
                    Toast.makeText(context, "Add Note Success", Toast.LENGTH_LONG).show()
                    (activity as MainActivity).pushHomeScreen()
                } else {
                    Toast.makeText(context, "Add Note Failed", Toast.LENGTH_LONG).show()
                }
            })

            listPhotoPath?.observe(this@CreateNoteFragment, Observer {
                if (!it.isNullOrEmpty()) {
                    rcvImages.visibility = View.VISIBLE
                    layoutCreateNoteOptions.visibility = View.GONE
                    mImageAdapter.setPhotoPathList(it)
                    setupRecyclerView()
                }
            })
        }
    }

    override fun initData() {

    }

    override fun injectInjector() {
        (activity as BaseActivity).getInjector()?.inject(this)
    }

    override fun getViewRes(): Int = R.layout.fragment_create_note

    override fun getViewModel() {
        viewModel = ViewModelProviders.of(this, factory).get(CreateNoteViewModel::class.java)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.tvSave -> {
                viewModel?.addNote(edtTitle.text.toString(), edtContent.text.toString())
            }

            R.id.layoutPickImage -> {
                viewModel?.getImageFromGallery()
            }
        }
    }

    override fun onImageClicked(photoPath: String) {
        var image = Drawable.createFromPath(photoPath)
        var imageHeight = image.intrinsicHeight
        var imageWidth = image.intrinsicWidth
        image.setBounds(0,0,imageWidth, imageHeight)
        edtContent.setCompoundDrawables(null, null, null, image)
    }

    fun setupRecyclerView() {
        rcvImages.apply {
            layoutManager = GridLayoutManager(context.applicationContext, 3) as RecyclerView.LayoutManager?
            mImageAdapter.setOnImageClickedListener(this@CreateNoteFragment)
            adapter = mImageAdapter
        }
    }

}