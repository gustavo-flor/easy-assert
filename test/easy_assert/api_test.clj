(ns easy-assert.api-test
  (:require [easy-assert.api :refer :all]
            [clojure.test :refer :all]))

(def ^:private fellowship-of-the-ring
  ["frodo" "sam" "gandalf" "legolas" "aragorn" "boromir" "gimli" "merry" "pippin"])

(def ^:private other-exception
  (Exception.))

(def ^:private some-exception
  (Exception. "boom!" other-exception))

(deftest api-test
  (testing "Should test the assertions"
    (assert-that 1 :is-equal-to 1)
    (assert-that 0 :is-not-equal-to 1)
    (assert-that true :is true)
    (assert-that "Lorem" :is-not "Ipsum")
    (assert-that "Hello World" :starts-with "Hello")
    (assert-that fellowship-of-the-ring
                 :has-size 9
                 :includes ["frodo" "sam"]
                 :does-not-include ["sauron"])
    (assert-that-thrown-by #(throw some-exception)
                           :exception-has-message "boom!"
                           :exception-caused-by other-exception
                           :is-equal-to some-exception)))
