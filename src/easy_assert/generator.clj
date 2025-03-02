(ns easy-assert.generator)

(defn ^:private generate-assert-that
  [assert matchers]
  (fn [value & assertions]
    (doseq [[matcher-key expected] (partition 2 assertions)]
      (let [match? (matcher-key matchers)
            [result message] (match? value expected)]
        (assert result message)))))

(defn ^:private generate-fail
  [assert]
  (fn [message]
    (assert false message)))

(defn ^:private generate-assert-that-thrown-by
  [assert-that fail]
  (fn [function & assertions]
    (try
      (function)
      (fail "Expected exception, but none was thrown")
      (catch Exception e
        (assert-that e assertions)))))

(defn generate-helpers
  [assert matchers]
  (let [assert-that           (generate-assert-that assert matchers)
        fail                  (generate-fail assert)
        assert-that-thrown-by (generate-assert-that-thrown-by assert-that fail)]
    {:assert-that           assert-that
     :fail                  fail
     :assert-that-thrown-by assert-that-thrown-by}))
