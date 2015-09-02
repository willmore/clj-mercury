(ns mercury.mq
  (:require [mercury.queue :as queue]
            [langohr.core      :as rmq]
            [langohr.channel   :as lch]
            [langohr.queue     :as lq]
            [langohr.consumers :as lc]
            [langohr.basic     :as lb]))

(def default-exchange-name "")

(defn start-mq! []
  )

(defn create-queue! [qname]
  "Connect to exchange, create queue with given 'qname' and return handle."
  (let [conn  (rmq/connect)
        ch    (lch/open conn)]
    (lq/declare ch qname {:exclusive false})
    {:queue-type :mq :name qname :channel ch}))

(defn text-headers []
  {:content-type "text/plain" :type "plain.text"})

(defmethod queue/send! :mq [q msg]
  "Send single msg to the queue"
  (lb/publish (:channel q) default-exchange-name (:name q) msg (text-headers)))

(defn- promise-listener [message]
  (fn
    [ch metadata ^bytes payload]
    (let [str-payload (String. payload "UTF-8")]
      (println "delivering:" str-payload)
      (deliver message str-payload))))

(defmethod queue/receive! :mq [q]
  "Receive a single message; blocks until reception"
  (let [message (promise)]
    (lc/subscribe (:channel q) (:name q) (promise-listener message) {:auto-ack true})
    (println "awaiting..." (realized? message))
    @message))