(ns easy-assert.api
  (:require [clojure.test :refer [is]]
            [easy-assert.generator :refer [generate-helpers]]
            [easy-assert.matchers :refer [default-matchers]]))

(defn ^:private is-fn [form msg] (is form msg))

(def ^:private helpers (generate-helpers is-fn default-matchers))

(def assert-that (:assert-that helpers))

(def fail (:fail helpers))

(def assert-that-thrown-by (:assert-that-thrown-by helpers))
