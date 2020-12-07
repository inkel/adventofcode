(ns adventofcode-2020.day3
  (:gen-class))

(require '[clojure.java.io :as io]
         '[clojure.string :as str])

(defn next-pos [x y width height]
  (let [x (+ x 3)
        y (inc y)]
    (if (> y height)
      nil
      (if (> x width)
        (list (- x width) y)
        (list x y)))))

(defn count-trees [input]
  (let [width (count (first input))
        height (count input)]
    (loop [x 1
           y 1
           acc 0]
      (let [[x y] (next-pos x y width height)]
        (if (nil? x)
          acc
          (let [row (get input (dec y))
                c (get row (dec x))]
            (recur x y (+ acc (if (= c \#) 1 0)))))))))

(defn- input []
  (->> "day3.txt"
       io/resource
       slurp
       str/split-lines))

(defn part1 []
  (count-trees (input)))
