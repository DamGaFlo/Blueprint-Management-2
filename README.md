# Blueprint Management 2

## PART III
-   What race conditions could occur?

    Trying to create or update a certain blueprint could cause bad behavior in terms of keys and values

-   What are the respective critical regions?

    All calls to BluprintServices are a critical regions

### Solution

Just use a ConcurrentHashMap that allows us to lock the resource when a put sentence is executed
``` java
private final Map<Tuple<String, String>, Blueprint> blueprints = new ConcurrentHashMap<>();
```
This usually is handle by the dbms in case you use a database to save your data
