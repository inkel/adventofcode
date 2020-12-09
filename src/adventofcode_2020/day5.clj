(ns adventofcode-2020.day5)

(require '[clojure.string :as s]
         '[clojure.java.io :as io])

(defn- decode-bsp [data minmax lower-half-cmp]
  (letfn [(reduce-min-max [[min max] c]
            (let [diff (int (/ (- max min) 2))
                  middle (+ min diff)]
              (if (lower-half-cmp c)
                [min middle]
                [(inc middle) max])))]
    (first (reduce reduce-min-max minmax data))))

(defn parse-row [data]
  (decode-bsp data [0 127] #(= \F %)))

(defn parse-column [data]
  (decode-bsp data [0 7] #(= \L %)))

(defn seat-id [boarding]
  (+ (* 8 (parse-row (take 7 boarding)))
     (parse-column (drop 7 boarding))))

(defn- input []
  (->> "day5.txt"
       io/resource
       slurp
       s/split-lines))

(defn part1 []
  (->> (input)
       (map seat-id)
       (apply max)))
