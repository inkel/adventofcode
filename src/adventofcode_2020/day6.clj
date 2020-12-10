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

(defn count-group-answers-everyone [group]
  (let [npeople (count group)]
    (loop [acc {}
           group group]
      (let [answers (first group)]
        (if (nil? answers)
          (count (filter (fn [[k v]] (= v npeople)) acc))
          (recur (reduce (fn [m x] (conj m [x (inc (get m x 0))])) acc answers)
                 (rest group)))))))

(defn sum-group-answers-everyone [input]
  (letfn [(prepare-input [input]
            (let [lines (s/split-lines input)]
              (loop [acc [] lines lines]
                (if (empty? lines)
                  acc
                  (recur (conj acc (take-while not-empty lines))
                         (rest (drop-while not-empty lines)))))))]
    (reduce + (map count-group-answers-everyone (prepare-input input)))))

(defn part2 []
  (sum-group-answers-everyone (input)))
