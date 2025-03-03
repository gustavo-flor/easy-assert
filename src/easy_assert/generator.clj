(ns easy-assert.generator)

(defn ^:private generate-assert-that
  "Generates an assertion function that checks if a value matches the expected criteria.

  Parameters:
    `assert`   - The assertion function to use.
    `matchers` - A map of matcher functions.

  Returns:
    A function that takes a value and a sequence of assertions, and applies the matchers to the value.

  Example:
    (def assert-fn (generate-assert-that default-assert default-matchers))
    (assert-fn 5 :is-equal-to 5) ; Asserts that 5 is equal to 5
    (assert-fn \"hello\" :starts-with \"he\") ; Asserts that \"hello\" starts with \"he\")"
  [assert matchers]
  (fn [value & assertions]
    (doseq [[matcher-key expected] (partition 2 assertions)]
      (let [match? (matcher-key matchers)
            [result message] (match? value expected)]
        (assert result message)))))

(defn ^:private generate-fail
  "Generates a function that always fails with a given message.

  Parameters:
    `assert` - The assertion function to use.

  Returns:
    A function that takes a message and fails the assertion.

  Example:
    (def fail-fn (generate-fail default-assert))
    (fail-fn \"This should always fail\") ; Always fails with the message \"This should always fail\""
  [assert]
  (fn [message]
    (assert false message)))

(defn ^:private generate-assert-that-thrown-by
  "Generates an assertion function that checks if a function throws an exception.

  Parameters:
    `assert-that` - The assertion function to use for checking the exception.
    `fail`        - The function to call if no exception is thrown.

  Returns:
    A function that takes a function and a sequence of assertions, and checks if the function throws an exception.

  Example:
    (def assert-thrown-fn (generate-assert-that-thrown-by assert-that fail))
    (assert-thrown-fn #(throw (Exception. \"error\")) :exception-has-message \"error\")"
  [assert-that fail]
  (fn [function & assertions]
    (try
      (function)
      (fail "Expected exception, but none was thrown")
      (catch Exception e
        (assert-that e assertions)))))

(defn generate-helpers
  "Generates a map of helper assertion functions.

  Parameters:
    `assert`   - The assertion function to use.
    `matchers` - A map of matcher functions.

  Returns:
    A map containing the generated helper functions."
  [assert matchers]
  (let [assert-that           (generate-assert-that assert matchers)
        fail                  (generate-fail assert)
        assert-that-thrown-by (generate-assert-that-thrown-by assert-that fail)]
    {:assert-that           assert-that
     :fail                  fail
     :assert-that-thrown-by assert-that-thrown-by}))
