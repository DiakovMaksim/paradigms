(load-file "proto.clj")
(defn constant [value] (fn [_] value))
(defn variable [name] (fn [vars] (get vars name)))
(defn operation [f] (fn [& args] (fn [vars] (apply f (map #(% vars) args)))))
(def add (operation +))
(def subtract (operation -))
(def multiply (operation *))
(defn div
  ([a] (/ (double 1) a))
  ([a & args] (reduce #(/ (double %1) %2) a args)))
(def divide (operation div))
(def negate subtract)
(def sum add)
(defn avgEval [& args] (/ (reduce + args) (count args)))
(def avg (operation avgEval))
(def pow (operation #(Math/pow %1 %2)))
(defn logEval [a b] (/ (Math/log (Math/abs b)) (Math/log (Math/abs a))))
(def log (operation logEval))
(def exp (operation #(Math/exp %)))
(def ln (operation #(Math/log %)))
(defn sumexpEval [& args] (reduce + (map #(Math/exp %) args)))
(def sumexp (operation sumexpEval))
(defn lseEval [& args] (Math/log (apply sumexpEval args)))
(def lse (operation lseEval))
(defn meansqEval [& args] (/ (reduce + (map #(* % %) args)) (count args)))
(def meansq (operation meansqEval))
(defn rmsEval [& args] (Math/sqrt (apply meansqEval args)))
(def rms (operation rmsEval))
(def arcTan (operation #(Math/atan %)))
(def arcTan2 (operation #(Math/atan2 %1 %2)))
(def funcSymbols {'const  constant, 'var variable, '+ add, '- subtract, '* multiply, '/ divide, 'negate negate, ;base
                  'sum    sum, 'avg avg, 'pow pow, 'log log, 'exp exp, 'ln ln, 'atan arcTan, 'atan2 arcTan2, ;extra
                  'sumexp sumexp, 'lse lse, 'meansq meansq, 'rms rms}) ;hard
(def _name (field :name))
(def _args (field :args))
(def _f (field :f))
(def toString (method :toString))
(def evaluate (method :evaluate))
(def Operation {:toString (fn [this] (str "(" (_name this) " " (clojure.string/join " " (map #(toString %) (_args this))) ")"))
                :evaluate (fn [this vars]
                            (apply (_f this) (map #(evaluate % vars) (_args this))))})
(def OperationConstructor (constructor (fn [this, f, name, args]
                                         (assoc this :f f :name name :args args)) Operation))
(def ConstantPrototype {:evaluate (fn [this _] (_args this))
                        :toString (fn [this] (str (_args this)))})
(def ConstantConstructor (constructor (fn [this, value] (assoc this :args value)) ConstantPrototype))
(defn Constant [value] (ConstantConstructor value))
(defn Add [& args] (OperationConstructor +, "+", args))
(defn Subtract [& args] (OperationConstructor -, "-", args))
(defn Multiply [& args] (OperationConstructor *, "*", args))
(defn Negate [& args] (OperationConstructor -, "negate", args))
(def Sum Add)
(defn Avg [& args] (OperationConstructor avgEval, "avg", args))
(defn Pow [& args] (OperationConstructor #(Math/pow %1 %2), "pow", args))
(defn Log [& args] (OperationConstructor logEval, "log", args))
(defn Exp [& args] (OperationConstructor #(Math/exp %), "exp", args))
(defn Ln [& args] (OperationConstructor #(Math/log %), "ln", args))
(defn Divide [& args] (OperationConstructor div, "/", args))
(defn Sumexp [& args] (OperationConstructor sumexpEval, "sumexp", args))
(defn LSE [& args] (OperationConstructor lseEval, "lse", args))
(defn Meansq [& args] (OperationConstructor meansqEval, "meansq", args))
(defn RMS [& args] (OperationConstructor rmsEval, "rms", args))
(defn ArcTan [& args] (OperationConstructor #(Math/atan %), "atan", args))
(defn ArcTan2 [& args] (OperationConstructor #(Math/atan2 %1 %2), "atan2", args))
(defn Sin [& args] (OperationConstructor #(Math/sin %), "sin", args))
(defn Cos [& args] (OperationConstructor #(Math/cos %), "cos", args))
(def VariablePrototype {:evaluate (fn [this variables] (variables (_name this)))
                        :toString (fn [this] (_name this))})
(def VariableConstructor (constructor (fn [this, name] (assoc this :name name)) VariablePrototype))
(defn Variable [name] (VariableConstructor name))
(def objSymbols {'const  Constant, 'var Variable, '/ Divide, 'negate Negate, '* Multiply, '- Subtract, '+ Add,
                 'sum    Sum, 'avg Avg, 'pow Pow, 'log Log, 'exp Exp, 'ln Ln, 'atan ArcTan, 'atan2 ArcTan2, ;extra
                 'sumexp Sumexp, 'lse LSE, 'meansq Meansq, 'rms RMS, 'sin Sin, 'cos Cos}) ;hard
(defn parse [input operationSet] (cond (number? input) ((operationSet 'const) input)
                                       (list? input) (apply (get operationSet (first input)) (mapv #(parse % operationSet) (rest input)))
                                       :else ((operationSet 'var) (str input))))
(defn parseFunction [expr] (parse (read-string expr) funcSymbols))
(defn parseObject [expr] (parse (read-string expr) objSymbols))
