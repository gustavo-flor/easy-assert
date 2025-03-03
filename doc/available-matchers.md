# Available Matchers

The following matchers are available by default in `easy-assert`:

---

### `:is-equal-to` 

Checks if the actual value is equal to the expected value.

```clojure
(assert-that 5 :is-equal-to 5)
```

### `:is-not-equal-to`

Checks if the actual value is not equal to the expected value.

```clojure
(assert-that 5 :is-not-equal-to 3)
```

### `:is`

Alias for `:is-equal-to`.

```clojure
(assert-that 5 :is 5)
```

### `:is-not`

Alias for `:is-not-equal-to`.

```clojure
(assert-that 5 :is-not 3)
```

### `:starts-with`

Checks if the actual string starts with the expected string.

```clojure
(assert-that "hello" :starts-with "he")
```

### `:has-size`

Checks if the collection has the expected size.

```clojure
(assert-that [1 2 3] :has-size 3)
```

### `:includes`

Checks if the actual collection includes all the expected elements.

```clojure
(assert-that [1 2 3] :includes [2 3])
```

### `:does-not-include`

Checks if the actual collection does not include any of the expected elements.

```clojure
(assert-that [1 2 3] :does-not-include [4 5])
```

### `:exception-has-message`

Checks if the exception has the expected message.

```clojure
(assert-that-thrown-by #(throw (Exception. "error")) 
                       :exception-has-message "error")
```

### `:exception-caused-by`

Checks if the exception was caused by the expected cause.

```clojure
(assert-that-thrown-by #(throw (Exception. "error" (Exception. "cause"))) 
                       :exception-caused-by (Exception. "cause"))
```
