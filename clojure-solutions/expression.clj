(defn constant [value] (fn [_] value))
(defn variable [name] (fn [vars] (get vars name)))

(defn operation [f] (fn [& args] (fn [vars] (apply f (map #(% vars) args)))))
(def add (operation +))
(def subtract (operation -))
(def multiply (operation *))
(def divide (operation (fn ([a] (/ (double 1) a))
                               ([a & args] (reduce #(/ (double %1) %2) a args)))))
(def negate subtract)
(def sum add)
(def avg (operation (fn [& args] (/ (reduce + args) (count args)))))
(def pow (operation #(Math/pow %1 %2)))
(def log (operation (fn [a b] (/ (Math/log (Math/abs b)) (Math/log (Math/abs a))))))
(def exp (operation #(Math/exp %)))
(def ln (operation #(Math/log %)))
(defn sumexpEval [& args] (reduce + (map #(Math/exp %) args)))
(def sumexp (operation sumexpEval))
(def lse (operation (fn [& args] (Math/log (apply sumexpEval args)))))
(defn meansqEval [& args] (/ (reduce + (map #(* % %) args)) (count args)))
(def meansq (operation meansqEval))
(def rms (operation (fn [& args] (Math/sqrt (apply meansqEval args)))))

(def symbols {'+ add, '- subtract, '* multiply, '/ divide, 'negate negate
, 'sum sum, 'avg avg, 'pow pow, 'log log, 'exp exp, 'ln ln, 'sumexp sumexp, 'lse lse, 'meansq meansq, 'rms rms});extra

(defn parseFunction [input] (let [inputList (read-string (str input))]
                              (if (number? inputList)
                                (constant inputList)
                                (if (symbol? inputList)
                                  (variable (str inputList))
                                  (apply (get symbols (first inputList)) (map parseFunction (rest inputList)))))))
