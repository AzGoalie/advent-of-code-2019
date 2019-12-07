(ns day-3
  (:require [clojure.java.io :as io]
            [clojure.string :as str]
            [clojure.set :refer [intersection]]))

(def input (->> (io/resource "day_3_input.txt")
                (slurp)
                (str/split-lines)
                (map #(str/split % #","))
                (map #(map (fn [move]
                             [(first move) (Integer/parseInt (subs move 1))]) %))))

(defn gen-path [[x y] [direction n]]
  (drop 1
        (case direction
          \U (map vector (repeat x) (range y (+ y n 1)))
          \D (map vector (repeat x) (range y (- y n 1) -1))
          \L (map vector (range x (- x n 1) -1) (repeat y))
          \R (map vector (range x (+ x n 1)) (repeat y)))))

(defn follow-moves [moves]
  (reduce (fn [positions move]
            (concat positions (gen-path (last positions) move)))
          [[0 0]] moves))

(defn part-1 []
  (let [wire-1 (follow-moves (first input))
        wire-2 (follow-moves (second input))]
    (->> (intersection (set wire-1) (set wire-2))
         (map #(+ (Math/abs (first %)) (Math/abs (second %))))
         (sort)
         (second))))
