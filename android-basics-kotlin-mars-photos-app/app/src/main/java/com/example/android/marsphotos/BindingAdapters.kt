package com.example.android.marsphotos

import android.view.View
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.android.marsphotos.network.MarsPhoto
import com.example.android.marsphotos.overview.MarsApiStatus
import com.example.android.marsphotos.overview.PhotoGridAdapter

/** (戒め) */
//class BindingAdapters {
//}

@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?) {
    imgUrl?.let {
        val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()
        //Coil ライブラリを使用すると、アプリで画像を管理するプロセス（ダウンロード、バッファリング、デコード、キャッシュ保存など）を簡素化できます
        imgView.load(imgUri) {
            placeholder(R.drawable.loading_animation)
            error(R.drawable.ic_broken_image)
        }
    }
}
@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<MarsPhoto>?) {
    val adapter = recyclerView.adapter as PhotoGridAdapter//cast
    adapter.submitList(data)//これにより、新しいリストが利用可能であることが RecyclerView に通知されます
}

@BindingAdapter("marsApiStatus")
fun bindStatus(statusImageView: ImageView, status: MarsApiStatus?) {
    when(status) {
        MarsApiStatus.LOADING -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.loading_animation)
        }
        MarsApiStatus.ERROR -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.ic_connection_error)
        }
        MarsApiStatus.DONE -> {
            statusImageView.visibility = View.GONE //非表示にします。
        }
    }
}