package io.github.sithengineer.dialer.callhistory

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import io.github.sithengineer.dialer.data.model.Contact
import io.github.sithengineer.dialer.viewmodel.CallHistoryViewModel
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject

class CallHistoryAdapter : RecyclerView.Adapter<CallHistoryViewHolder>() {

  private val contactFavoritePublisher: PublishSubject<Contact> = PublishSubject.create()
  private val contactEditPublisher: PublishSubject<Contact> = PublishSubject.create()
  private val contactCallPublisher: PublishSubject<Contact> = PublishSubject.create()
  private val callHistories = mutableListOf<CallHistoryViewModel>()

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CallHistoryViewHolder {
    val view = LayoutInflater.from(parent.context).inflate(CallHistoryViewHolder.LAYOUT_ID, parent,
        false)
    return CallHistoryViewHolder(view, contactFavoritePublisher, contactEditPublisher,
        contactCallPublisher)
  }

  override fun getItemCount(): Int = callHistories.size

  override fun onBindViewHolder(holder: CallHistoryViewHolder, position: Int) {
    holder.bind(callHistories[position])
  }

  fun addCallHistory(callHistory: CallHistoryViewModel) {
    callHistories.add(callHistory)
    //notifyItemInserted(callHistories.size)
    notifyDataSetChanged()
  }

  fun setCallHistory(callHistories: List<CallHistoryViewModel>) {
    this.callHistories.clear()
    this.callHistories.addAll(callHistories)
    notifyDataSetChanged()
  }

  fun userFavoriteSelected(): Observable<Contact> = contactFavoritePublisher
  fun userEditSelected(): Observable<Contact> = contactEditPublisher
  fun userCallSelected(): Observable<Contact> = contactCallPublisher

}