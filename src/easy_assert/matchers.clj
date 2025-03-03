(ns easy-assert.matchers
  (:require [clojure.string :as str]
            [easy-assert.shared.text :refer [red-highlight yellow-highlight]]))

(defn ^:private is-equal-to?
  "Checks if the actual value is equal to the expected value.

  Parameters:
    `actual`   - The actual value.
    `expected` - The expected value.

  Returns:
    A vector containing a boolean indicating if the values are equal and a message if they are not."
  [actual expected]
  (if (= actual expected)
    [true]
    [false (str "Expected: " (yellow-highlight expected) ", but got: " (red-highlight actual))]))

(defn ^:private is-not-equal-to?
  "Checks if the actual value is not equal to the expected value.

  Parameters:
    `actual`   - The actual value.
    `expected` - The expected value.

  Returns:
    A vector containing a boolean indicating if the values are not equal and a message if they are."
  [actual expected]
  (if (not= actual expected)
    [true]
    [false (str "Expected: " (yellow-highlight expected) " not to be equal to, but got: " (red-highlight actual))]))

(defn ^:private starts-with?
  "Checks if the actual string starts with the expected string.

  Parameters:
    `actual`   - The actual string.
    `expected` - The expected starting string.

  Returns:
    A vector containing a boolean indicating if the actual string starts with the expected string and a message if it does not."
  [actual expected]
  (if (str/starts-with? actual expected)
    [true]
    [false (str "Expected to start with: " (yellow-highlight expected) ", but got: " (red-highlight actual))]))

(defn ^:private has-size?
  "Checks if the collection has the expected size.

  Parameters:
    `actual`   - The actual collection.
    `expected` - The expected size.

  Returns:
    A vector containing a boolean indicating if the collection has the expected size and a message if it does not."
  [actual expected]
  (let [size (count actual)]
    (if (= size expected)
      [true]
      [false (str "Expected size: " (yellow-highlight expected) ", but got " (red-highlight size))])))

(defn ^:private includes?
  "Checks if the actual collection includes all the expected elements.

  Parameters:
    `actual`   - The actual collection.
    `expected` - The expected elements.

  Returns:
    A vector containing a boolean indicating if the actual collection includes all the expected elements and a message if it does not."
  [actual expected]
  (let [not-included (map yellow-highlight (filter #(not (contains? (set actual) %)) expected))]
    (if (empty? not-included)
      [true]
      [false (str (str/join ", " not-included) " should be included in " (pr-str actual))])))

(defn ^:private does-not-include?
  "Checks if the actual collection does not include any of the expected elements.

  Parameters:
    `actual`   - The actual collection.
    `expected` - The expected elements.

  Returns:
    A vector containing a boolean indicating if the actual collection does not include any of the expected elements and a message if it does."
  [actual expected]
  (let [included (map yellow-highlight (filter #(contains? (set actual) %) expected))]
    (if (empty? included)
      [true]
      [false (str (str/join ", " included) " should not be included in " (pr-str actual))])))

(defn ^:private exception-has-message?
  "Checks if the exception has the expected message.

  Parameters:
    `exception` - The exception.
    `expected`  - The expected message.

  Returns:
    A vector containing a boolean indicating if the exception has the expected message and a message if it does not."
  [exception expected]
  (let [actual (.getMessage exception)]
    (if (= actual expected)
      [true]
      [false (str "Expected exception message: " (yellow-highlight expected) ", but got: " (red-highlight actual))])))

(defn ^:private exception-caused-by?
  "Checks if the exception was caused by the expected cause.

  Parameters:
    `exception` - The exception.
    `expected`  - The expected cause.

  Returns:
    A vector containing a boolean indicating if the exception was caused by the expected cause and a message if it was not."
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
