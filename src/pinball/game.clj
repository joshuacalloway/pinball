(ns pinball.game
  (:import (java.awt Color Dimension)
           (javax.swing JPanel JFrame JOptionPane)
           (java.awt.event ActionListener KeyListener))
  )

(require ['pinball.ball :as 'ball])

(defn- quit-key? [c]
  (= \q c)
  )

(defn handle-key [c game controls]
  (cond
   (quit-key? c) (System/exit 1)
   ))

(defn game-panel [frame game controls]
  (proxy [JPanel ActionListener KeyListener] []
    (keyPressed [e]
      (handle-key (.getKeyChar e) game controls)
      (.repaint this)
      )
    (getPreferredSize []
      (Dimension. 500 500)
      )
    
    )
  )

  
(defn game-frame []
  (let [
        controls (ref (struct-map controls
                        :center center
                        :clear false))
        game nil
        frame (JFrame. "Pinball")
        panel (game-panel frame controls)
        ]
    (doto panel
      (.setFocusable true)
      (.addKeyListener panel)
      )
    (doto frame
      (.add panel)
      (.pack)
      (.setVisible true)
      (.setDefaultCloseOperation JFrame/EXIT_ON_CLOSE)))))

(defn run-game [] (game-frame))
