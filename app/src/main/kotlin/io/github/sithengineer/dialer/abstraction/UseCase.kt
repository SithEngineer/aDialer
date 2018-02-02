package io.github.sithengineer.dialer.abstraction

interface UseCase<in Q : UseCase.RequestValues, out R : UseCase.ResponseValue> {

  fun execute(request: Q): R

  /**
   * Data passed to a request.
   */
  interface RequestValues

  /**
   * Data received from a request.
   */
  interface ResponseValue
}