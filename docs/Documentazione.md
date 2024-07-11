# Città dei Coboldi

## Indice

1. [Introduzione](#1-introduzione)

# 1. Introduzione

## Cerignolus: Città dei Coboldi

Questo progetto è stato sviluppato come prova per il conseguimento del corso di **Metodi Avanzati di Programmazione**, 
che punta a formare gli studenti sulla programmazione ad oggetti e a formarsi individualmente su nuovi argomenti
richiedendone l'utilizzo per la realizzazione di questo progetto.

Nello specifico il progetto richiede la realizzazione di un'avventura prevalentemente testuale, con l'aggiunta di una **Graphic User Interface**
per la visualizzazione delle informazioni principali del gioco.

Le avventure testuali sono una tipologia di videogioco che si incentra molto sulla narrazione e sulla risoluzione di enigmi,
infatti non si disponeva di engine di grafica per la realizzazione di texture e animazioni, nonostante ciò il genere ha 
avuto grande successo e si trattava di una novità, nonché i primi passi verso ciò che abbiamo oggi come giochi di ruolo.

### Caratteristiche Principali

- **Interfaccia Testuale**: l'interazione avviene tramite comandi scritti, "prendi mantello", "ispeziona" ecc...
- **Descrizioni Dettagliate**: il gioco descrive le scene, gli oggetti e le azioni attraverso testi ed un'immagine di sfondo
- **Minigiochi**: all'interno del gioco sono presenti minigiochi come il **Blackjack** o la ricerca dei pezzi dell'**Auto**

### Trama

Ispirato ad una storia vera, il gioco comincia con il protagonista che torna nella sua casa in campagna guidando la sua macchina e la parcheggia momentaneamente fuori casa
prima di riprendere a guidare. Finito ciò che doveva fare in casa, il protagonista torna fuori e si accorge che la sua macchina stava venendo rubata da un gruppo di persone 
incappucciate. Seguendo le tracce dell'auto si ritrova in un posto sconosciuto, e appena scorge un cartello con scritto "Cerignola", un buco nel pavimento si forma sotto i suoi piedi
e sprofonda in un mondo sotterraneo e buio, ed una scritta gli appare dinanzi: "Cerignolus: Città dei Coboldi".
L'obiettivo del nostro è quello di riuscire ad uscire dalla caverna e tornare a casa riprendendosi la sua macchina ma come vedremo potrà finire in molti modi.

### Struttura del Progetto

- **Parser**: il giocatore interagisce attraverso comandi testuali che devono essere interpretati dal gioco, il parser è dunque una componente fondamentale
- **Thread**: si tratta di un progetto che ricchiede l'esecuzione contemporanea di più elementi insieme, come ad esempio il timer, la musica o il socket server
- **File di Configurazione**: il gioco include la possibiltà di salvare una partita in un dato momento, questo viene fatto serializzando gli oggetti nello stato a runtime e riportandoli in un file JSON, poi caricato per permettere di riprendere una partita da quel momento
- **Database**: il gioco registra i tempi di gioco dei giocatori ed i loro nomi su un database **H2**
- **Socket/REST**: il gioco include un server socket, sulla porta 4200 per una piccola panoramica sugli sviluppatori collegandosi all'account Github di ognuno
- **API**: il gioco per l'implementazione dei minigiochi utilizza API esterne come quella di **Deck of Cards** per il Blackjack
- **GUI**: nonostante il gioco sia testuale, è stata implementata una GUI per visualizzare le informazioni principali del gioco.

#### [Ritorna all'Indice](#indice)

# 2. Progettazione

La fase di progettazione si concentra sul definire le classi e le interazioni tra di esse, in modo da avere una visione chiara di come il progetto sarà strutturato

Nello specifico si rappresenta la parte più significativa del progetto 

## Diagramma delle Classi

Di seguito viene rappresentata la parte che gestisce la logica del videogioco, ovvero le classi che rappresentano le entità del gioco e le loro interazioni
Vengono quindi esclusi i componenti grafici, le classi di supporto e le implementazioni di minigiochi o degli argomenti esterni


![Class_Diagram_1.png](img/Class_Diagram_1.png)

![Class_Diagram_2.png](img/Class_Diagram_2.png)

Ho omesso l'implementazione di ognuna delle "AvailableRooms" e "Minigames" per non appesantire il diagramma, ma sono ovviamente presenti nel progetto

### Descrizione delle Classi

1. **Game**: Gestisce l'interazione con le stanze e i minigiochi, e la visualizzazione delle informazioni;
2. **Item**: Rappresenta un oggetto di gioco generico;
3. **Inventory**: Gestisce il denaro e un insieme di oggetti posseduti dal giocatore;
4. **Parser**: Analizza i comandi del giocatore e li redireziona al giusto manager;
5. **ParserOutput**: Contiene il comando analizzato e l'oggetto interessato;
6. **Room**: Classe astratta per definire la struttura generale di una stanza;
7. **RoomInteractionResult**: Rappresenta il risultato di un'interazione con una stanza;
8. **RoomsMap**: Tiene traccia della stanza corrente e gestisce il movimento tra stanze;
9. **RoomPath**: Gestisce i percorsi tra le stanze e la loro accessibilità;
10. **RoomInteractionResultType**: Enumera i tipi di risultati delle interazioni nelle stanze.
11. **AvailableRooms**: Collezione delle stanze disponibili nel gioco, omesse le implementazioni;
12. **MiniGame**: Classe astratta per definire il comportamento generale di un minigioco;
13. **MiniGameInteraction**: Rappresenta il risultato di un'interazione con un minigioco;
14. **MiniGameInteractionType**: Enumera i tipi di risultati delle interazioni nei minigiochi.
15. **VariousMiniGame**: Collezione dei minigiochi disponibili, omesse le implementazioni;
16. **Command**: Definisce un comando di gioco con una stringa e alias;
17. **GameCommandResult**: Rappresenta il risultato di un comando eseguito nel gioco;
18. **Time Manager**: Gestisce il tempo di gioco e la visualizzazione del tempo trascorso;

#### [Ritorna all'Indice](#indice)

## 3. Specifiche Algebriche
Due delle strutture dati più utilizzate nel nostro progetto sono la **Lista** e la **Mappa**, in questa sezione verranno presentate le specifiche algebriche di entrambe.

### 3.1 - Specifica algebrica della Lista
La lista è una struttura dati che permette di memorizzare e recuperare informazioni sfruttando l'indice di posizione degli elementi contenuti.

#### Specifica sintattica

**Tipi**
- `List`, `Element`, `Integer`, `Boolean`

**Operatori**
- `newList() -> List`: Crea una nuova lista vuota
- `add(List, Element, Integer) -> List`: Aggiunge un elemento alla lista nella posizione specificata
- `isEmpty(List) -> Boolean`: Restituisce `true` se la lista è vuota altrimenti `false`
- `getSize(List) -> Integer`: Restituisce l'ultima posizione occupata da un elemento
- `getIndex(List, Element) -> Integer`: Restituisce la posizione dell'elemento specificato
- `getElement(List, Integer) -> Element`: Restituisce l'elemento nella posizione specificata
- `remove(List, Integer) -> List`: Rimuove dalla lista l'elemento nella posizione specificata
- `contains(List, Element) -> Boolean`: Restituisce `true` se l'elemento specificato è contenuto nella lista

Si noti come `Element` è un tipo generico, che può essere sostituito con qualsiasi altro tipo di dato. `Integer` e `Boolean` invece, sono tipi ausiliari alla definizione della specifica algebrica della lista.

#### Osservazioni e Costruttori

| Metodo               | Costruttore         | Osservazioni                                                             |
|----------------------|---------------------|--------------------------------------------------------------------------|
| `isEmpty(l')`        | `newList`           | `true`                                                                   |
| `isEmpty(l')`        | `add(l, el, id)`    | `false`                                                                  |
| `getSize(l')`        | `newList`           | `error`                                                                  |
| `getSize(l')`        | `add(l, el, id)`    | `if isEmpty(l) then 1 else getSize(l) + 1`                               |
| `getIndex(l', el')`  | `newList`           | `error`                                                                  |
| `getIndex(l', el')`  | `add(l, el, id)`    | `if el = el' then id else getIndex(l, el')`                              |
| `getElement(l', id')`| `newList`           | `error`                                                                  |
| `getElement(l', id')`| `add(l, el, id)`    | `if id = id' then el else getElement(l, id')`                            |
| `remove(l', id')`    | `newList`           | `error`                                                                  |
| `remove(l', id')`    | `add(l, el, id)`    | `if id = id' then l else add(remove(l, id'), el)`                        |
| `contains(l', el')`  | `newList`           | `false`                                                                  |
| `contains(l', el')`  | `add(l, el, id)`    | `if el = el' then true else contains(l, el')`                            |

#### Specifica semantica

- **DECLARE**
  - `l`, `l'`: `List`
  - `el`, `el'`: `Element`
  - `id`, `id'`: `Integer`

- **OPERATIONS**
  - `isEmpty(newList) = true`
  - `isEmpty(add(l, el, id)) = false`
  - `getSize(add(l, el, id)) = if isEmpty(l) then 1 else getSize(l) + 1`
  - `getIndex(add(l, el, id), el') = if el = el' then id else getIndex(l, el')`
  - `getElement(add(l, el, id), id') = if id = id' then el else getElement(l, id')`
  - `remove(add(l, el, id), id') = if id = id' then l else add(remove(l, id'), el)`
  - `contains(newList, el') = false`
  - `contains(add(l, el, id), el') = if el = el' then true else contains(l, el')`

#### Specifica di restrizione

- **RESTRICTIONS**
  - `getSize(newList) = error`
  - `getIndex(newList, el') = error`
  - `getElement(newList, id') = error`
  - `remove(newList, id') = error`

### 3.2 - Specifica algebrica della Mappa
La mappa è una struttura dati che associa una chiave ad un valore, permettendo di memorizzare e recuperare informazioni in modo efficiente.

#### Specifica sintattica

**Tipi**
- `Map`, `Key`, `Value`, `Boolean`, `Integer`

**Operatori**
- `newMap() -> Map`: Crea una nuova mappa vuota
- `isEmpty(Map) -> Boolean`: Restituisce `true` se la mappa è vuota, `false` altrimenti
- `put(Map, Key, Value) -> Map`: Aggiunge una coppia chiave-valore alla mappa, o, se già presente, ne aggiorna il valore
- `get(Map, Key) -> Value`: Restituisce il valore associato alla chiave specificata
- `containsKey(Map, Key) -> Boolean`: Restituisce `true` se la chiave specificata è presente nella mappa
- `containsValue(Map, Value) -> Boolean`: Restituisce `true` se il valore specificato è presente nella mappa
- `remove(Map, Key) -> Map`: Rimuove la chiave ed il valore associato ad essa dalla mappa
- `size(map) -> Integer`: Restituisce il numero di coppie chiave-valore presenti nella mappa

#### Osservazioni e Costruttori

| Metodo               | Costruttore         | Osservazioni                                                                 |
|----------------------|---------------------|------------------------------------------------------------------------------|
| `isEmpty(m')`        | `newMap`            | `true`                                                                       |
| `isEmpty(m')`        | `put(m, k, v)`      | `false`                                                                      |
| `containsKey(m', k')`| `newMap`            | `false`                                                                      |
| `containsKey(m', k')`| `put(m, k, v)`      | `if k = k' then true else containsKey(m, k')`                                |
| `containsValue(m', v')`| `newMap`          | `false`                                                                      |
| `containsValue(m', v')`| `put(m, k, v)`    | `if v = v' then true else containsValue(m, v')`                              |
| `get(m', k')`        | `newMap`            | `error`                                                                      |
| `get(m', k')`        | `put(m, k, v)`      | `if k = k' then v else get(m, k')`                                           |
| `remove(m', k')`     | `newMap`            | `error`                                                                      |
| `remove(m', k')`     | `put(m, k, v)`      | `if k = k' then m else put(remove(m, k'), k, v)`                             |
| `size(m')`           | `newMap`            | `0`                                                                          |
| `size(m')`           | `put(m, k, v)`      | `if isEmpty(m) then 1 else size(m) + 1`                                      |

#### Specifica semantica

- **DECLARE**
  - `m`, `m'`: `Map`
  - `k`, `k'`: `Key`
  - `v`, `v'`: `Value`

- **OPERATIONS**
  - `isEmpty(newMap) = true`
  - `isEmpty(put(m, k, v)) = false`
  - `containsKey(newMap, k') = false`
  - `containsKey(put(m, k, v), k') = if k = k' then true else containsKey(m, k')`
  - `containsValue(newMap, v') = false`
  - `containsValue(put(m, k, v), v') = if v = v' then true else containsValue(m, v')`
  - `get(put(m, k, v), k') = if k = k' then v else get(m, k')`
  - `remove(put(m, k, v), k') = if k = k' then m else put(remove(m, k'), k, v)`
  - `size(newMap) = 0`
  - `size(put(m, k, v)) = size(m) + 1`

#### Specifica di restrizione

- **RESTRICTIONS**
  - `get(newMap, k') = error`
  - `remove(newMap, k') = error`


#### [Ritorna all'Indice](#indice)

# 4. Applicazione Argomenti del Corso

## File

I file sono il modo attraverso il quale il calcolatore salva i dati in modo permanente, permettendo di memorizzare grandi quantità di dati e di accederci e scriverci

In Java, i file sono gestiti attraverso la classe Files, che fornisce metodi per la gestione dei file

Nel nostro caso i file vengono utilizzati per salvare e caricare le partite, i tempi di gioco e le informazioni sui giocatori
così da permettere ai giocatori di salvare una partita che si vuole lasciare in sospeso e riprenderla in seguito

Nella fattispecie è stato usato il formato **JSON** per salvare i dati, in quanto è un formato molto flessibile e leggibile, e permette di salvare oggetti complessi in modo semplice,
inoltre l'interazione con la classe "gson" permette di serializzare e deserializzare gli oggetti in modo molto semplice

### Applicazione

- Per la riproduzione della musica
- Per serializzare gli oggetti importanti all'interno del gioco, come l'inventario, le stanze e gli oggetti, si utilizza il seguente metodo:
```java
public static void serialize(Game game, String time) {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Room.class, new RoomDeserializer())
                .registerTypeAdapter(Item.class, new ItemDeserializer())
                .create();

        GameState gameState = new GameState(game.getInventory(), time, game.getCurrentRoomMap());
        String json = gson.toJson(gameState);
        String filePath = "src/main/resources/saves/" + game.getPlayerName() + "-" + (GameSave.getNumberOfUserSave(game.getPlayerName()) + 1) + ".json";

        try {
            Files.write(Paths.get(filePath), json.getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
```

- Per deserializzare il file json riportando gli oggetti nello stato in cui si trovavano al momento del salvataggio, si utilizza il seguente metodo:
```java
public static GameState deserialize(Path filePath) {
        try {
            String json = new String(Files.readAllBytes(filePath));
            Gson gson = new GsonBuilder()
                    .registerTypeAdapter(Room.class, new RoomDeserializer())
                    .create();
            
            return gson.fromJson(json, GameState.class);
        } catch (IOException e) {
            throw new RuntimeException("Error during JSON reading", e);
        }
    }
```

Sono stati utilizzati dei GsonBuilder() per registrare dei deserializzatori
personalizzati per le classi Room e Item che sono costruiti nel seguente modo

```java
@Override
public Room deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        JsonObject jsonObject = jsonElement.getAsJsonObject();

        String roomName = jsonObject.get("name").getAsString();
        Class roomClass = Room.class;

        switch (roomName) {
            case "corridoio" -> roomClass = HallwayRoom.class;
            case "circuito" -> roomClass = CircuitRoom.class;
            case "fucine" -> roomClass = ForgeRoom.class;
            case "palazzo" -> roomClass = PalaceEntryRoom.class;
            case "generatore" -> roomClass = PowerHouseRoom.class;
            case "taverna" -> roomClass = PubRoom.class;
            case "spiazzale" -> roomClass = SquareRoom.class;
            case "caverna" -> roomClass = StartingRoom.class;
            case "trono" -> roomClass = ThroneRoom.class;
        }

        return jsonDeserializationContext.deserialize(jsonElement, roomClass);
    }
    
@Override
public Item deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
  JsonObject jsonObject = jsonElement.getAsJsonObject();

  String itemName = jsonObject.get("name").getAsString();
  Class itemClass = Item.class;

  switch (itemName) {
    case "birre" -> itemClass = Beers.class;
    case "carrozzeria" -> itemClass = CarBody.class;
    case "mantello" -> itemClass = Cloak.class;
    case "motore" -> itemClass = Engine.class;
    case "maglio" -> itemClass = FireMaul.class;
    case "GinMoncello" -> itemClass = GinMoncello.class;
    case "volante" -> itemClass = SteeringWheel.class;
  }

  return jsonDeserializationContext.deserialize(jsonElement, itemClass);
}
```

Implementando l'interfaccia JsonDeserializer e usando questi metodi nel TypeAdapter che permette di distinguere e trattare in modo diverso gli oggetti che Gson tratterebbe come uguali

Ci siamo inoltre serviti di una classe che contenesse lo stato del gioco in un dato momento **GameState** e di una classe che gestisce la comunicazione coi file **GameSave**
così da poter accedere ad informazioni utili quali il nome dei salvataggi o il numero di salvataggi di un determinato giocatore

#### [Ritorna all'Indice](#indice)

## Thread

I thread sono utilizzati per eseguire operazioni in modo concorrente, permettendo di sfruttare al massimo le risorse del sistema e di migliorare le prestazioni delle applicazioni.
La classe thread di Java implementa tutte le funzionalità di un singolo thread e può essere creata in due modi:

- Estendendo la classe Thread, che prevede l'implementazione del metodo run() che contiene il codice da eseguire nel thread.
- Implementando l'interfaccia Runnable, che prevende anch'essa il metodo run().
A prescindere dal metodo utilizzato, il thread deve essere avviato chiamando il metodo start(), che avvia l'esecuzione del thread e chiama il metodo run().

Il thread può essere interrotto chiamando il metodo interrupt(), che invia un segnale di interruzione al thread, che può essere catturato e gestito nel metodo run().

### Applicazione 

Nel nostro progetto abbiamo utilizzato i thread per gestire il timer di gioco, la musica di sottofondo e il server socket.

- **UtilMusic**: Per permettere all'applicazione di riprodurre la musica di sottofondo in modo concorrente, è stata creata una classe che estende Thread e che implementa il metodo run() per gestire la riproduzione della musica.

```java
public class UtilMusic extends Thread {
  public void run() {
        try {
                AudioInputStream stream = AudioSystem.getAudioInputStream(new File("src/main/resources/music/Gegagedigedagedago.wav"));
                clip = AudioSystem.getClip();
                clip.open(stream);
                clip.addLineListener(event -> {
                        if (event.getType() == LineEvent.Type.STOP && !isMuted) {
                                clip.setFramePosition(0);
                                playClip();
                        }
                });
                playClip();
        } catch (UnsupportedAudioFileException e) {
                System.err.println("Unsupported audio file: " + e.getMessage());
        } catch (IOException e) {
                System.err.println("I/O error: " + e.getMessage());
        } catch (LineUnavailableException e) {
                System.err.println("Line unavailable: " + e.getMessage());
        }
  }
}
```

Qui viene gestita la comunicazione con un file di musica, che viene riprodotto in loop finché il gioco è in esecuzione.

- Nelle prossime applicazioni verranno usate insieme alle **lambda expressions** che approndiremo più avanti

- **Timer**: Il timer deve effettuare calcoli e aggiornamenti ogni secondo, per questo è stato utilizzato un thread che esegue un ciclo while che aggiorna il tempo di gioco nella GUI ogni secondo.
prendendo informazioni dalla classe **TimeManager** che gestisce il tempo di gioco
```java
public synchronized void tickTime(Game game) {
        new Thread(() -> {
            while (isGameRunning) {
                timerLabel.setText(" " + game.getTimeManager().getTime() + " ");

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();
    }
```

**synchronized** è stato utilizzato per evitare che più thread accedano contemporaneamente alle stesse risorse

- **Socket Server**: Il server socket è stato implementato in un thread a parte, che rimane in ascolto di richieste di connessione da parte dei client, e crea un thread dedicato per ogni client che si connette.
```java
public static void startServerA() {
        new Thread(() -> {
            try {
                ServerSocket serverSocket = new ServerSocket(PORT_A, MAX_USERS);
                System.out.println("SERVER STARTED at http://localhost:" + PORT_A);

                while (true) {
                    Socket socket = serverSocket.accept();
                    HttpPage page = new HttpPage(socket);

                    page.renderPage(new File("src/main/java/org/uniba/kobold/socket/page.html"));
                }

            } catch(IOException ex){
                System.out.println("CANNOT START SERVER AT PORT: " + PORT_A);
            }
        }).start();
    }
```
Sia per il server A che B

- **REST**: Il server REST è stato implementato anch'esso in un thread similarmente al socket server
```java
public static void startServer() {
        URI baseUri = UriBuilder.fromUri("http://localhost/").port(PORT).build();

        HttpServer server = GrizzlyHttpServerFactory.createHttpServer(baseUri, getControllers());

        new Thread(() -> {
            try {
                server.start();
                System.out.println(String.format("SERVER STARTED at http://localhost:%d", PORT));

                System.in.read();
                server.shutdown();
            } catch (IOException ex) {
                Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
            }
        }).start();

    }
```

#### [Ritorna all'Indice](#indice)

## Database