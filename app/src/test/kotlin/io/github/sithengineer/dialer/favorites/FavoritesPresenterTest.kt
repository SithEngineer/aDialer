package io.github.sithengineer.dialer.favorites

import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.on
import org.jetbrains.spek.api.dsl.xit

class FavoritesPresenterTest : Spek({
  given("a presenter for listing favorite contacts") {
    on("favorite contacts view created") {
      xit("should have presenter set") {

      }
    }

    on("presenter started") {
      xit("should request all loaded contacts and filter non-favorite contacts") {

      }
    }

    on("contact selected") {
      xit("should make a call to the selected contact") {

      }
    }
  }
})