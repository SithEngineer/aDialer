package io.github.sithengineer.dialer.callhistory

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import butterknife.BindView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.jakewharton.rxbinding2.view.RxView
import io.github.sithengineer.dialer.R
import io.github.sithengineer.dialer.abstraction.ui.BaseRecyclerViewHolder
import io.github.sithengineer.dialer.data.model.Contact
import io.github.sithengineer.dialer.viewmodel.CallHistoryViewModel
import io.reactivex.subjects.PublishSubject
import timber.log.Timber

class CallHistoryViewHolder(
    view: View,
    contactFavoritePublisher: PublishSubject<Contact>,
    contactEditPublisher: PublishSubject<Contact>,
    contactCallPublisher: PublishSubject<Contact>
) : BaseRecyclerViewHolder(view) {

  companion object {
    const val LAYOUT_ID = R.layout.list_item_call_history_contact
  }

  @BindView(R.id.list_item_history_contact_image)
  lateinit var image: ImageView

  @BindView(R.id.list_item_history_contact_name)
  lateinit var name: TextView

  @BindView(R.id.list_item_history_contact_edit)
  lateinit var edit: ImageView

  @BindView(R.id.list_item_history_contact_favorite)
  lateinit var favorite: ImageView

  init {
    // FIXME take care of these subscription leaks
    RxView.clicks(favorite).subscribe(
        {
          isFavorite = isFavorite.not()
          setFavoriteIcon()
          contactFavoritePublisher.onNext(contact)
        },
        { err ->
          Timber.e(err)
        }
    )
    RxView.clicks(edit).subscribe(
        { contactEditPublisher.onNext(contact) },
        { err ->
          Timber.e(err)
        }
    )
    RxView.clicks(itemView).subscribe(
        { contactCallPublisher.onNext(contact) },
        { err ->
          Timber.e(err)
        }
    )
  }

  private lateinit var contact: Contact
  private var isFavorite = false

  fun bind(callHistory: CallHistoryViewModel) {
    this.contact = callHistory.contact
    isFavorite = contact.isFavorite

    name.text = contact.name
    setFavoriteIcon()

    if (contact.thumbnailPath.isEmpty()) {
      image.visibility = View.GONE
    } else {
      val requestOptions = RequestOptions.circleCropTransform()
      Glide.with(itemView).load(contact.thumbnailPath).apply(requestOptions).into(image)
    }
  }

  private fun setFavoriteIcon() {
    val drawableId = if (isFavorite) R.drawable.ic_favorite_white_24dp else R.drawable.ic_favorite_border_white_24dp
    favorite.setImageResource(drawableId)
  }

}