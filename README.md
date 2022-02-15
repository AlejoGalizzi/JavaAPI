# AlkemyJavaChallenge

Este documento establece como funcionara la api. Asi como datos para que se ingresen en los endpoints.

La api se creo para tener un registro de las peliculas y series de disney asi como los personajes que muchas veces participan 
en distintas peliculas (Por ejemplo, las peliculas de superheroes de Marvel que son propiedad de Disney).

Dentro de la documentacion de postman se utiliza un email propio para enviar el mail de bienvenida. Se recomienda para ver que funciona, probar el registro y el login con un mail ditinto.

Como se establecio una pelicula puede tener muchos personajes y un personaje puede estar en muchas peliculas. Por lo tanto, una relacion de una pelicula con un persoanaje se 
realizara en un endpoint especifico. Todos los detalles de los endpoints se encuentran en la documentacion de los mismos.

Tambien una pelicula puede tener un solo genero, por lo que el mismo se definira cuando se cree una pelicula.

Algunos datos para instanciar genero, peliculas y personajes. Luego dependera de cada usuario crear las relaciones que desee con el uso de los endpoints.

Datos para los generos(copie los body encerrados por '{}')
- {
    "nombre":"Accion"
  }
- {
    "nombre":"Animacion"
  }
- {
    "nombre":"Infantil"
  }
 
Datos para las peliculas(copie los body encerrados por '{}')
- {
    "titulo":"Lilo and Stitch",
    "fecha_creacion": "2002-01-05",
    "calificacion": "4",
    "generoId":"2"
  }
- {
    "titulo":"Goofy: The Movie",
    "fecha_creacion": "1995-01-06",
    "calificacion": "5",
    "generoId":"3"
  }
- {
    "titulo":"Spider Man: Homecoming",
    "fecha_creacion": "2017-01-06",
    "calificacion": "3",
    "generoId":"1"
  }
- {
    "titulo":"Iron Man",
    "fecha_creacion": "2008-01-29",
    "calificacion": "3",
    "generoId":"1"
  } 
- {
    "titulo":"WandaVision",
    "fecha_creacion": "2021-01-14",
    "calificacion": "2",
    "generoId":"1"
  }
- {
    "titulo":"Avengers: Endgame",
    "fecha_creacion": "2019-01-25",
    "calificacion": "5",
    "generoId":"1"
  } 
- {
    "titulo":"Captain America: The First Avenger",
    "fecha_creacion": "2011-01-27",
    "calificacion": "4",
    "generoId":"1"
  }  

Datos para los personajes(copie los body encerrados por '{}')
- {
    "nombre":"Goofy",
    "edad":"40",
    "peso":"60"
  }
- {
    "nombre":"Mickey Mouse",
    "edad":"30",
    "peso":"40"
  }
- {
    "nombre":"Spider Man",
    "edad":"18",
    "peso":"75"
  }
- {
    "nombre":"Iron Man",
    "edad":"40",
    "peso":"85"
  }
- {
    "nombre":"Scarlet Witch",
    "edad":"23",
    "peso":"70"
  }
- {
    "nombre":"Captain Marvel",
    "edad":"30",
    "peso":"70"
  }
- {
    "nombre":"Captain America",
    "edad":"30",
    "peso":"70"
  }
- {
    "nombre":"Stitch",
    "edad":"20",
    "peso":"30"
  }
