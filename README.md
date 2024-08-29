# API Carrito de Compras

API para gestionar productos, carritos y compras.

**Autores:**
- Bedini Pia: pbedini@alumnos.exa.unicen.edu.ar
- Fernandez Florencia: florenciafernandez0301@gmail.com

## Swagger
Se puede acceder a la documentación completa de la API a través de Swagger, donde encontrarás todos los endpoints disponibles, ejemplos de solicitudes y respuestas, así como descripciones detalladas de cada uno.  
`http://localhost:8080/api/swagger-ui/index.html`

## Endpoints

### Obtener productos de un usuario

**Descripción:**
La API `/api/products/{userId}` permite obtener una lista de productos específicos de un `userId`.

**Método HTTP:** `GET`

**Parámetros de la Ruta:**
- `userId`: Identificador único del usuario.

**Respuestas:**
- `200 OK`: La solicitud fue exitosa, y la respuesta contiene los datos del género correspondiente.
- `404 Not Found`: El usuario especificado no existe.

---

### Crear un Carrito de compras

**Descripción:**
La API `/api/cart` permite crear un nuevo carrito entre userId y sellerId (obtenido desde el producto especificado en el cuerpo de la solicitud).

**Método HTTP:** `POST`

**Headers de la solicitud:**
- `userId`: Identificador único del usuario.

**Cuerpo:**
- `productId`
- `quantity`

**Respuestas:**
- `201 Created`: La solicitud de creación fue exitosa, y la respuesta contiene los detalles del nuevo carrito.
- `400 Bad Request`: Ya existe un carrito abierto entre el usuario y el vendedor de ese producto o cantidad solicitada excede el stock disponible.
- `404 Not Found`: El usuario o el producto especificado no existe.

---

### Agregar un producto a un Carrito de compras

**Descripción:**
La API `/api/cart/{cartId}/product/{productId}` permite agregar el producto obtenido del cuerpo de la solicitud con `productId` al carrito con `cartId`.

**Método HTTP:** `POST`

**Parámetros de la Ruta:**
- `cartId`: Identificador único del carrito.
- `productId`: Identificador único del producto.

**Cuerpo:**
- `userId`
- `quantity`

**Respuestas:**
- `201 Created`: La solicitud de creación fue exitosa, y la respuesta contiene los detalles del nuevo carrito.
- `400 Bad Request`: La cantidad de productos debe ser mayor a cero.
- `403 Forbidden`: El carrito no pertence al usuario.
- `404 Not Found`: El producto o el carrito especificado no existe.
- `409 Conflict`: Cantidad solicitada excede el stock disponible.

---

### Actualizar cantidad de un producto en un Carrito de compras

**Descripción:**
La API `/api/cart/{cartId}/product/{productId}` posibilita la actualización de la cantidad de un producto mediante su `productId` en un determinado carrito `cartId`. Se debe proporcionar la cantidad en el cuerpo de la solicitud.

**Método HTTP:** `PUT`

**Parámetros de la Ruta:**
- `cartId`: Identificador único del carrito.
- `productId`: Identificador único del producto.

**Headers de la solicitud:**
- `userId`: Identificador único del usuario.

**Cuerpo:**
- `quantity`

**Respuestas:**
- `200 OK`: La solicitud de actualización fue exitosa, y la respuesta contiene los detalles del nuevo carrito.
- `400 Bad Request`: La cantidad de productos debe ser mayor a cero.
- `403 Forbidden`: El carrito no pertence al usuario.
- `404 Not Found`: El producto o el carrito especificado no existe.
- `409 Conflict`: Cantidad solicitada excede el stock disponible.

---

### Eliminar un producto de un Carrito de compras

**Descripción:**
La API `/api/cart/{cartId}/product/{productId}` permite la eliminación del producto con `productId` en el carrito `cartId`.

**Método HTTP:** `DELETE`

**Parámetros de la Ruta:**
- `cartId`: Identificador único del carrito.
- `productId`: Identificador único del producto.

**Headers de la solicitud:**
- `userId`: Identificador único del usuario.

**Respuestas:**
- `200 OK`: La solicitud de eliminación fue exitosa,  y la respuesta contiene los detalles del nuevo carrito.
- `403 Forbidden`: El carrito no pertence al usuario.
- `404 Not Found`: El producto o el carrito especificado no existe.

---

### Obtener un Carrito de Compras

**Descripción:**
La API `/api/cart/{cartId}` permite obtener el carrito con `cartId` del usuario `userId`, sus productos y el precio total del mismo.

**Método HTTP:** `GET`

**Parámetros de la Ruta:**
- `cartId`: Identificador único del carrito.

**Headers de la solicitud:**
- `userId`: Identificador único del usuario.

**Respuestas:**
- `200 OK`: La solicitud fue exitosa, y la respuesta contiene la lista de todos los productos del carrito.
- `403 Forbidden`: El carrito no pertence al usuario.
- `404 Not Found`: El carrito especificado no existe.

---

### Finalizar compra

**Descripción:**
La API `/api/cart/{cartId}/checkout` permite finalizar la compra del carrito `cartId` del usuario `userId`.

**Método HTTP:** `PUT`

**Parámetros de la Ruta:**
- `cartId`: Identificador único del carrito.

**Headers de la solicitud:**
- `userId`: Identificador único del usuario.

**Respuestas:**
- `200 OK`: La solicitud fue exitosa, y la respuesta contiene la lista de todos los productos del carrito.
- `403 Forbidden`: El carrito no pertence al usuario.
- `404 Not Found`: El carrito especificado no existe.
- `422 Unprocessable Entity`: Estado del carrito desactualizado con respecto al precio por unidad o stock insuficiente para completar la compra.
- `404 Not Found`: El carrito especificado no existe.

---

## Parte individual - Pia Bedini
### Obtener productos de un usuario

**Descripción:**
La API `/api/products` permite obtener una lista de todos los productos, independientemente del vendedor.

**Método HTTP:** `GET`

**Respuestas:**
- `200 OK`: La solicitud fue exitosa, y la respuesta contiene los datos del género correspondiente.

---

### Actualizar stock de un producto propio de un vendedor

**Descripción:**
La API `/api/products/{productId}/stock` posibilita la actualización del stock de un producto mediante su `productId` de un determinado vendedor `userId`. Se debe proporcionar el stock en el cuerpo de la solicitud.

**Método HTTP:** `PUT`

**Parámetros de la Ruta:**
- `productId`: Identificador único del producto.

**Headers de la solicitud:**
- `userId`: Identificador único del usuario.

**Cuerpo:**
- `stock`

**Respuestas:**
- `200 OK`: La solicitud de actualización fue exitosa.
- `403 Forbidden`: El producto no pertenece al usuario.
- `404 Not Found`: El producto o el usuario especificado no existe.
- `409 Conflict`: El stock actualizado es el mismo que el actual.
- 
---

### Actualizar precio de un producto propio de un vendedor

**Descripción:**
La API `/api/products/{productId}/price` posibilita la actualización del precio de un producto mediante su `productId` de un determinado vendedor `userId`. Se debe proporcionar el precio en el cuerpo de la solicitud.

**Método HTTP:** `PUT`

**Parámetros de la Ruta:**
- `productId`: Identificador único del producto.

**Headers de la solicitud:**
- `userId`: Identificador único del usuario.

**Cuerpo:**
- `unitPrice`

**Respuestas:**
- `200 OK`: La solicitud de actualización fue exitosa.
- `403 Forbidden`: El producto no pertenece al usuario.
- `404 Not Found`: El producto o el usuario especificado no existe.
- `409 Conflict`: El precio actualizado es el mismo que el actual.


---



