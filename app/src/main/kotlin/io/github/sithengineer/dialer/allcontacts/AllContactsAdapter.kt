package io.github.sithengineer.dialer.allcontacts

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import butterknife.BindView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import io.github.sithengineer.dialer.R
import io.github.sithengineer.dialer.abstraction.mvp.BaseRecyclerViewHolder
import io.github.sithengineer.dialer.data.model.User

class AllContactsAdapter : RecyclerView.Adapter<AllContactsAdapter.ViewHolder>() {

  private val users = mutableListOf<User>()

  override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
    val view = LayoutInflater.from(parent?.context).inflate(R.layout.list_item_square_contact,
        parent, false)
    return ViewHolder(view)
  }

  override fun getItemCount(): Int = users.size

  override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
    holder?.setData(users[position])
  }

  fun setData(users: List<User>) {
    this.users.clear()
    this.users.addAll(users)
    notifyDataSetChanged()
  }

  class ViewHolder(view: View) : BaseRecyclerViewHolder(view) {

    @BindView(R.id.list_item_favorite_contact_image)
    lateinit var image: ImageView

    @BindView(R.id.list_item_favorite_contact_name)
    lateinit var name: TextView

    @BindView(R.id.list_item_favorite_contact_number)
    lateinit var number: TextView

    @BindView(R.id.list_item_favorite_contact_edit)
    lateinit var edit: ImageView

    @BindView(R.id.list_item_favorite_contact_favorite)
    lateinit var favorite: ImageView

    fun setData(user: User) {
      name.text = user.name

      if (user.thumbnailPath.isNullOrEmpty()) {
        image.visibility = View.GONE
      } else {
        val requestOptions = RequestOptions.circleCropTransform()
        Glide.with(itemView).load(user.thumbnailPath).apply(requestOptions).into(image)
      }
    }

  }
}
