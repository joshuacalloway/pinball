(ns pinball.slot)

(defstruct slot :name :probability :numballs)

(defn make ([n p] (struct slot n p 0)))

