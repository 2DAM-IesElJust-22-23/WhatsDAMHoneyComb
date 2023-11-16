# Registre de canvis

## [Versió 2.2] - Adaptació del codi al model MVVM
### Canviat:
- Restructura del projecte per paquets.
- Adaptacio del codi al model MVVM.
  
## [Versió 2.1] - Correció de problemes :)
### Canviat:
- Importació Messages i Message al AdaptadorMessages i MessagesWindow.
- Finalització del RecycledView.

## [Versió 2.0] - Creació del RecycledView
### Afegit:
- Una nova classe **MessageViewHolder**.
- Funció per a mostrar l'hora actual.
- Una nova activitat **Messages**.
- Nova classe **Messages**.
- Nou objecte **Message**.
    - Funció per a afegir el missatges a la llista.
- Una nova activitat **AdaptadorMessages**.
- Funcions sobreescrites:
    - **onCreateViewHolder** --> La seua funció és crear una vista per a cada element en la llista.

    - **getItemCount** --> La seua funció és determinar quants elements s'han de mostrar en la RecyclerView.

    - **onBindViewHolder** --> La seua funció és vincular dades a una vista en un element de la RecyclerView.

## [Versió 1.3] - Refer el projecte

### Afegit:
- Projecte arreglat **WhatsDAMHoneyComb**.

### Canviat:
- Projecte vell **WhatsDam.0**.

## [Versió 1.2] - Canviar el nom, el ip i esborrar el que s'escriu al xat

### Afegit:
- Funció que mostra el text amb les variables que li hem passat.

### Canviat:
- Modificada la funció que passa les variables perquè es mostren.

## [Versió 1.1] - Primeres funcions

### Afegit:
- Funció per a avançar en prémer el botó.
- Funció per a passar les variables de nom i ip.

## [Versió 1.0] - Principi de desenvolupament

### Afegit:
- Creació projecte.
