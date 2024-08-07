# Carrito de compra

## Introducción


El objetivo de este Bootcamp es la construcción de una **API Rest** utilizando **Spring boot** como framework de desarrollo. Para ello, se provee este repositorio en gitlab con un proyecto que sirve como base para desarrollar los requerimientos funcionales. Este repositorio se encuentra restringido y, por ende, el desarrollador del bootcamp deberá crear un _fork_ para trabajar.

Los progresos y consultas se realizarán en los espacios de consulta de las clases restantes o a través de Slack. **Se estima para la finalización del bootcamp entre 2 y 3 semanas.**

## Requerimiento funcional


Se necesita un sistema de comercio electrónico. Un sistema de comercio electrónico, mejor conocido como E-commerce, es un sistema de compra y venta de productos o servicios que se realiza exclusivamente a través de Internet. Se refiere a las transacciones entre compradores y vendedores mediante una plataforma online que gestiona los cobros y los pagos de manera completamente electrónica. Estos tipos de sistemas presentan múltiples módulos que se enfocan en distintos subdominios del problema: finanzas, stock, etc. 

En un principio, cada usuario del sistema puede tener su propio stock de productos en venta. Como primer requerimiento se desea desarrollar el carrito de compras. El mismo se responsabiliza de llevar control de los productos y cantidades que un usuario desea comprarle a otro. A su vez contiene la información del precio total a pagar por dicho carrito.

Tener en cuenta que los productos en stock son dinámicos en terminos de cantidad disponible y precio por unidad.

Debe ser claro, al momento de finalizar una compra de un carrito, cual es el precio actual por unidad, el precio final, y si es posible satisfacer las cantidades requeridas.

Teniendo en cuenta estos requerimientos desde el lado del backend se necesita API que haga una ABM de este carrito de compra. La API debe contener servicios para crear un carrito de compras (con al menos un producto), agregar productos al carrito de compra, eliminar productos, calcular el precio total del carrito de compra y finalizar la compra de un carrito.

En una reunión de planeamiento para el nuevo requerimiento, el equipo de desarrollo acordó utilizar las siguientes tecnologías:

1. Spring Boot.
2. Postgresql
3. Docker

