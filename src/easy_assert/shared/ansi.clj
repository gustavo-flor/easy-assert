(ns easy-assert.shared.ansi)

; Read more on https://en.wikipedia.org/wiki/ANSI_escape_code#Control_Sequence_Introducer_commands
(def ^:private control-sequence-introducer "\033[")

; Read more on https://en.wikipedia.org/wiki/ANSI_escape_code#3-bit_and_4-bit
(def color-codes {:red    (str control-sequence-introducer "31m")
                  :yellow (str control-sequence-introducer "33m")
                  :reset  (str control-sequence-introducer "0m")})
