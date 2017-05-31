(defproject verify-translations "0.1.0-SNAPSHOT"
  :description "FIXME: write this!"
  :url "http://example.com/FIXME"

  :min-lein-version "2.5.3"

  :dependencies [[org.clojure/clojure "1.9.0-alpha16"]
                 [org.clojure/clojurescript "1.9.562"]
                 [com.cognitect/transit-cljs "0.8.239"]
                 [metosin/spec-tools "0.2.0"]
                 [org.clojure/spec.alpha "0.1.109"]
                 [org.clojure/test.check "0.9.0"]]

  :plugins [[lein-cljsbuild "1.1.6"]
            [lein-figwheel "0.5.10"]]

  :clean-targets ["server.js"
                  "target"]

  :source-paths ["src"]

  :profiles {:dev {:dependencies [[com.cemerick/piggieback "0.2.1"]
                                  [figwheel-sidecar "0.5.10"]]
                   :source-paths ["cljs_src" "dev"] }}

  :repl-options {:nrepl-middleware [cemerick.piggieback/wrap-cljs-repl]}

  :cljsbuild {
    :builds [{:id "dev"
              :source-paths ["src"]
              :figwheel true
              :compiler {
                :main verify-translations.core
                :output-to "target/server_dev/verify_translations.js"
                :output-dir "target/server_dev"
                :target :nodejs
                :optimizations :none
                :source-map true}}
             {:id "prod"
              :source-paths ["src"]
              :compiler {
                :output-to "target/server_prod/server.js"
                :output-dir "target/server_prod"
                :target :nodejs
                :optimizations :simple}}]})
