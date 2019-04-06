package io.github.sithengineer.dialer.favorites

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import io.github.sithengineer.dialer.data.model.Contact
import io.github.sithengineer.dialer.data.model.ContactNumber
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject

class FavoriteContactsAdapter : RecyclerView.Adapter<FavoriteContactsViewHolder>() {

  private val contactFavoritePublisher: PublishSubject<Contact> = PublishSubject.create()
  private val contactEditPublisher: PublishSubject<Contact> = PublishSubject.create()
  private val contactCallPublisher: PublishSubject<Contact> = PublishSubject.create()

  private val users = mutableListOf<Contact>()
  private val contactNumbers = mutableMapOf<Contact, List<ContactNumber>>()

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteContactsViewHolder {
    val view = LayoutInflater.from(parent.context).inflate(FavoriteContactsViewHolder.LAYOUT_ID,
        parent, false)
    return FavoriteContactsViewHolder(view, contactFavoritePublisher, contactEditPublisher,
        contactCallPublisher)
  }

  override fun getItemCount(): Int = users.size

  override fun onBindViewHolder(holder: FavoriteContactsViewHolder, position: Int) {
    val user = users[position]
    holder.bind(user, contactNumbers.getOrElse(user) {emptyList()})
  }

  fun setUsers(contacts: List<Contact>, contactNumbers: Map<Contact, List<ContactNumber>>) {
    this.users.clear()
    this.users.addAll(contacts)

    this.contactNumbers.clear()
    this.contactNumbers.putAll(contactNumbers)

    notifyDataSetChanged()
  }

  fun removeUser(contact: Contact) {
    if (this.users.remove(contact)) {
      notifyDataSetChanged()
    }
  }

  fun userFavoriteSelected(): Observable<Contact> = contactFavoritePublisher
  fun userEditSelected(): Observable<Contact> = contactEditPublisher
  fun userCallSelected(): Observable<Contact> = contactCallPublisher
}