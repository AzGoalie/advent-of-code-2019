(ns day-2
  (:require [clojure.java.io :as io]
            [clojure.string :as str]))

(def input (as-> "day_2_input.txt" x
             (io/resource x)
             (slurp x)
             (str/split x #",")
             (map #(Integer/parseInt (str/trim %)) x)
             (vec x)))

(defn process-instruction[instruction memory]
  (let [arg1 (nth memory(nth instruction 1))
        arg2 (nth memory(nth instruction 2))
        dest (nth instruction 3)
        func (case (first instruction) 1 + 2 *)]
    (assoc memory dest (func arg1 arg2))))

(defn intcode
  ([memory] (intcode 0 memory))
  ([n memory]
   ;; [99 99 99] is used to pad out the last partition if it doesn't have enough numbers
   (let [instruction (nth (partition 4 4 [99 99 99] memory) n)]
     (if (= 99 (first instruction))
       (first memory)
       (recur (inc n) (process-instruction instruction memory))))))

(defn initialize-memory [noun verb]
  (assoc (assoc input 1 noun) 2 verb))

(defn part-1 []
  (intcode (initialize-memory 12 2)))

(defn part-2 []
  (some #(when (some? %) %)
        (for [noun (range 100) verb (range 100)]
          (let [memory (initialize-memory noun verb)
                value (intcode memory)]
            (if (= 19690720 value)
              (+ (* noun 100) verb))))))
