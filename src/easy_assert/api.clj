(ns easy-assert.api
  (:require [easy-assert.asserts :refer [default-assert]]
            [easy-assert.generator :refer [generate-helpers]]
            [easy-assert.matchers :refer [default-matchers]]))

(def ^:private helpers (generate-helpers default-assert default-matchers))

(def assert-that (:assert-that helpers))

(def fail (:fail helpers))

(def assert-that-thrown-by (:assert-that-thrown-by helpers))
