(ns adventofcode-2020.day3
  (:gen-class))

(require '[clojure.java.io :as io]
         '[clojure.string :as str])

(defn next-pos
  ([x y width height] (next-pos x y 3 1 width height))
  ([x y dx dy width height]
   (let [x (+ x dx)
         y (+ y dy)]
     (if (> y height)
       nil
       (if (> x width)
         (list (- x width) y)
         (list x y))))))

(defn count-trees
  ([input] (count-trees input 3 1))
  ([input dx dy]
   (let [width (count (first input))
         height (count input)]
     (loop [x 1
            y 1
            acc 0]
       (let [[x y] (next-pos x y dx dy width height)]
         (if (nil? x)
           acc
           (let [row (get input (dec y))
                 c (get row (dec x))]
             (recur x y (+ acc (if (= c \#) 1 0))))))))))

(defn- input []
  (->> "day3.txt"
       io/resource
       slurp
       str/split-lines))

(defn part1 []
  (count-trees (input)))

(def slopes [[1 1] [3 1] [5 1] [7 1] [1 2]])

(defn count-trees-by-slopes [input slopes]
  (for [[dx dy] slopes]
    (count-trees input dx dy)))

(defn part2 []
  (apply * (count-trees-by-slopes (input) slopes)))
