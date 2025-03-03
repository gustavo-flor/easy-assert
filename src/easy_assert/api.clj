(ns easy-assert.api
  (:require [easy-assert.asserts :refer [default-assert]]
            [easy-assert.generator :refer [generate-helpers]]
            [easy-assert.matchers :refer [default-matchers]]))

(def ^:private helpers (generate-helpers default-assert default-matchers))

(def assert-that
  "Asserts that a value matches the expected criteria.

  Parameters:
    `actual`     - The value to be checked.
    `assertions` - A sequence of assertions (pairs of matcher keyword and expected value) to be applied to the value.

  Usage Examples:
    (assert-that 5 :is-equal-to 5) ; Asserts that 5 is equal to 5
    (assert-that \"hello\" :starts-with \"he\") ; Asserts that \"hello\" starts with \"he\"
    (assert-that [1 2 3] :has-size 3) ; Asserts that the collection has size 3"
  (:assert-that helpers))

(def fail
  "Always fails with a given message.

  Parameters:
    `message` - The failure message.

  Usage Example:
    (fail \"This should always fail\") ; Always fails with the message \"This should always fail\""
  (:fail helpers))

(def assert-that-thrown-by
  "Asserts that a function throws an exception.

  Parameters:
    `function`   - The function expected to throw an exception.
    `assertions` - A sequence of assertions to be applied to the thrown exception.

  Usage Example:
    (assert-that-thrown-by #(throw (Exception. \"error\")) :exception-has-message \"error\")
    ; Asserts that the function throws an exception with the message \"error\""
  (:assert-that-thrown-by helpers))
