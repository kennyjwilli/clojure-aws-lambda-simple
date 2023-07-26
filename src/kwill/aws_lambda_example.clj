(ns kwill.aws-lambda-example
  (:require [clojure.java.io :as io])
  (:gen-class
    :methods [^:static [handler
                        [java.io.InputStream
                         java.io.OutputStream
                         com.amazonaws.services.lambda.runtime.Context]
                        void]]))

(defn my-handler
  [{:keys [input]}]
  (println "hello lambda")
  (println "input" (pr-str (slurp input)))
  "abc123")

(defn -handler
  [is os context]
  (let [result (my-handler {:input is :context context})
        result-stream (io/input-stream (if (string? result) (.getBytes result) result))]
    (io/copy result-stream os)))
