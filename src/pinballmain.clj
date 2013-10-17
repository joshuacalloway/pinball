(ns pinballmain (:gen-class))

(use 'pinball.game)


(defn -main [& args] (run-game))
