# Easy Assert

[![Clojars Project](https://img.shields.io/clojars/v/com.github.gustavo-flor/easy-assert.svg)](https://clojars.org/com.github.gustavo-flor/easy-assert)

A Clojure library designed to help you with assertions.

## Installation

Add the following dependency to your `project.clj` file:

```clojure
[com.github.gustavo-flor/easy-assert "*.*.*"]
```

Other available options on [Clojars repo](https://clojars.org/com.github.gustavo-flor/easy-assert).

## Usage

### Importing the Library

First, require the necessary namespaces in your Clojure file:

```clojure
(ns your-namespace
  (:require [easy-assert.api :refer :all]))
```

### Using the Default API

The default API provides a set of helper functions for common assertions.

#### Using `assert-that`

The `assert-that` function is used to assert various conditions:

```clojure
(assert-that 5 :is-equal-to 5)
(assert-that 5 :is-not-equal-to 10)
(assert-that "hello" :starts-with "he")

; You can attach multiple conditions to be validated
(assert-that [1 2 3] 
             :has-size 3
             :includes [1 2]
             :does-not-include [4 5])
```

#### Using `fail`

The `fail` function is used to force a failure with a given message:

```clojure
(fail "This should always fail") 
; Always fails with the message "This should always fail"
```

#### Using `assert-that-thrown-by`

The `assert-that-thrown-by` function is used to assert that a function throws an exception:

```clojure
(def exception (Exception. "error"))

(assert-that-thrown-by #(throw exception) 
                       :exception-has-message "error"
                       :exception-caused-by nil) ; Passes
```

### Customizing with Generator

You can customize the assertion functions by generating your own helpers.

#### Generating Helper Functions

First, require the necessary namespaces:

```clojure
(ns your-namespace
  (:require [easy-assert.generator :refer [generate-helpers]]))
```

Then, generate the helper functions using your assertion function and matchers:

```clojure
(def my-assert (fn [result message] 
                 (when (not result) 
                   (throw (AssertionError. message)))))

(defn has-status?
  [actual expected]
  (if (= (:status actual) expected)
    [true]
    [false "My custom failure message"]))

(def my-matchers {:has-status has-status?})

(def helpers (generate-helpers my-assert (merge default-matchers my-matchers)))

(def assert-that (:assert-that helpers))
```

Now you can use the generated helper functions in the same way as the default API:

```clojure
(def http-response {:status 200 ,,,})

(assert-that http-response :has-status 200)
```

## Contributing

1. Fork the repository on GitHub.
2. Create a new branch for your feature or bugfix.
3. Write your code and tests.
4. Ensure all tests pass.
5. Submit a pull request with a clear description of your changes.
