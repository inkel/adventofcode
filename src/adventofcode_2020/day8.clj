(ns adventofcode-2020.day8
  (:require [clojure.string :as s]
            [clojure.java.io :as io]))

(def ^:private input
  (->> "day8.txt"
       io/resource
       slurp))

(defn parse-code [input]
  (letfn [(parse-line [line]
            (let [[op arg] (s/split line #" ")]
              [op (Integer/parseInt arg) false]))]
    (->> input
        s/split-lines
        (map parse-line)
        (apply vector))))

(defn run [input]
  (let [code (parse-code input)
        move (fn [idx n]
               (mod (+ idx n) (count code)))]
    (loop [acc 0
           idx 0
           code code]
      (let [[op arg visited] (nth code idx)
            new-code (assoc code idx [op arg true] )]
        (if visited
          acc
          (case op
            "nop" (recur acc (move idx 1) new-code)
            "acc" (recur (+ acc arg) (move idx 1) new-code)
            "jmp" (recur acc (move idx arg) new-code)))))))

(defn part1 []
  (run input))
