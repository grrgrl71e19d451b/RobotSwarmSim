Titolo del Progetto
RobotSwarmSim: Una Libreria Java con Applicazione Terminale per la Simulazione di uno Sciame di Robot e Ambiente Virtuale Interattivo.

Data
23 Gennaio 2024

Premessa
RobotSwarmSim è un progetto che sviluppa una libreria Java per la simulazione di robot in uno spazio bidimensionale. Focalizzandosi su interazioni complesse, la libreria consente ai robot di esplorare liberamente, sperimentando comportamenti collettivi. Dotati di percezione ambientale e capacità di movimento, i robot reagiscono a varie condizioni, operando in aree che influenzano il loro comportamento.

Specifiche Tecniche
Il progetto RobotSwarmSim è stato realizzato utilizzando Java versione 19, una scelta che garantisce prestazioni ottimali e compatibilità con le più recenti funzionalità del linguaggio. L'adozione di Gradle come sistema di gestione del progetto contribuisce a mantenere il codice organizzato, pulito e facilmente scalabile. Per garantire la massima affidabilità e la manutenibilità del codice, il progetto include una suite di test completi. 















Responsabilità assegnate alle Classi
1.	Classe App: Questa classe agisce come il punto di partenza del programma. È responsabile dell'interpretazione dei parametri della linea di comando, caricando sia l'ambiente che i comandi dei robot. Una volta che questi elementi sono stati configurati, avvia la simulazione. La classe App è fondamentale perché collega l'input dell'utente con il sistema di simulazione, assicurando che tutte le componenti necessarie siano inizializzate e pronte all'uso.
2.	Classe RobotSimulator: Agisce come il motore centrale della simulazione, eseguendo la sequenza di comandi sui robot. Coordina le azioni dei robot, gestisce la loro interazione con l'ambiente e controlla il flusso temporale della simulazione. È questa classe che rende dinamica la simulazione, permettendo ai robot di agire e reagire in base agli input forniti.
3.	Classe Robot (implementa l'interfaccia Entity): Rappresenta l'entità robotica individuale all'interno della simulazione. Ogni robot gestisce il proprio stato fisico e le proprie azioni, agendo in base ai comandi ricevuti. La classe Robot è fondamentale per modellare le capacità e le caratteristiche dei singoli robot.
4.	Classe CommandsParser: Questa classe svolge un ruolo cruciale nell'interpretazione dei comandi forniti in input. Converte le stringhe di testo in azioni concrete che possono essere eseguite dai robot nella simulazione. È responsabile per assicurare che i comandi siano correttamente analizzati e trasformati in una forma che il sistema può utilizzare efficacemente.
5.	Classi di Comandi (es. SignalCommand, MoveCommand, ecc.): Queste classi sono progettate per definire azioni specifiche che i robot possono eseguire nella simulazione. Ogni classe comando rappresenta un tipo diverso di azione, permettendo una varietà di interazioni e comportamenti all'interno della simulazione. Sono essenziali per trasformare gli input testuali in azioni concrete all'interno dell'ambiente simulato.
6.	Classe Environment: Gestisce l'ambiente di simulazione, che è lo spazio virtuale in cui i robot si muovono e interagiscono. È incaricata di tracciare e mantenere l'organizzazione delle varie aree, nonché di fornire un contesto in cui i robot possono operare. Questa classe è essenziale per modellare le condizioni e le limitazioni dell'ambiente in cui avviene la simulazione.
7.	Classi Circle e Rectangle (implementano l'interfaccia Area): Queste classi definiscono specifiche aree geometriche all'interno dell'ambiente di simulazione. Ogni area ha un impatto diretto sul comportamento dei robot, influenzando il loro movimento e le loro azioni. Queste classi sono cruciali per aggiungere complessità e realismo all'ambiente simulato.

Istruzioni 
Compilazione: Per compilare il programma, eseguire il seguente comando nella directory principale del progetto: gradle build. Questo comando compila il codice sorgente e prepara il programma per l'esecuzione.




Esecuzione di "RobotSwarmSim"
Per eseguire "RobotSwarmSim", è necessario usare il comando Gradle con una sintassi specifica per passare gli argomenti al programma. Ecco diversi scenari d'uso:
1. Caricamento da File: Per caricare sia i dati dell'ambiente che i comandi dei robot da file, utilizzare:
gradle run --args="1 10 file 'C:/environment.txt' file 'C:/robot_commands.txt' 2" , dove : 
environment.txt: Contiene le definizioni delle aree (es. RECTANGLE x y width height per ogni riga).
robot_commands.txt: Contiene i comandi per i robot, uno per riga (es. MOVE, SIGNAL).

2. Caricamento da Stringa: Per fornire direttamente i dati dell'ambiente e i comandi dei robot come stringhe, utilizzare:
gradle run --args="1 10 string 'Z2 CIRCLE 5 6 5\nZ1 RECTANGLE 1 1 2 2' string 'SIGNAL Z1\nMOVE 1 0 1\nCONTINUE 5\nUNSIGNAL Z1\nSTOP' 1" 

3. Caricamento Misto (Stringa e File): Per un approccio misto, ad esempio, caricando l'ambiente da un file e i comandi dei robot come stringa, utilizzare:
gradle run --args="1 10 file 'C:/environment.txt' string 'SIGNAL Z1\nMOVE 1 0 1\nCONTINUE 5\nSTOP\nFOLLOW Z1 3 5\nUNSIGNAL Z1\nMOVE RANDOM 1 2 1 3 5\nSTOP' 2" 

4. Utilizzo di Comandi Complessi: Il programma supporta vari comandi complessi. Ecco alcuni esempi:
Continue:
gradle run --args="1 10 string 'Z2 CIRCLE 5 6 5\nZ1 RECTANGLE 1 1 2 2' string 'SIGNAL Z1\nMOVE 1 0 1\nCONTINUE 5\nUNSIGNAL Z1\nSTOP' 1" 
Follow:
gradle run --args="1 10 file 'Z2 CIRCLE 5 6 5\nZ1 RECTANGLE 1 1 2 2' string 'SIGNAL Z1\nMOVE 1 0 1\nSTOP\nFOLLOW Z1 90 5\nUNSIGNAL Z1\nMOVE RANDOM 1 2 1 3 5\nSTOP' 2" 
Until:
gradle run --args="2 20 string 'Z1 RECTANGLE 25 -40 80 80\nZ2 CIRCLE 5 6 15' string 'SIGNAL Z1\nUNTIL Z1\nMOVE 1 0 5\nDONE\nUNSIGNAL Z1' 1" 
Forever:
gradle run --args="2 20 string 'Z2 CIRCLE 5 6 50\nZ1 RECTANGLE 1 
1 80 80' string 'SIGNAL Z1\nDO FOREVER\nMOVE 1 0 1\nSTOP\nDONE' 1" ```
Repeat:
gradle run --args="2 14 string 'Z2 CIRCLE 5 6 50\nZ1 RECTANGLE 1 1 80 80' string 'SIGNAL Z1\nREPEAT 2\nMOVE 1 0 1\nSTOP\nDONE\nUNSIGNAL Z1\nMOVE 1 0 1' 1" 
Gestione degli Errori
Se i parametri non vengono inseriti correttamente, il programma genererà delle eccezioni con messaggi descrittivi per aiutare a identificare e correggere l'errore. Ecco una lista dei controlli effettuati: 
Verifica se sono stati forniti almeno 7 argomenti nella riga di comando.
1.	Verifica che il tempo per istruzione (timePerInstruction) sia un numero positivo.
2.	Verifica che il tempo di simulazione (simulationTime) sia un numero positivo.
3.	Verifica che il tipo di ambiente (environmentType) sia "file" o "string".
4.	Verifica che il percorso dell'ambiente (environmentPath) sia valido se il tipo di ambiente è "file".
5.	Verifica che il tipo di programma (programType) sia "file" o "string".
6.	Verifica che il percorso del programma (programPath) sia valido se il tipo di programma è "file".
7.	Verifica che il numero di robot (numberOfRobots) sia non negativo.
La classe genera eccezioni specifiche con messaggi descrittivi per guidare l'utente nell'individuazione e nella correzione di eventuali errori nei parametri forniti. Pertanto, è importante assicurarsi che tutti i parametri siano inseriti correttamente e rispettino i requisiti specificati.


Riscontro in Tempo Reale Durante l'Esecuzione
Durante l'esecuzione di "RobotSwarmSim", il terminale visualizza in tempo reale lo stato dei robot e le azioni eseguite. A ogni "tick" del processore, definito dall'intervallo di tempo specificato negli argomenti di input (per esempio, ogni 2 secondi se l'intervallo è impostato su 2), vengono forniti dettagli aggiornati relativi a ciascun robot. Questi aggiornamenti includono:
•	L'identificativo unico del robot.
•	Le aree in cui il robot è posizionato.
•	La sua posizione attuale con coordinate X e Y.
•	La direzione e la velocità del robot.
•	I segnali attivi e i comandi eseguiti.

Un output tipico inizia mostrando lo stato di tutti i robot presenti nell'ambiente allo step 0, includendo informazioni come la loro posizione, direzione, velocità e segnali attivi. Successivamente, a partire dallo step 1, vengono eseguiti i comandi ad ogni step per ciascun robot, e l'esecuzione di ciascun comando implica la stampa aggiornata delle informazioni relative a ciascun robot, comprese la sua posizione, direzione, velocità e segnali attivi. Quando la lista di comandi è stata eseguita, l'output mostra il messaggio 'All robots have completed their commands', riportando l'ultimo aggiornamento delle informazioni di ciascun robot.

Test JUnit Il progetto "RobotSwarmSim" adotta una strategia di test intensiva, con test JUnit che coprono oltre il 90% del codice e il 100% delle classi principali. Ciò garantisce non solo che le funzionalità chiave siano adeguatamente testate, ma anche che il codice rimanga robusto e affidabile nel tempo.
Per eseguire i test, utilizzare il comando:
gradle test 
Questo comando avvia tutti i test JUnit definiti nel progetto, fornendo un resoconto dettagliato di tutti i test superati, falliti o saltati.

Conclusione
Il progetto "RobotSwarmSim" è un esempio di programmazione orientata agli oggetti e sviluppo software avanzati. Grazie ai test JUnit, offre affidabilità e facilità di manutenzione.

