ERRORES MAS FRECUENTES AL HACER UNA API-REST:

1.ESTRUCTURA DE RECURSOS: Asignar correctamente los nombres de tus URL en base a cada método HTTPS

ERROR					CORRECTO

GET:					GET:
-/api/listarClientes			-/api/v1/clientes -> indicamos nomenclatura, version, y nombre en plurarl
-/api/cliente-eliminar/14		-/api/v1/cliente?id=12 -> tambien se puede buscar de forma singular por id
-/api/cliente/guardar/25		-/api/v1/cliente/2/telefonos -> cliente el id y sus telefonos (recomendable el /2/ para enmascarar 										id)
					-/api/v1/clientes?page=2&size=10&order=desc -> consulta en clientes la pagina 2, 10 registros en 												orden descendente
					
					
POST:					POST: Se trabaja bajo bodies (JSON, fromdata...)
-/api/listarClientes			-/api/v1/cliente -> el bodie (con id) va por debajo, actualiza el cliente
-/api/cliente/eliminar/24		-/api/v1/usuario/12/restablecer-clave -> bajo una accion de tipo controlador
-/api/cliente/actualizar?id=25

PUT:										PUT: Se trabaja bajo bodies tambien
-/api/cliente/actualiar?id=25&nombre=Pablo&apellido=Mora&edad=32		-/api/v1/cliente -> el bodie (con id) va por debajo
NUNCA SE DEBE MANDAR TODA LA INFORMACION EN LA URL				-/api/v1/cliente/1
										-/api/v1/cliente?id=12 -> no tan recomendable a menos que 														vaya encriptado, porque ves el id
										
										
										DELETE:
										-/api/v1/cliente?id=12 -> cliente/12/, /cliente y con bodie 
													   tambien son formas válidas
													   
													   
													   
													   
													  
													  
2.ESPECIFICA LOS STATUS CODE: Define los status code segun su metodo


ERROR:				CORRECTO:

GET:				GET-DELETE:
-200 OK			-200 OK

POST:				POST:
-200 OK			-200 OK
				-201 CREATE
				
PUT:				PUT:
-200 OK			-200 OK
-201 CREATE			-201 CREATE

				ERROR:
				-404 NOT FOUND
				-405 METHOD NOT ALLOWED
				...
				
				
3.ESTRUCTURA DEL BODY RESPONSE: Debes ser claro con tus Response


ERROR:

POST:
-Guardado correctamente

PUT:
-Modificado correctamente

DELETE:
-Eliminado correctamente

Texto plano, lo cual dará problemas si por ejemplo otra API quisiera consumir tu API, o al implementarla en el entorno web.
Lo ideal es hacer un tipo de formato tal y como sean los datos JSON, XML... Y que sea todo claro:

CORRECTO:

PUT-POST-DELETE:

{
	codigo=0, //personalizado un codigo para cada cosa que pueda pasar, errores -1 o uno para cada cosa, a gusto
	mensaje="Modificado/Guardado correctamente",
	error=false, //si hay errores tambien se puede añadir un campo path que indique de que recurso viene el fallo
	fecha=2023/08/24
}
PUT:(Excepciones)
{
	codigo=2,
	mensaje=1, //El 1 nos indica el id, hay otras formas, esta es una de obtener el id
	error=false,
	fecha=2023/09/21
}



4.PROTEGE TU API: Las APIs deben estar totalmente protegidas

Gracias al protocolo HTTP nos permite obtener una protección adicional como el OAuth 2.0 el Basic Auth o el Bearer Token,
estos tres son los más utilizados, hay otros.



5.DOCUMENTA TU API: Siempre debes documentar tu API, evita la documentacion extensa via WORD, PDF o EMAIL

Desarrollas un API con 35 atributos (bastantes), nos puede llegar a resultar tedioso, pero tenemos la ayuda de herramientas
como Swagger UI nos permite documentar la API a través de consultar, pues nos genera el JSON por ejemplo



													  
