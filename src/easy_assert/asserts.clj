(ns easy-assert.asserts
  (:require [clojure.test :refer [is]]))

(defn default-assert
  [result message]
  (is result (str message "\n")))
