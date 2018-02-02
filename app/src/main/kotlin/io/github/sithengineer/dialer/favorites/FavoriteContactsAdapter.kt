package io.github.sithengineer.dialer.favorites

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import butterknife.BindView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.jakewharton.rxbinding2.view.RxView
import io.github.sithengineer.dialer.R
import io.github.sithengineer.dialer.abstraction.mvp.BaseRecyclerViewHolder
import io.github.sithengineer.dialer.data.model.User
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject

class FavoriteContactsAdapter : RecyclerView.Adapter<FavoriteContactsAdapter.ViewHolder>() {

  private val userFavoritePublisher: PublishSubject<User> = PublishSubject.create()
  private val userEditPublisher: PublishSubject<User> = PublishSubject.create()
  private val userCallPublisher: PublishSubject<User> = PublishSubject.create()
  private val users = mutableListOf<User>()

  override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
    val view = LayoutInflater.from(parent?.context).inflate(ViewHolder.LAYOUT_ID, parent, false)
    return ViewHolder(view, userFavoritePublisher, userEditPublisher, userCallPublisher)
  }

  override fun getItemCount(): Int = users.size

  override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
    holder?.bind(users[position])
  }

  fun setUsers(users: List<User>) {
    this.users.clear()
    this.users.addAll(users)
    notifyDataSetChanged()
  }

  fun removeUser(user: User) {
    if (this.users.remove(user)) {
      notifyDataSetChanged()
    }
  }

  fun userFavoriteSelected(): Observable<User> = userFavoritePublisher
  fun userEditSelected(): Observable<User> = userEditPublisher
  fun userCallSelected(): Observable<User> = userCallPublisher

  class ViewHolder(
      view: View,
      userFavoritePublisher: PublishSubject<User>,
      userEditPublisher: PublishSubject<User>,
      userCallPublisher: PublishSubject<User>
  ) : BaseRecyclerViewHolder(view) {

    companion object {
      const val LAYOUT_ID = R.layout.list_item_square_contact
    }

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

    init {
      RxView.clicks(favorite).subscribe({ _ ->
        isFavorite = isFavorite.not()
        setFavoriteIcon()
        userFavoritePublisher.onNext(user)
      })
      RxView.clicks(edit).subscribe({ _ -> userEditPublisher.onNext(user) })
      RxView.clicks(itemView).subscribe({ _ -> userCallPublisher.onNext(user) })
    }

    private lateinit var user: User
    private var isFavorite = false

    fun bind(user: User) {
      this.user = user
      isFavorite = user.isFavorite

      name.text = user.name
      number.text = user.number
      setFavoriteIcon()

      if (user.thumbnailPath.isEmpty()) {
        image.visibility = View.GONE
      } else {
        val requestOptions = RequestOptions.circleCropTransform()
        Glide.with(itemView).load(user.thumbnailPath).apply(requestOptions).into(image)
      }
    }

    private fun setFavoriteIcon() {
      val drawableId = if (isFavorite) R.drawable.ic_favorite_white_24dp else R.drawable.ic_favorite_border_white_24dp
      favorite.setImageResource(drawableId)
    }
  }
}