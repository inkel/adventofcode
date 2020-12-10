(ns adventofcode-2020.day6)

(require '[clojure.string :as s]
         '[clojure.java.io :as io])

(defn prepare-input [input]
  (let [lines (s/split-lines input)]
    (loop [acc []
           lines lines]
      (if (empty? lines)
        acc
        (let [group (take-while not-empty lines)]
          (recur (conj acc (s/join group))
                 (drop (+ (count group) 1) lines)))))))

(defn count-group-answers [group]
  (count (reduce conj #{} group)))

(defn sum-groups-answers [groups]
  (reduce + (map count-group-answers groups)))

(defn- input []
  (->> "day6.txt"
       io/resource
       slurp))

(defn part1 []
  (->> (input)
       prepare-input
       sum-groups-answers))
