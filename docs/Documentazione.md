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

# Progettazione

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

## 4 - Specifiche Algebriche
Due delle strutture dati più utilizzate nel nostro progetto sono la **Lista** e la **Mappa**, in questa sezione verranno presentate le specifiche algebriche di entrambe.

### 4.1 - Specifica algebrica della Lista

### Specifica sintattica
<table>
    <thead>
        <tr>
            <th colspan="2">Tipi</th>
        </tr>
    </thead>
    <tbody>
        <tr>
            <td colspan="2"><code>List</code>, <code>Item</code>, <code>Integer</code>, <code>Boolean</code></td>
        </tr>
        <tr>
            <td colspan="2" align="center"><strong>Operatori</strong></td>
        </tr>
        <tr>
            <td><code>newList() -> List</code></td>
            <td>Crea una nuova lista vuota</td>
        </tr>
        <tr>
            <td><code>add(List, Item, Integer) -> List</code></td>
            <td>Aggiunge un elemento alla lista nella posizione specificata</td>
        </tr>
        <tr>
            <td><code>isEmpty(List) -> Boolean</code></td>
            <td>Restituisce <code>true</code> se la lista è vuota altrimenti <code>false</code></td>
          </tr>
            <tr>
                <td><code>getSize(List) -> Integer</code></td>
                <td>Restituisce il numero di elementi della struttura</td>
            </tr> 
            <tr>
                <td><code>getIndex(List, Item) -> Integer</code></td>
                <td>Restituisce la posizione dell'elemento specificato</td>
            </tr> 
            <tr>
                <td><code>getItem(List, Integer) -> Item</code></td>
                <td>Restituisce l'elemento nella posizione specificata</td> 
            </tr> 
            <tr>
                <td><code>remove(List, Integer) -> List</code></td>
                <td>Rimuove dalla lista l'elemento nella posizione specificata</td>  
            </tr>
            <tr>
                <td><code>contains(List, Item) -> Boolean</code></td>
                <td>Restituisce <code>true</code> se l'elemento specificato è contenuto nella lista</td>
            </tr>
    </tbody>
</table>
Si noti come <code>Item</code> è un tipo generico, che può essere sostituito con qualsiasi altro tipo di dato.

<code>Interger</code> e <code>Boolean</code> invece, sono tipi ausiliari alla definizione della specifica algebrica della lista.

### Osservazioni e Costruttori

<table>
  <thead>
    <tr>
      <th></th>
      <th colspan="2">Costruttori di l'</th>
    </tr>
  </thead>
  <tbody align="center">
    <tr>
      <td><strong>Osservazioni</strong></td>
      <td><code>newList</code></td>
      <td><code>add(l, it, id)</code></td>
    </tr>
    <tr>
      <td><code>isEmpty(l')</code></td>
      <td><code>true</code></td>
      <td><code>false</code></td>
    </tr>
    <tr>
      <td><code>getSize(l')</code></td>
      <td><code>error</code></td>
      <td>if <code>isEmpty(l)</code> then <code>1</code> else <code>getSize(l) + 1</code></td>
    </tr>
    <tr>
      <td><code>getIndex(l', it')</code></td>
      <td><code>error</code></td>
      <td>if <code>it = it'</code> then <code>id</code> else <code>getIndex(l, it')</code></td>
    </tr>
    <tr>
      <td><code>getItem(l', id')</code></td>
      <td><code>error</code></td>
      <td>if <code>id = id'</code> then <code>it</code> else <code>getItem(l, id')</code></td>
    </tr>
    <tr>
      <td><code>remove(l', id')</code></td>
      <td><code>error</code></td>
      <td>if <code>id = id'</code> then <code>l</code> else <code>add(remove(l, id'), it)</code></td>
    </tr>
    <tr>
      <td><code>contains(l', it')</code></td>
      <td><code>false</code></td>
      <td>if <code>it = it'</code> then <code>true</code> else <code>contains(l, it')</code></td>
    </tr>
  </tbody>
</table>

### Specifica semantica
- **DECLARE**
    - <code>l</code>, <code>l'</code>: <code>List</code>
    - <code>it</code>, <code>it'</code>: <code>Item</code>
    - <code>id</code>, <code>id'</code>: <code>Integer</code>


