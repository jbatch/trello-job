(ns au.com.directcommunications.trello-job.middleware
  (:require [au.com.directcommunications.trello-job.layout :as layout]))

(defn wrap-layout
  "Will wrap the body in the default layout of the site."
  [handler]
  (fn [request]
    (when-let [response (handler request)]
      (let [status (:status response 200)
            new-body (layout/wrap-in-layout response)]
        (assoc response
          :headers (merge (response :headers)
                          {"Content_Type" "text/html;charset=UTF=8"})
          :status (or (response :status) 200)
          :body new-body)))))
