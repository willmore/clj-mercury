(ns mercury.queue)

(defmulti send!
  "Send single msg to the queue"
  :queue-type)

(defmulti receive!
  "Receive a single message; blocks until reception"
  :queue-type)
