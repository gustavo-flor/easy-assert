(ns easy-assert.asserts
  (:require [clojure.test :refer [is]]))

(defn default-assert
  "Default assertion function.

  Parameters:
    `result`  - The result of the assertion (true or false).
    `message` - The message to display if the assertion fails.

  Returns:
    Calls the `is` function with the result and message."
  [result message]
  (is result (str message "\n")))