- **OPERATIONS**
    - <code>isEmpty(newList)</code> = <code>true</code>
    - <code>isEmpty(add(l, it, id))</code> = <code>false</code>
    - <code>getSize(add(l, it, id))</code> = if <code>isEmpty(l)</code> then <code>1</code> else <code>getSize(l) + 1</code>
    - <code>getIndex(add(l, it, id), it')</code> = if <code>it = it'</code> then <code>id</code> else <code>getIndex(l, it')</code>
    - <code>getItem(add(l, it, id), id')</code> = if <code>id = id'</code> then <code>it</code> else <code>getItem(l, id')</code>
    - <code>remove(add(l, it, id), id')</code> = if <code>id = id'</code> then <code>l</code> else <code>add(remove(l, id'), it)</code>
    - <code>contains(newList, it')</code> = <code>false</code>
    - <code>contains(add(l, it, id), it')</code> = if <code>it = it'</code> then <code>true</code> else <code>contains(l, it')</code>

### Specifica di restrizione
- **RESTRICTIONS**
    - <code>getSize(newList)</code> = <code>error</code>
    - <code>getIndex(newList, it')</code> = <code>error</code>
    - <code>getItem(newList, id')</code> = <code>error</code>
    - <code>remove(newList, id')</code> = <code>error</code>

### 4.2 - Specifica algebrica della Mappa

- La mappa è una struttura dati che associa una chiave ad un valore, permettendo di memorizzare e recuperare informazioni in modo efficiente.

### Specifica sintattica
<table>
    <thead>
        <tr>
            <th colspan="2">Tipi</th>
        </tr>
    </thead>
    <tbody>
        <tr>
            <td colspan="2">Map, Key, Value, Boolean, Integer</td>
        </tr>
        <tr>
            <td colspan="2"><strong>Operatori</strong></td>
        </tr>
        <tr>
            <td><code>newMap() -> Map</code></td>
            <td>Crea una nuova mappa vuota</td>
        </tr>
        <tr>
            <td><code>isEmpty(Map) -> Boolean</code></td>
            <td>Restituisce <code>true</code> se la mappa è vuota, <code>false</code> altrimenti</td>
        </tr>
        <tr>
            <td><code>put(Map, Key, Value) -> Map</code></td>
            <td>Aggiunge una coppia chiave-valore alla mappa, o, se già presente, ne aggiorna il valore</td>
        </tr>
        <tr>
            <td><code>get(Map, Key) -> Value</code></td>
            <td>Restituisce il valore associato alla chiave specificata</td>
        </tr>
        <tr>
            <td><code>containsKey(Map, Key) -> Boolean</code></td>
            <td>Restituisce <code>true</code> se la chiave specificata è presente nella mappa</td>
        </tr> 
        <tr>
            <td><code>containsValue(Map, Value) -> Boolean</code></td>
            <td>Restituisce <code>true</code> se il valore specificato è presente nella mappa</td> 
        </tr>
        <tr>
            <td><code>remove(Map, Key) -> Map</code></td>
            <td>Rimuove la chiave ed il valore associato ad essa dalla mappa</td>
        </tr> 
        <tr>
            <td><code>size(map) -> Integer</code></td>
            <td>Restituisce il numero di coppie chiave-valore presenti nella mappa</td>  
        </tr>
    </tbody>
</table>

### Osservazioni e Costruttori

<table>
  <thead>
    <tr>
      <th></th>
      <th colspan="2">Costruttori di m'</th>
    </tr>
  </thead>
  <tbody align="center">
    <tr>
      <td><strong>Osservazioni</strong></td>
      <td><code>newMap</code></td>
      <td><code>put(m, k, v)</code></td>
    </tr>
    <tr>
      <td><code>isEmpty(m')</code></td>
      <td><code>true</code></td>
      <td><code>false</code></td>
    </tr>
    <tr>
      <td><code>containsKey(m', k')</code></td>
      <td><code>false</code></td>
      <td>if <code>k = k'</code> then <code>true</code> else <code>containsKey(m, k')</code></td>
    </tr>
    <tr>
      <td><code>containsValue(m', v')</code></td>
      <td><code>false</code></td>
      <td>if <code>v = v'</code> then <code>true</code> else <code>containsValue(m, v')</code></td>
    </tr>
    <tr>
      <td><code>get(m', k')</code></td>
      <td><code>error</code></td>
      <td>if <code>k = k'</code> then <code>v</code> else <code>get(m, k')</code></td>
    </tr>
    <tr>
      <td><code>remove(m', k')</code></td>
      <td><code>error</code></td>
      <td>if <code>k = k'</code> then <code>m</code> else <code>put(remove(m, k'), k, v)</code></td>
    </tr>
    <tr>
      <td><code>size(m')</code></td>
      <td><code>0</code></td>
      <td>if <code>isEmpty(m)</code> then <code>1</code> else <code>size(m) + 1</code></td>
    </tr>
  </tbody>
</table>

### Specifica semantica

- **DECLARE**
    - <code>m</code>, <code>m'</code>: <code>Map</code>
    - <code>k</code>, <code>k'</code>: <code>Key</code>
    - <code>v</code>, <code>v'</code>: <code>Value</code>

- **OPERATIONS**
    - <code>isEmpty(newMap)</code> = <code>true</code>
    - <code>isEmpty(put(m, k, v))</code> = <code>false</code>
    - <code>containsKey(newMap, k')</code> = <code>false</code>
    - <code>containsKey(put(m, k, v), k')</code> = if <code>k = k'</code> then <code>true</code> else <code>containsKey(m, k')</code>
    - <code>containsValue(newMap, v')</code> = <code>false</code>
    - <code>containsValue(put(m, k, v), v')</code> = if <code>v = v'</code> then <code>true</code> else <code>containsValue(m, v')</code>
    - <code>get(put(m, k, v), k')</code> = if <code>k = k'</code> then <code>v</code> else <code>get(m, k')</code>
    - <code>remove(put(m, k, v), k')</code> = if <code>k = k'</code> then <code>m</code> else <code>put(remove(m, k'), k, v)</code>
    - <code>size(newMap)</code> = <code>0</code>
    - <code>size(put(m, k, v))</code> = <code>size(m) + 1</code>


### Specifica di restrizione

- **RESTRICTIONS**
    - <code>get(newMap, k')</code> = <code>error</code>
    - <code>remove(newMap, k')</code> = <code>error</code>
#### [Ritorna all'Indice](#indice)