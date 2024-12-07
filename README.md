# Java README

## Sommario
1. Inventario
2. Introduzione
3. Struttura
4. Funzionalità e comandi
5. Istruzioni per l'uso  
5.1 Guida alla creazione del file .jar  
5.2 Guida all'esecuzione dell'applicazione  

## 1. Inventario
 La directory principale è src, suddivisa a sua volta in un sistema di sottocartelle che contengono
al loro interno i file sorgente .java. In particolare il percorso main\java\org\javabasics contiene il codice dell'applicazione
principale. Allo stesso livello di main è inclusa una directory test, dove è presente un insimeme di sottocartelle in cui dei files .java dimostrano che l'ambiente di testing è preimpostato e funzionante.  
Il seguente project folder contiene 3 file di lettura .csv che sono salvati nella cartella assets, che si trova nell'apposito percorso src/main/resources. Questa struttura è in perfetta armonia con Apache Maven.

## 2. Introduzione
Il progetto java è mirato all'uso di funzionalità specifiche che consentono il controllo di un sistema dedito
alla gestione delle vendite di capi second-hand. Tra le funzionalità principali il sistema prevede oltre all'acquisto di un capo,
la restituzione dello stesso e l'aggiunta di nuovi utenti. L'interfacciamento con l'operatore avviene tramite terminale, dove
ciclicamente viene mostrata la lista delle operazioni disponibili. Inoltre tra le opzioni è possibile selezionarne una
che prevede la fine dell'esecuzione del programma (i.e. tasto '0').

L'architettura del progetto segue il pattern a tre livelli: Presentation, Business Logic e Data Access, rappresentate rispettivamente dai packages controller, service e model.

## 3. Struttura Operativa
 - START: la parte che avvia il sistema è contenuto in App.java.
 - BUSINESS LOGIC: il service contiene il singleton Service.java strutturato per l'esecuzione del programma in una maniera sicura e fruibile.  
 - PRESENTATION: altra parte fondamentale del sistema è il package controller, che contiene a sua volta la classe Controller.java, la vera e propria interfaccia con l'utente. Controller ha un constructor che integra la chiamata ad un metodo esterno per la formulazione dell'istanza di tipo Service.
 - DATA ACCESS: la business logic del singleton Service delega il data access al package model, facendo da tramite tra questo e il controller. In model è contenuto il modello operazionale del sistema con 5 classi principali, e un enum Operazione.java, destinato alla dichiarazione e definizione dei 5 metodi principali: panoramica, acquisto, restituzione, registrazione, e esportazione.

## 4. Funzionalità e comandi
Come anticipato l'applicazione prevede l'interazione con l'utente attraverso l'inserimento da tastiera delle scelte desiderate, o dei dati da fornire al sistema.
Il tutto tramite l'interfaccia del terminale. Qui di seguito sono elencate in ordine le opzioni del menu principale, che si apre all'avvio
e si reitera finchè l'utente non decida di terminare il programma, inviando '0'.
 - Tasto 1 (PANORAMICA): La prima operazione prevede la visualizzazione di tutti i capi all'interno del sistema, disponibili o meno. I file sono stati caricati all'avvio del programma in automatico, ovviamente.
 - Tasto 2 (ACQUISTO): a seguito degli input corretti e al termine del processo senza errori viene creata una classe vendita e aggiunta all'elenco delle vendite.
 - Tasto 3 (RESTITUZIONE): operazione per la restituzione di un capo già acquistato in precedenza.
 - Tasto 4 (REGISTRAZIONE): aggiunta di un nuovo utente.
 - Tasto 5 (ESPORTAZIONE): salvataggio di un file .csv aggiornato con i capi disponibili e intitolato con la data corrente.
 - Tasto 0 (TERMINAIONE): all'invio del tasto zero nel contesto del menu principale il programma termina su richiesta dell'operatore.

 Come già brevemente anticipato la lettura dei files .csv inziali, contenuti in src/main/resources/assets/, occorre all'avvio del programma in automatico. Nello specifico è delegata al package model in accordo con la metodologia di data access del progetto.

## 5. Istruzioni per l'uso
Per fare il build del progetto sarà adoperato di seguito l'uso di Maven, che semplifica e velocizza il processo. Lo stesso Maven verrà utilizzato per creare il file application.jar, il tutto con una piccolissima linea di codice, visto che il project object model (i.e. pom.xml) è già  stato settato e pronto all'uso.
Alternativamente, per chi fosse curioso di conoscere anche un altro modo di creare un file JAR, si possono consultare le indicazioni sul sito di [Oracle](https://docs.oracle.com/javase/tutorial/deployment/jar/build.html).

 ### 5.1 Guida alla creazione del file .jar
Per creare il file .jar dal nome application.jar, specificato nel pom.xml, basta usando il terminale spostarsi nella directory del progetto, e usare il comando di Maven 'mvn clean install'. Questo creerà l'apposito file application.jar, come da specifiche del project object model incluso nel progetto, oltre a tutti i .class.  
In alternativa si possono chiamare i seguenti comandi individualmente:
 - mvn clean: Rimuove tutti i file generati dalle build precedenti, o autogenerati dall'IDE.
 - mvn compile: Compila il codice sorgente del progetto dando luogo al bytecode relativo (i.e. .class).
 - mvn test compile: Compila il codice e crea il file .jar.
 - mvn package

 N.B: Se non si dispone di Maven o di JDK, è necessario installare separatamente un pacchetto JDK e Maven. Assicurarsi che la variabile d'ambiente JAVA_HOME punti alla directory di installazione del JDK, creare una variabile d'ambiente MAVEN_HOME che punti alla directory di Maven estratta e aggiungere il percorso MAVEN_HOME\bin alla variabile d'ambiente PATH per poter eseguire i comandi Maven da qualsiasi directory.
 Oppure puoi installare Maven direttamente dall'IDE di tuo gradimento (e.g. IntelliJ IDEA o VSC).

 ### 5.2 Guida all'esecuzione del file .jar
 L'esecuzione dell'applicazione è a questo punto il passo più banale, e può avvenire tanto da terminale quanto da IDE.
 Nel nostro caso se volessimo operare da terminale basterà usare la stringa 'java -jar application.jar' direttamente dalla directory in cui si trova il file (i.e. target). Altrimenti da IDE si può aprire l'intero folder come un progetto e lanciare il build, così da proseguire nell'inserimento dei dati da terminale (integrato o esterno).
