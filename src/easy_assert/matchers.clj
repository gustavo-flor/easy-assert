(ns easy-assert.matchers
  (:require [clojure.string :as str]
            [easy-assert.shared.text :refer [red-highlight yellow-highlight]]))

(defn ^:private is-equal-to?
  [actual expected]
  (if (= actual expected)
    [true]
    [false (str "Expected: " (yellow-highlight expected) ", but got: " (red-highlight actual))]))

(defn ^:private is-not-equal-to?
  [actual expected]
  (if (not= actual expected)
    [true]
    [false (str "Expected: " (yellow-highlight expected) " not to be equal to, but got: " (red-highlight actual))]))

(defn ^:private starts-with?
  [actual expected]
  (if (str/starts-with? actual expected)
    [true]
    [false (str "Expected to start with: " (yellow-highlight expected) ", but got: " (red-highlight actual))]))

(defn ^:private has-size?
  [value expected]
  (let [actual (count value)]
    (if (= actual expected)
      [true]
      [false (str "Expected size: " (yellow-highlight expected) ", but got " (red-highlight actual))])))

(defn ^:private includes?
  [actual expected]
  (let [not-included (map yellow-highlight (filter #(not (contains? (set actual) %)) expected))]
    (if (empty? not-included)
      [true]
      [false (str (str/join ", " not-included) " should be included in " (pr-str actual))])))

(defn ^:private does-not-include?
  [actual expected]
  (let [included (map yellow-highlight (filter #(contains? (set actual) %) expected))]
    (if (empty? included)
      [true]
      [false (str (str/join ", " included) " should not be included in " (pr-str actual))])))

(defn ^:private exception-has-message?
  [exception expected]
  (let [actual (.getMessage exception)]
    (if (= actual expected)
      [true]
      [false (str "Expected exception message: " (yellow-highlight expected) ", but got: " (red-highlight actual))])))

(defn ^:private exception-caused-by?
  [exception expected]
  (let [actual (.getCause exception)]
    (if (= actual expected)
      [true]
      [false (str "Expected exception cause: " (yellow-highlight expected) ", but got: " (red-highlight actual))])))

(def default-matchers {:is-equal-to           is-equal-to?
                       :is-not-equal-to       is-not-equal-to?
                       :is                    is-equal-to?
                       :is-not                is-not-equal-to?
                       :starts-with           starts-with?
                       :has-size              has-size?
                       :includes              includes?
                       :does-not-include      does-not-include?
                       :exception-has-message exception-has-message?
                       :exception-caused-by   exception-caused-by?})
