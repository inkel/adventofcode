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

(defn fix-brute-force [input]
  (let [code (parse-code input)]
    (letfn [(move [idx n]
              (mod (+ idx n) (count code)))

            (toggle-op [code idx]
              (let [[op arg v] (nth code idx)]
                (assoc code
                       idx
                       [(if (= :jmp op) :nop :jmp) arg true])))]
      0)))

(defn- move-idx [code idx n]
  (mod (+ idx n) (count code)))

(defn- visit [code idx]
  (let [[op arg v] (nth code idx)]
    (assoc code idx [op arg true])))

(defn exec [acc idx code]
  (let [[op arg visited] (nth code idx)
        new-idx (move-idx code idx 1)
        new-code (visit code idx)]
    (case op
      "nop" [acc new-idx new-code]
      "acc" [(+ acc arg) new-idx new-code]
      "jmp" [acc (move-idx code idx arg) new-code])))

(defn dry-run [input]
  (let [code (parse-code input)

        move-idx (let [size (count code)]
                   (fn [idx n]
                     (mod (+ idx n) size)))

        last-idx (dec (count code))
        ]
    nil))
    ;; (loop [acc 0 idx 0 code code]
    ;;   (let [[op arg visited] (nth code idx)
    ;;         (if visited
    ;;           '(false nil)))))))
