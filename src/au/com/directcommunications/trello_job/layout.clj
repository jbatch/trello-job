(ns au.com.directcommunications.trello-job.layout
  (:require [net.cgrand.enlive-html :as html]))

(html/deftemplate default-layout "templates/layout/default.html"
  [{title :title body :body}]
  [:head :title] (html/content title)
  [:html :body] (html/content body))

(defn wrap-in-layout
  "Wraps the generated webpage in a layout, the response is checked
  for details so the generated page can determine the layout, etc."
  [response]
  (default-layout response))
