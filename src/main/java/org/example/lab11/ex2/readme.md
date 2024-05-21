## Concluzii

Pentru numere f. mici (10,100,...), rezultatele pot varia si diferenta de timp e nesesizabila (3ms vs 1ms vs 1 ms intr-un run, respectiv 1 ms 6 ms 1 ms in altul).

Pentru numere mai mari (1e7 de ex), se remarca faptul ca ParallelStream este mai rapid decat CompletableFuture & Thread pt ca in ParallelStream, task-urile sunt impartite automat, pe cand, pt CompletableFuture & Thread, trebuie sa impartim manual task-urile.

### Rezultate pentru 10 numere

Thread: 1 ms

parallelStream: 4 ms

CompletableFuture: 4 ms 

### Rezultate pentru 1000 numere

Thread: 1 ms

parallelStream: 0 ms 

CompletableFuture: 1 ms

### Rezultate pentru 10000 numere

Thread: 5 ms

parallelStream: 1 ms

CompletableFuture: 1 ms 

### Rezultate pentru 10000000 numere

Thread: 342 ms

parallelStream: 49 ms

CompletableFuture: 349 ms

