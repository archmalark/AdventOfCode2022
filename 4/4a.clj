(require '[clojure.string :as str])



(defn parseTuple [tuple]
  (map read-string (str/split tuple #"-"))
  )

(defn parseRow [row]
  (map parseTuple (str/split row #","))
  )



(defn calculateOverlap [firstPair secondPair exactmatch]
  (cond
    (and (= (first firstPair) (first secondPair)) 
         (= (last firstPair) (last secondPair))) exactmatch
    (and (>= (last firstPair) (last secondPair))
     (>= (first secondPair) (first firstPair) ) ) 1
    :else 0)
  )

(defn solve [input]
  (if (empty? input)
    0
    (+ (calculateOverlap (first (parseRow (first input))) (last (parseRow (first input))) 1 )
       (calculateOverlap (last (parseRow (first input))) (first (parseRow (first input))) 0)
       (solve (rest input))
       ))
  
  )



;; (parseRow "4-9,3-7")
;; Test input
(solve (str/split (slurp "/home/arkmal/Kod/aoc2022/4/4a-example.input") #"\n"))

;; Real input
(solve (str/split (slurp "/home/arkmal/Kod/aoc2022/4/4a.input") #"\n"))

