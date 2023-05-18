prime(N) :- \+ composite(N).
up_to(I, X, N) :- X =< N, assert(composite(X)), X1 is X + I, up_to(I, X1, N).
level(I, N) :- I > N, !.
level(I, N) :- \+ composite(I), X is I * I, up_to(I, X, N).
level(I, N) :- I * I < N, I1 is I + 1, level(I1, N).
init(MAX_N) :- level(2, MAX_N).
prime_divisors(N, Divisors) :- findall(R, prime_divisor(N, R), Divisors).
prime_divisor(N, I) :- create_list(N, 2, I).
create_list(N, I, I) :- 0 is mod(N, I).
create_list(N, I, X) :- I < N, 0 is mod(N, I), !, N1 is N // I, create_list(N1, I, X).
create_list(N, I, X) :- I < N, I1 is I + 1, create_list(N, I1, X).
square_divisors(N, Divisors) :- N2 is N * N, prime_divisors(N2, Divisors).