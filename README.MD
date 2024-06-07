# AutoMobile

## Ejecución

Para ejecutar el proyecto, se debe seguir los siguientes pasos:

- Iniciar Docker.
- Abrir un Terminal/Símbolo del sistema.
- Dirigirse al directorio src del repositorio en la terminal (donde se
  encuentra el archivo docker-compose.yml).
- Ejecutar el comando:
  docker-compose build
- Ejecutar el comando: docker-compose up
  docker-compose up
- Comprobamos que en la sección Containers, el contenedor se está
  ejecutando.

## Introducción

La página web de compra-venta de coches ofrece a los usuarios una
plataforma completa y confiable para explorar una amplia variedad de
vehículos disponibles para la venta, así como acceder a información valiosa
sobre el mundo automotriz.

Los usuarios pueden navegar por los listados de coches publicados por otros
usuarios sin necesidad de registrarse, pero se necesita un registro para
disfrutar de funciones adicionales como ver anuncios, publicar coches,
añadir otros a favoritos.

Se podrá hacer un filtro de los coches para encontrar el coche adecuado
para cada persona, y ordenarlos según el precio o kilometraje.

Permitimos contactar con los vendedores para adquirir un coche, y la
modificación del perfil para personalizar el usuario a sus gustos.

## Funciones

- RF01 - El usuario podrá registrarse.
- RF02 - El usuario podrá iniciar sesión.
- RF03 - El usuario podrá hacer cambios en su perfil.
- RF04 - Habrá un pequeño formulario para hacer la búsqueda del coche deseado.
- RF05 - Se podrá ver los coches publicados.
- RF06 - Se podrá filtrar la búsqueda de los coches.
- RF07 - Se podrá ordenar la búsqueda de los coches.
- RF08 - Se podrá contactar con el vendedor (número de tlf o email).
- RF09 - Se podrá guardar coches en favoritos.
- RF10 - Se podrá publicar coches.
- RF11 - Se podrá editar coches publicados.
- RF12 - Habrá una sección de noticias e información.
- RF13 - Administración: Los administradores tendrán acceso a un panel de control desde el cual pueden gestionar todos los aspectos de la plataforma.
- RF14 - Gestión de Usuarios: Los administradores pueden ver y administrar la lista de usuarios.
- RF15 - Gestión de Contenido: Los administradores pueden supervisar y gestionar
  los anuncios publicados por los usuarios para garantizar que cumplan con las
  directrices de la plataforma y no contengan contenido inapropiado fraudulento.

## Stack tecnológico

### Back-End:

Para el back-end se va a utilizar Spring Framework combinado con la base de datos de MySQL.

### Front-End:

Para el front-end se ha utilizado el framework Angular (HTML + Bootstrap, CSS + SCSS y TypeScript).
