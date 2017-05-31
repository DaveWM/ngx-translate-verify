(ns verify-translations.core
  (:require [cljs.nodejs :as nodejs]
            [cognitect.transit :as t]
            [clojure.spec.alpha :as s]
            [spec-tools.data-spec :as data-spec]
            [clojure.string :as str]))

(nodejs/enable-util-print!)

(def fs (nodejs/require "fs"))

(s/def ::translated-string
  (s/and string? (complement str/blank?)))

(defn update-keys [update-fn m]
  (->> m
       (map (fn [[k v]] [(update-fn k) v]))
       (into {})))

(defn read-json [path]
  (let [r (t/reader :json)]
    (->> (.readFileSync fs path)
         (t/read r)
         (update-keys keyword))))

(defn build-spec [valid-map]
  (let [valid-keys (keys valid-map)
        keys-spec (->> (zipmap (keys valid-map)
                               (repeat ::translated-string))
                       (data-spec/spec ::translation-keys)
                       :spec)]
    (s/and (s/map-of qualified-keyword? ::translated-string)
           keys-spec)))

(defn exit-with-error [message]
  (do (println message)
      (.exit js/process 1)))

(defn -main []
  (let [[valid-filepath to-validate-filepath] (drop 2 (.-argv js/process))]
    (if (and valid-filepath to-validate-filepath)
      (let [json-reader (t/reader :json)
            valid (read-json valid-filepath)
            to-validate (read-json to-validate-filepath)
            spec (build-spec valid)]
        (if (s/valid? spec to-validate)
          (println "Translation file is valid")
          (do (s/explain spec to-validate)
              (exit-with-error "Failed validation"))))
      (exit-with-error "Invalid args passed - need a valid filepath and a path to file to validate"))
    ))

(set! *main-cli-fn* -main)
