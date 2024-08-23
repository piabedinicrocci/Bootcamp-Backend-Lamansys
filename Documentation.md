# API Carrito de Compras

API para gestionar productos, carritos y compras.

**Autores:**
- Bedini Pia: pbedini@alumnos.exa.unicen.edu.ar
- Fernandez Florencia: florenciafernandez0301@gmail.com

## Swagger
Se puede acceder a la documentación completa de la API a través de Swagger, donde encontrarás todos los endpoints disponibles, ejemplos de solicitudes y respuestas, así como descripciones detalladas de cada uno.  
`http://localhost:8080/api/swagger-ui/index.html`

## Endpoints

### Listar productos

**Descripción:**
La API `/api/products` se utiliza para obtener una lista de todos los productos

**Método HTTP:** `GET`

  **Respuestas:**
- `200 OK`: La solicitud fue exitosa, y la respuesta contiene la lista de todos los productos.

---

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
La API `/api/carts/{userId}` permite crear un nuevo carrito entre userId y sellerId (obtenido desde el producto especificado en el cuerpo de la solicitud).

**Método HTTP:** `POST`

**Parámetros de la Ruta:**
- `userId`: Identificador único del usuario.

**Respuestas:**
- `201 Created`: La solicitud de creación fue exitosa, y la respuesta contiene los detalles del nuevo carrito.
- `400 Bad Request`: Ya existe un carrito abierto entre el usuario y el vendedor de ese producto o cantidad solicitada excede el stock disponible.
- `404 Not Found`: El usuario o el producto especificado no existe.

---

### Agregar un producto a un Carrito de compras

**Descripción:**
La API `/api/carts/{cartId}/products/{productId}` permite agregar un producto con `productId` al carrito con `cartId`.

**Método HTTP:** `POST`

**Parámetros de la Ruta:**
- `cartId`: Identificador único del carrito.
- `productId`: Identificador único del producto.

**Respuestas:**
- `201 Created`: La solicitud de creación fue exitosa, y la respuesta contiene los detalles del nuevo carrito.
- `400 Bad Request`: La cantidad de productos debe ser mayor a cero.
- `403`: El carrito no pertence al usuario.
- `404 Not Found`: El producto o el carrito especificado no existe.
- `409`: Cantidad solicitada excede el stock disponible.

---

### Actualizar cantidad de un producto en un Carrito de compras

**Descripción:**
La API `/api/carts/{cartId}/products/{productId}` posibilita la actualización de la cantidad de un producto mediante su `productId` en un determinado carrito `cartId`. Se deben proporcionar los nuevos datos en el cuerpo de la solicitud.

**Método HTTP:** `PUT`

**Parámetros de la Ruta:**
- `cartId`: Identificador único del carrito.
- `productId`: Identificador único del producto.

**Respuestas:**
- `200 OK`: La solicitud de actualización fue exitosa, y la respuesta contiene los detalles del nuevo carrito.
- `400 Bad Request`: La cantidad de productos debe ser mayor a cero.
- `403`: El carrito no pertence al usuario.
- `404 Not Found`: El producto o el carrito especificado no existe.
- `409`: Cantidad solicitada excede el stock disponible.

---

### Eliminar un producto de un Carrito de compras

**Descripción:**
La API `/api/carts/{cartId}/products/{productId}` permite la eliminación del producto con `productId` en el carrito `cartId`.

**Método HTTP:** `DELETE`

**Parámetros de la Ruta:**
- `cartId`: Identificador único del carrito.
- `productId`: Identificador único del producto.

**Respuestas:**
- `200 OK`: La solicitud de eliminación fue exitosa, y la respuesta contiene los detalles del nuevo carrito.
- `403`: El carrito no pertence al usuario.
- `404 Not Found`: El producto o el carrito especificado no existe.

---

### Obtener un Carrito de Compras

**Descripción:**
La API `/api/carts/{cartId}` permite obtener el carrito con `cartId` junto al precio unitario de los productos y el precio total del mismo.

**Método HTTP:** `GET`

**Parámetros de la Ruta:**
- `cartId`: Identificador único del carrito.

**Respuestas:**
- `200 OK`: La solicitud fue exitosa, y la respuesta contiene la lista de todos los productos del carrito.
- `403`: El carrito no pertence al usuario.
- `404 Not Found`: El carrito especificado no existe.

---


### Finalizar compra

**Descripción:**
La API `/api/carts/{cartId}/checkout` permite finalizar la compra del carrito `cartId`.

**Método HTTP:** `POST`

**Parámetros de la Ruta:**
- `cartId`: Identificador único del carrito.

**Respuestas:**
- `200 OK`: La solicitud fue exitosa, y la respuesta contiene la lista de todos los productos del carrito.
- `403`: El carrito no pertence al usuario.
- `404 Not Found`: El carrito especificado no existe.
- `422`: Estado del carrito desactualizado con respecto al precio por unidad o stock insuficiente para completar la compra.
- `404 Not Found`: El carrito especificado no existe.

---





