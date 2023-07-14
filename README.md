Progetto finale di gruppo del corso Java Full Stack Developer di IDM Consulting.

#Il progetto

Le finalità del progetto sviluppato nella durata del corso sono quelle di fornire ad un utente la possibilità di interagire con un sistema di creazione di treni dopo una fase di autenticazione.  Il treno creato verra’ inserito all’interno di un Database con foreign key id_utente che andra’ a collegare il treno con l’utente che lo ha creato. L’utente inoltre potra’ accedere alla lista di tutti i Treni che ha creato precedentemente.

#Strumenti di sviluppo
	•	L’ambiente di sviluppo scelto e’ l’ IDE Eclipse molto utile per lo sviluppo con linguaggio Java
	•	Server Tomcat versione 9.0 con l’aiuto di Eclipse
	•	MySQL Workbench per lo sviluppo delle tabelle nel Database
	•	HTML,CSS,Javascript per la parte Front End
	•	Utilizzo di Maven per la gestione dei progetti dei vari file JAR
	•	Framework Spring MVC  per la realizzazione del progetto Web
	•	JPA per la gestione della persistenza dei dati sul database attraverso la rappresentazione e il mantenimento su database relazionale di un sistema di oggetti Java.

#Tecnologie utilizzate:
	•	i JPA e l’ EntityManagerFactory  che viene utilizzato per creare una connessione con il Database MySQL
	•	Il pattern DAO è usato per separare la logica di business dalla logica di acceso ai dati di un Utente
	•	Pattern DTO usato per trasferire dati in modo semplice e senza logica complicata
	•	Un controllo lato Server della presenza o meno dell’ oggetto Utente con username e password uguali a username e password inseriti dall’utente.
	•	Uso di Spring MVC che grazie al Controller ,a seconda del risultato della ricerca nel Database MySQL, ci portera’ alla Home in caso di successo o al Login in caso contrario.
	•	Lato Frontend utilizzo di HTML5 e CSS che rendono le pagine Responsive,  uno script in Javascript per gli effetti visivi e base  JSP.
#Home
All’interno della Home troviamo una barra di navigazione Responsive ottenuta con CSS e Javascript che riporta i Link delle pagine : CreazioneTreno e ListaTreni e il nome dell’ utente loggato.(Home prima del login e registrazione).

#Fase di costruzione del Treno
Per la costruzione della sigla del Treno utilizziamo il pattern Builder con al suo interno un controllo degli errori di inserimento da parte dell’utente. Per ogni possibile errore e’ stata creata una classe che estendono la RunTimeException e nel momento in cui viene lanciata c’e’ anche un metodo ControllaSigla che ricorsivamente corregge la Sigla seguendo le regole.
In caso la Sigla inserita fosse corretta utiliziamo JPA e l’EntityManager per prelevare la sigla e inserirla all’interno di una tabella MySQL; Spring MVC trasportiamo l’attributo username tra le JSP per poi collegare l’id dell’utente che crea il Treno con il Treno creato.
Il pattern DAO è usato per separare la logica di business dalla logica di acceso ai dati del Treno.
Pattern DTO e’ usato per trasferire dati in modo semplice e senza logica complicata al Database e tramite Annotation di JPA andiamo a mappare i dati necessari per la tabella nel Database.

#Organizzazione del Lavoro
Abbiamo utilizzato la metodologia Agile SCRUM in questa maniera per ogni giornata:
	•	Definizione delle Users Story
	•	Dalle 9:00 alle 9:30 sistemazione del lavoro personale
	•	Meeting  alle 9:30 per fare il punto della situazione e organizzare la giornata lavorativa ponendo degli obiettivi da raggiungere in giornata e la suddivisione del lavoro
	•	14:15 Risoluzione di Errori se presenti tra i membri del gruppo e aggiornamento compiti
	•	17:45 Controllo funzionamento progetto , merge sul main di git e controllo del superamento degli obiettivi