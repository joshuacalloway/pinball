(ns pinball.gamegui
    (:use seesaw.core seesaw.border)
    (:import (java.awt Color))
    )

(require ['pinball.slot :as 'slot])
(require ['pinball.game :as 'game])
(require ['seesaw.bind :as 'b])

(defn slotgui-text[s]
  (str (:name s) " has #" (:numballs s) " balls"))

(defn slotgui [s]
  (label :id (:id s) :text (slotgui-text s)))

(defn game-canvas [g]
  (vertical-panel
   :background Color/GRAY
   :border (line-border :color Color/BLACK :thickness 5)
   :items (vec (map #(slotgui %) (:slots g)))))

(defn update-slot [acanvas aslot]
  (let [ labels (select acanvas [:<javax.swing.JLabel>])
        found (some #(if (= (config % :id) (:id aslot)) %) labels)
        ]
    (println "aslot is : " aslot)
    (println "found is : " found)
    (config! found :text (slotgui-text aslot))))

(defn update-canvas [acanvas slots]
  (doseq [i slots] (update-slot acanvas i)))
  



(defn game-controls [agame acanvas]
  (println "in game-controls")
  (horizontal-panel
   :items [
           (button :id :button1 :text "One Ball" :listen [:action (fn [e] (update-canvas acanvas (:slots (game/play-ball agame))  )) ])
           (button :id :button2 :text "100 Balls")
           (button :id :button3 :text "1,000 Balls")
           (button :id :button4 :text "10,000 Balls")]
   )
  )

(defn create-gamegui [g]
  (let [canvas (game-canvas g)]
    (-> (frame :title "Pinball Demo"
               :width 400 :height 600
               :content (border-panel
                         :center canvas
                         :south (game-controls g canvas)
                         )
               :on-close :exit)
        pack!
        show!)))
  
      
(defn run-game [] (create-gamegui (game/default-game [31 19 50])))

