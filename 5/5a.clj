(require '[clojure.string :as str])



  (defn parseAddBox [table box column]
    (cond
      (= box \space) table
      :else (assoc table column (conj (vec (get table column)) box))
      )
    
    )
  

(defn parseAddRow [row table columnNr]
  (cond 
    (empty? row) table
    :else (parseAddRow (str/replace (subs row 3) #"^\s?" "") (parseAddBox table (.charAt row 1) columnNr ) (+ columnNr 1)))
  )


(defn parseInitialConfig [input table]
  (cond
    ;; If line starts with a 1. Its the number line
    (= (.charAt (first input) 1) \1) table
       :else (parseInitialConfig (rest input) (parseAddRow (first input) table 0))
       
       ))

(defn getMoveList [input]
  (cond
    (= (first input) "") (rest input)
    :else (getMoveList (rest input))
    ))

(defn getAmountToMove [move]
  (read-string (get (str/split move #" ") 1)))

(defn getFromStack [move]
  (- (read-string (get (str/split move #" ") 3)) 1))

(defn getToStack [move]
  (- (read-string (get (str/split move #" ") 5)) 1))



(defn copyBox [boxTable from to]
  (assoc boxTable to (conj (seq (get boxTable to)) (first (get boxTable from))))
  )


(defn removeBox [boxTable from]
  (assoc boxTable from (vec (rest (get boxTable from))) )
  )

(defn makeMove [amount from to boxTable]
  (cond
    (= amount 0) boxTable 
    :else (makeMove (- amount 1) from to (removeBox (copyBox boxTable from to) from)) )
)

(defn makeMessage [boxTable counter message]
  (cond
    (= counter (count boxTable)) (clojure.string/join "" message)
    :else (makeMessage boxTable (+ counter 1) (conj message (first (get boxTable counter)))))
  )

(defn solve [moveList boxTable]
  (cond
    (empty? moveList) (makeMessage boxTable 0 [])
    :else (solve (rest moveList) (makeMove (getAmountToMove (first moveList)) (getFromStack (first moveList)) (getToStack (first moveList)) boxTable))
    )
  )





;; Test input
(solve (getMoveList (str/split (slurp "/home/arkmal/Kod/aoc2022/5/5a-example.input") #"\n"))
       (parseInitialConfig (str/split (slurp "/home/arkmal/Kod/aoc2022/5/5a-example.input") #"\n") {})
       )


;; Real input
(solve (getMoveList (str/split (slurp "/home/arkmal/Kod/aoc2022/5/5a.input") #"\n"))
       (parseInitialConfig (str/split (slurp "/home/arkmal/Kod/aoc2022/5/5a.input") #"\n") {}))


