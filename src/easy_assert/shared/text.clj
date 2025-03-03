(ns easy-assert.shared.text
  (:require [easy-assert.shared.ansi :refer [get-color-code]]))

(defn highlight [text color]
  (str (get-color-code color) text (get-color-code :reset)))

(defn yellow-highlight [text]
  (highlight text :yellow))

(defn red-highlight [text]
  (highlight text :red))
