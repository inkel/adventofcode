(ns adventofcode-2020.day9
  (:require [clojure.string :as str]
            [clojure.java.io :as io]))

(def ^:private preamble-size 25)

(defn- input []
  (->> "day9.txt"
       io/resource
       slurp
       str/split-lines
       (map (fn [x] (Integer/parseInt x)))))

(defn find-pair-sum [number preamble]
  (for [a preamble
        b preamble
        :when (= number (+ a b))] [a b]))

(defn find-invalid [input preamble-size]
  (let [preamble (take preamble-size input)
        number (nth input preamble-size)
        valid? (find-pair-sum number preamble)]
    (if (empty? valid?)
      number
      (recur (rest input) preamble-size))))

(defn part1 []
  (find-invalid (input) preamble-size))
