package io.github.sithengineer.dialer.abstraction

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.support.v4.content.LocalBroadcastManager
import io.reactivex.Observable

class RxLocalBroadcastReceiver {
  companion object {
    fun generateObservable(context: Context, intentFilter: IntentFilter): Observable<Intent> {
      return Observable.create { emitter ->
        val broadcastReceiver = object : BroadcastReceiver() {
          override fun onReceive(context: Context?, intent: Intent) {
            emitter.onNext(intent)
          }
        }

        LocalBroadcastManager.getInstance(context).registerReceiver(broadcastReceiver,
            intentFilter)

        emitter.setCancellable {
          LocalBroadcastManager.getInstance(context).unregisterReceiver(broadcastReceiver)
        }
      }
    }
  }
}