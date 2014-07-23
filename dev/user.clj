(ns user
  (:require [clojure.tools.namespace.repl :refer (refresh refresh-all)]
            [au.com.directcommunications.trello-job.system :as system]))

(def system nil)

(defn init
  "Constructs the current development system."
  []
  (alter-var-root #'system
                  (constantly (system/system))))

(defn start
  "Starts the current development system."
  []
  (alter-var-root #'system system/start))

(defn stop
  "Shuts down and destroys the current development system."
  []
  (alter-var-root #'system
                  (fn [s] (when s (system/stop s)))))

(defn go
  "Initialises the current development system and starts it running."
  []
  (init)
  (start))
