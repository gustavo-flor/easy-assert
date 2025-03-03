(ns easy-assert.shared.text
  (:require [easy-assert.shared.ansi :refer [get-color-code]]))

(defn highlight
  "Highlights the given text with the specified color.

  Parameters:
    `text`  - The text to highlight.
    `color` - The color to use for highlighting.

  Returns:
    The highlighted text as a string."
  [text color]
  (str (get-color-code color) text (get-color-code :reset)))

(defn yellow-highlight
  "Highlights the given text in yellow.

  Parameters:
    `text` - The text to highlight.

  Returns:
    The text highlighted in yellow as a string."
  [text]
  (highlight text :yellow))

(defn red-highlight
  "Highlights the given text in red.

  Parameters:
    `text` - The text to highlight.

  Returns:
    The text highlighted in red as a string."
  [text]
  (highlight text :red))
