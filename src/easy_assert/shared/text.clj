(ns easy-assert.shared.text
  (:require [easy-assert.shared.ansi :refer [color-codes]]))

(defn highlight [text color]
  (str (get color-codes color :reset) text (get color-codes :reset)))

(defn yellow-highlight [text]
  (highlight text :yellow))

(defn red-highlight [text]
  (highlight text :red))
