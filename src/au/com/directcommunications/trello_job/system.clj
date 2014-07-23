(ns au.com.directcommunications.trello-job.system
  "This namespace contains the start and stop functions for creating
  the system object that is to be used for the system."
  (:require [environ.core :refer [env]]
            [ring.adapter.jetty :refer [run-jetty]]
            [ring.middleware.stacktrace :refer [wrap-stacktrace]]
            [au.com.directcommunications.trello-job.routes :as app-routes])
  (:gen-class))

(defn system
  "Returns a whole instance of the system."
  []
  {:handler #'app-routes/app
   :server nil})

(defn start
  "Perform the side effects to initialise the system, acquire
  resources, and start it running. Returns an updated instance of the
  system."
  [system]
  (let [handler (wrap-stacktrace (:handler system))
        server (run-jetty handler {:port 3000 :join? false})]
    (assoc system :server server :handler handler)))

(defn stop
  "Performs side effects to shut down the system and release its
  resources. Returns an updated instance of the system."
  [system]
  (if (:server system)
    (do
      (.stop (:server system))
      (assoc system :server nil :handler #'app-routes/app))
    system))

(defn -main
  "Main that starts the whole program."
  []
  (start (system)))
