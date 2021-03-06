(ns app.api
  (:require [om.next.server :as om]
            [om.next.impl.parser :as op]
            [taoensso.timbre :as timbre]))

(defmulti apimutate om/dispatch)
(defmulti api-read om/dispatch)

(defmethod apimutate :default [e k p]
  (timbre/error "Unrecognized mutation " k))

(defmethod api-read :default [{:keys [ast query] :as env} dispatch-key params]
    (timbre/error "Unrecognized query on dispatch key " dispatch-key (op/ast->expr ast)))

; This is the only thing we wrote for the server...just return some value so we can
; see it really talked to the server for this query.
(defmethod api-read :tab-data-query [env dispatch-key params]
  {:value {:text "This is a value from the server"}})
