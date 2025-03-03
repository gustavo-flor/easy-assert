(ns build
  (:require [clojure.tools.build.api :as b]
            [deps-deploy.deps-deploy :as dd]))

(def lib 'com.github.gustavo-flor/easy-assert)
(def version "0.0.1")
(def class-dir "target/classes")
(def basis (b/create-basis {:project "deps.edn"}))
(def jar-file (format "target/%s-%s.jar" (name lib) version))

(defn clean!
  "Clean '/target' folder"
  [_]
  (b/delete {:path "target"}))

(defn jar!
  "Generate jar file on '/target'"
  [_]
  (clean! nil)
  (b/write-pom {:class-dir class-dir
                :lib       lib
                :version   version
                :basis     basis
                :src-dirs  ["src"]
                :scm       {:url                 "https://github.com/gustavo-flor/easy-assert"
                            :connection          "scm:git:git://github.com/gustavo-flor/easy-assert.git"
                            :developerConnection "scm:git:ssh://git@github.com/gustavo-flor/easy-assert.git"
                            :tag                 version}
                :pom-data  [[:licenses
                             [:license
                              [:name "Apache License, Version 2.0"]
                              [:url "http://www.apache.org/licenses/LICENSE-2.0"]]]]})
  (b/copy-dir {:src-dirs   ["src" "resources"]
               :target-dir class-dir})
  (b/jar {:class-dir class-dir
          :jar-file  jar-file}))

(defn deploy!
  "Deploy built jar to Clojars"
  [_]
  (jar! nil)
  (dd/deploy {:installer :remote
              :artifact  jar-file
              :pom-file  (b/pom-path {:lib lib :class-dir class-dir})}))
