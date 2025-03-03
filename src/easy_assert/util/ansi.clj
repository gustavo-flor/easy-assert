(ns easy-assert.util.ansi)

; Read more on https://en.wikipedia.org/wiki/ANSI_escape_code#Control_Sequence_Introducer_commands
(def ^:private control-sequence-introducer "\033[")

; Read more on https://en.wikipedia.org/wiki/ANSI_escape_code#3-bit_and_4-bit
(def ^:private color-codes {:red    "31m"
                            :yellow "33m"
                            :reset  "0m"})

(defn get-color-code
  "Returns the ANSI escape code for the given color.

  Parameters:
    `color` - The color keyword for which to get the ANSI code.

  Returns:
    A string representing the ANSI escape code for the given color, or the reset code if the color is not found."
  [color]
  (str control-sequence-introducer (get color-codes color :reset)))
