(ns pinball.game)
 
(defrecord Game [name slots])

(defn make ([n s] (Game. n s)))

(use '[pinball.slot :exclude [make] :as slot])

(defn default-game [probabilities]
  (pinball.game/make "default game" (map slot/default-make probabilities)))


(defn game-valid? [agame] (= 100 (reduce + (map #(:probability %) (:slots agame)))))

(defn weighted-rand-choice [agame]
  (let [w (reductions #(+ % %2) (map #(:probability %) (:slots agame)))
        r (rand-int (last w))]
    (nth (:slots agame) (count (take-while #( <= % r ) w)))))

(defn play-ball [agame]
  (let [slotpicked (weighted-rand-choice agame)
        newslot (slot/add-ball slotpicked)]
    (make (:name agame) (cons newslot (filter #(not= (:name slotpicked) (:name %)) (:slots agame))))))