La idea con estas decisiones está en manejar tecnologías open-source que faciliten el desarrollo. A su vez, se establece como convención de arquitectura, la estructura de una [arquitectura hexagonal](https://alistair.cockburn.us/hexagonal-architecture/) que dirija todo el desarrollo.

## User Stories

### Listado de productos

Como usuario, quiero obtener un listado de productos con sus cantidades disponibles, para saber qué productos están actualmente en el stock de otro usuario.

#### Criterios de aceptación:

* La API debe devolver una lista de productos, con su precio unitario y cantidad.

* Si no hay productos disponibles, se debe devolver una lista vacía.

### Creación de carrito de compra

Como usuario, quiero crear un carrito de compra con productos de otro usuario, para poder preparar una compra.

#### Criterios de aceptación:

* La API debe permitir crear un carrito de compra especificando el usuario al que se desea realizar misma.

* El carrito contiene como minimo, una lista de códigos de productos con sus cantidades.

* El sistema debe validar que el carrito contenga al menos un producto.

* El sistema debe validar que cada código de producto pertenezca a un producto existente en ese stock; si no, debe devolver un error indicando el problema.

* El sistema debe validar que la cantidad solicitada no exceda el stock disponible; si lo hace, debe devolver un error.

* Las respuestas de error deben ser claras y específicas al problema.

* En caso de éxito en la creación del carrito, se debe devolver el código del mismo.

### Agregar un tipo de producto al carrito de compra

Como usuario, quiero agregar un tipo de producto a un carrito de compra existente.

#### Criterios de aceptación:

* La API debe permitir agregar un tipo de producto a un carrito de compra específico.

* El sistema debe validar que el carrito de compra exista y pertenezca al usuario que realiza la operación, también que el tipo de producto que se agrega, pertenezca al stock del usuario al que se esta realizando la compra; en caso contrario, debe devolver el error especifico.

* El sistema debe validar que la cantidad de unidades del tipo de producto a agregar al carrito no exceda el stock disponible; si lo hace, debe devolver un error.

### Modificar un tipo de producto al carrito de compra

Como usuario, quiero modificar la cantidad de unidades de un tipo de producto al carrito de compra, para ajustar mi pedido.

#### Criterios de aceptación:

* La API debe permitir modificar la cantidad unidades de un tipo de producto de un carrito de compra específico.

* El sistema debe validar que el carrito de compra exista, que pertenezca al usuario asociado y el tipo de producto ya esté precargado en el carrito; si no, debe devolver un error.

### Eliminar un tipo de producto al carrito de compra

Como usuario, quiero eliminar un tipo de producto en un carrito de compra, para ajustar mi pedido.

#### Criterios de aceptación:

* La API debe permitir eliminar un tipo de producto en un carrito de compra específico.

* El sistema debe validar que el carrito de compra exista y pertenezca al usuario asociado; también que el tipo de producto a eliminar, pertenezca actualmente al carrito, si no, debe devolver un error.

### Pedir estado de un carrito

Como usuario, quiero solicitar el estado de un carrito para hacer la compra.

#### Criterios de aceptación:

* El sistema debe validar que el carrito de compra exista y pertenezca al usuario asociado; si no, debe devolver un error.

* La API debe permitir validar si la cantidad solicitada de cada tipo de producto puede ser satisfacida por el stock actual. si no, debe devolver la información del faltante.

* La API debe permitir devolver el precio por unidad de cada producto solicitado.

* La API debe permitir calcular el costo total de los productos en un carrito de compra específico.

### Finalizar compra

Como usuario, quiero finalizar mi compra, para completar el proceso de compra.

#### Criterios de aceptación:

* La API debe permitir finalizar una compra para un carrito de compra específico.

* El sistema debe validar que el carrito de compra exista y pertenezca al usuario asociado; si no, debe devolver un error.

* El sistema debe validar que el carrito de compra pueda ser satisfacido en términos de stock; si no, debe devolver un error.

* El sistema debe validar que el estado del carrito que el usuario obtuvo coincida con el estado de la compra que va a realizar (precio unirario / precio total); si no, debe devolver un error.

* Al finalizar la compra, el sistema debe actualizar el stock de los productos.

* El carrito debe ser procesado, impidiendo operaciones sobre él.


### Realizar persistencia de los datos

Como desarrollador, quiero que los datos se almacenen de manera persistente, para que el sistema mantenga su estado tras reinicios.

#### Criterios de aceptación:

* Se debe utilizar el contenedor de PostgreSQL disponible en el repositorio para la persistencia de datos.

## Notas generales

* **Documentación**: Cada endpoint debe estar debidamente documentado, incluyendo detalles como parámetros, respuestas esperadas, errores posibles y ejemplos de uso.

* **Pruebas unitarias**: Cada funcionalidad debe estar cubierta con pruebas unitarias para verificar su funcionamiento y la validación de los criterios de aceptación.

## Sugerencias

Estas sugerencias no pretenden ser completas, solo una guía parcial ilustrativa.


Endpoints

```
GET /api/products
    - Obtener listado de productos
    > 404 "No hay productos disponibles."

POST /api/carts
    - Crear un carrito de compra dado un id de usuario vendedor
    > 400 "El carrito debe contener al menos un producto."

POST /api/carts/{cartId}/products/{productId}
    - Agregar un tipo de producto al carrito de compra
    > 400 "La cantidad de productos debe ser mayor a cero."
    > 403 "El carrito no pertence al usuario"
    > 404 "Producto no encontrado."
    > 404 "Carrito no encontrado."
    > 409 "Cantidad solicitada excede el stock disponible."

PUT /api/carts/{cartId}/products/{productId}
    - Editar un tipo de producto al carrito de compra
    > 400 "La cantidad de productos debe ser mayor a cero."
    > 403 "El carrito no pertence al usuario"
    > 404 "Producto no encontrado."
    > 404 "Carrito no encontrado."
    > 409 "Cantidad solicitada excede el stock disponible."

DELETE /api/carts/{cartId}/products/{productId}
    - Eliminar productos del carrito de compra
    > 403 "El carrito no pertence al usuario"
    > 404 "Producto no encontrado."
    > 404 "Carrito no encontrado."

GET /api/carts/{cartId}
    - Calcular el estado del carrito de compra
    > 403 "El carrito no pertence al usuario"
    > 404 "Carrito no encontrado."

POST /api/carts/{cartId}/checkout
    - Finalizar compra
    > 403 "El carrito no pertence al usuario"
    > 422 "Estado del carrito desactualizado con respecto al precio por unidad."
    > 422 "Stock insuficiente para completar la compra."
    > 404 "Carrito no encontrado."
```

Producto:

```
[
  {
    "id": 1,
    "name": "Producto A",
    "quantity": 100,
    "price": 350
  },
  {
    "id": 2,
    "name": "Producto B",
    "quantity": 50,
    "price": 2350
  }
]
```

Carrito:

```
{
  "products": [
    {
      "productId": 1,
      "quantity": 2
    },
    {
      "productId": 2,
      "quantity": 1
    }
  ]
}
```

### Manejo de errores y estandarización de respuestas

Para garantizar una experiencia consistente para los consumidores de la API, se puede estandarizar el formato de las respuestas de error. Esto facilitará el manejo de errores en el lado del cliente y mejorará la depuración.

#### Estructura de respuesta de error

Todas las respuestas de error podrían tener una estructura como esta:

```json
{
  "error": {
    "codigo": "ERROR_CODE",
    "mensaje": "Descripción detallada del error",
    "detalles": {
      // Información adicional específica del error (opcional)
    }
  }
}
```

#### Posibles códigos de error

- `INVALID_INPUT`: Datos de entrada inválidos
- `RESOURCE_NOT_FOUND`: Recurso no encontrado
- `INSUFFICIENT_STOCK`: Stock insuficiente
- `UNAUTHORIZED`: No autorizado
- `CART_MISMATCH`: El carrito no pertenece al usuario
- `OUTDATED_CART_STATE`: Estado del carrito desactualizado


## Workflow

Los requerimientos son presentados en forma de **User Stories**. Estas deben ser analizadas y desglosadas en **tareas** concretas de desarrollo para así poder brindar _visibilidad_ del trabajo en progreso y el trabajo pendiente. El _**proyecto**_ **particular** dentro de **Jira** donde se realice el seguimiento y avance del bootcamp **quedará a elección del** _**de los integrantes del gurpo**_.

Se espera que se tenga una mentalidad orientada a la integración y entrega continua de funcionalidades (_CI/CD_). Por ende, los commits en el repositorio se espera que sean pequeños y legibles para poder comprender una nueva versión estable del sistema que se está desarrollando. Cada uno de estos commits debe corresponder con una tarea en **Jira** mediante un identificador como prefijo en el mensaje del commit.

Ejemplo de formato: **EC-\[nro\_tarea\_jira\] \[Descripción de la funcionalidad que incorpora\]**

De esta manera se puede tener _trazabilidad_ entre los requerimientos y los desarrollos incluidos en el repositorio.
