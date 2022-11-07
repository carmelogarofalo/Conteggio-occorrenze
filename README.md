# Conteggio-occorrenze

Il programma conta le occorrenze dei caratteri alfabetici (lettere dalla "A" alla "Z" e dalla "a" alla "z") in un insieme di file di testo. 
Il programma prende in input una serie di percorsi di file testuali e per ciascuno di essi conta le occorrenze dei caratteri, ignorando eventuali caratteri non alfabetici (come per esempio le cifre da 0 a 9). 
Per ogni file, il conteggio viene effettuato da un apposito task e tutti i task attivati vengono gestiti tramite un pool di thread. 
I task registrano i loro risultati parziali all'interno di una ConcurrentHashMap. 
Prima di terminare, il programma stampa su un apposito file di output il numero di occorrenze di ogni carattere. 

Il file di output contiene una riga per ciascun carattere ed è formattato come segue:

- carattere 1,numero occorrenze
- carattere 2,numero occorrenze
- ...
- carattere N,numero occorrenze
