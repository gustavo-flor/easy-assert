(ns easy-assert.shared.ansi)

; Read more on https://en.wikipedia.org/wiki/ANSI_escape_code#Control_Sequence_Introducer_commands
(def ^:private control-sequence-introducer "\033[")

; Read more on https://en.wikipedia.org/wiki/ANSI_escape_code#3-bit_and_4-bit
(def ^:private color-codes {:red    "31m"
                  :yellow "33m"
                  :reset  "0m"})

(defn get-color-code
  [color]
  (str control-sequence-introducer (get color-codes color :reset)))
