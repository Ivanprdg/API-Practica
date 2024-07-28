# Errores más frecuentes al hacer una API-REST

## 1. Estructura de recursos
Asignar correctamente los nombres de tus URL en base a cada método HTTP

### GET

| ERROR                        | CORRECTO                                                                                          |
|------------------------------|---------------------------------------------------------------------------------------------------|
| `/api/listarClientes`        | `/api/v1/clientes` -> indicamos nomenclatura, versión, y nombre en plural                         |
| `/api/cliente-eliminar/14`   | `/api/v1/cliente?id=12` -> también se puede buscar de forma singular por id                       |
| `/api/cliente/guardar/25`    | `/api/v1/cliente/2/telefonos` -> cliente, el id y sus teléfonos (recomendable el /2/ para enmascarar id) |
|                              | `/api/v1/clientes?page=2&size=10&order=desc` -> consulta en clientes la página 2, 10 registros en orden descendente |

### POST

| ERROR                        | CORRECTO                                                                                          |
|------------------------------|---------------------------------------------------------------------------------------------------|
| `/api/listarClientes`        | `/api/v1/cliente` -> el body (con id) va por debajo, actualiza el cliente                         |
| `/api/cliente/eliminar/24`   | `/api/v1/usuario/12/restablecer-clave` -> bajo una acción de tipo controlador                     |
| `/api/cliente/actualizar?id=25` |                                                                                               |

### PUT

| ERROR                                                        | CORRECTO                                                                                          |
|--------------------------------------------------------------|---------------------------------------------------------------------------------------------------|
| `/api/cliente/actualizar?id=25&nombre=Pablo&apellido=Mora&edad=32` | `/api/v1/cliente` -> el body (con id) va por debajo                                               |
| NUNCA SE DEBE MANDAR TODA LA INFORMACIÓN EN LA URL           | `/api/v1/cliente/1`                                                                               |
|                                                              | `/api/v1/cliente?id=12` -> no tan recomendable a menos que vaya encriptado, porque ves el id       |

### DELETE

| ERROR                     | CORRECTO                                                                                          |
|---------------------------|---------------------------------------------------------------------------------------------------|
| `/api/v1/cliente?id=12`   | `/api/v1/cliente/12/`, `/api/v1/cliente` y con body también son formas válidas                    |

## 2. Especifica los status code
Define los status code según su método

### GET-DELETE

| ERROR     | CORRECTO  |
|-----------|-----------|
| 200 OK    | 200 OK    |

### POST

| ERROR     | CORRECTO  |
|-----------|-----------|
| 200 OK    | 200 OK    |
|           | 201 CREATE|

### PUT

| ERROR     | CORRECTO  |
|-----------|-----------|
| 200 OK    | 200 OK    |
| 201 CREATE| 201 CREATE|

### ERROR

| CÓDIGO    | DESCRIPCIÓN              |
|-----------|--------------------------|
| 404       | NOT FOUND                |
| 405       | METHOD NOT ALLOWED       |
| ...       |                          |

## 3. Estructura del body response
Debes ser claro con tus Response

### ERROR:

#### POST:
- Guardado correctamente

#### PUT:
- Modificado correctamente

#### DELETE:
- Eliminado correctamente

Texto plano, lo cual dará problemas si por ejemplo otra API quisiera consumir tu API, o al implementarla en el entorno web. Lo ideal es hacer un tipo de formato tal y como sean los datos JSON, XML... Y que sea todo claro:

### CORRECTO:

#### PUT-POST-DELETE:

```json
{
    "codigo": 0, // personalizado un código para cada cosa que pueda pasar, errores -1 o uno para cada cosa, a gusto
    "mensaje": "Modificado/Guardado correctamente",
    "error": false, // si hay errores también se puede añadir un campo path que indique de qué recurso viene el fallo
    "fecha": "2023/08/24"
}

{
    "codigo": 2,
    "mensaje": 1, // El 1 nos indica el id, hay otras formas, esta es una de obtener el id
    "error": false,
    "fecha": "2023/09/21"
}
```
## 4. Protege tu API
Las APIs deben estar totalmente protegidas.

Gracias al protocolo HTTP, podemos obtener una protección adicional como el OAuth 2.0, el Basic Auth o el Bearer Token. Estos tres son los más utilizados, aunque hay otros.

## 5. Documenta tu API
Siempre debes documentar tu API, evita la documentación extensa vía WORD, PDF o EMAIL.

Desarrollar una API con 35 atributos (bastantes) nos puede llegar a resultar tedioso, pero tenemos la ayuda de herramientas como Swagger UI que nos permite documentar la API a través de consultas, pues nos genera el JSON, por ejemplo.


