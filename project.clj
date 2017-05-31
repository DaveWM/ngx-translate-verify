(defproject ngx-translate-verify "0.1.0"
  :description "CLI tool for verifying ngx translate translation files"
  :url "https://github.com/DaveWM/ngx-translate-verify"

  :min-lein-version "2.5.3"

  :dependencies [[org.clojure/clojure "1.9.0-alpha16"]
                 [org.clojure/clojurescript "1.9.562"]
                 [com.cognitect/transit-cljs "0.8.239"]
                 [metosin/spec-tools "0.2.0"]
                 [org.clojure/spec.alpha "0.1.109"]
                 [org.clojure/test.check "0.9.0"]]

  :plugins [[lein-cljsbuild "1.1.6"]
            [lein-figwheel "0.5.10"]]

  :clean-targets ["target"]

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
                :output-to "target/dev/main.js"
                :output-dir "target/dev"
                :target :nodejs
                :optimizations :none
                :source-map true}}
             {:id "prod"
              :source-paths ["src"]
              :compiler {
                :output-to "target/prod/main.js"
                :output-dir "target/prod"
                :target :nodejs
                :optimizations :simple}}]})
