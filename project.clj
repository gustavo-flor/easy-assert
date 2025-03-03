(defproject com.github.gustavo-flor/easy-assert "1.0.3"
  :description "A Clojure library designed to help you with assertions."
  :url "https://github.com/gustavo-flor/easy-assert"
  :license {:name "Apache License, Version 2.0"
            :url  "http://www.apache.org/licenses/LICENSE-2.0"}
  :dependencies [[org.clojure/clojure "1.11.1"]]
  :plugins [[lein-project-version "0.1.0"]]
  :repl-options {:init-ns easy-assert.api}
  :repositories [["releases" {:url           "https://clojars.org/repo"
                              :username      :env/clojars_username
                              :password      :env/clojars_password
                              :sign-releases false}]])
