# Proyecto Hospital
------

[Juan Espinosa](https://github.com/JuanEspinosa97)

[Ignacio Rodriguez](https://github.com/IgnacioRodrig)

Nuestro proyecto se basa en un hospital. Vamos a crear una base de datos para recabar informacion sobre los pacientes, doctores, enfermeros y habitaciones. Esto nos permitira llevar un correcto funcionamiento de la gestion de los clientes.

- Diagrama Entidad-Relacion de la Base de Datos.

![Diagrama E-R](https://github.com/JuanEspinosa97/tis_2022_repositorio/blob/main/doc/Proyecto_Entidad_Relacion.png)

- Diagrama Casos de Uso de la Base de Datos.

![Diagrama UML](https://github.com/JuanEspinosa97/tis_2022_repositorio/blob/main/doc/Proyecto_Diagrama_de_usos.png)

- Las tablas de nuestra base de datos son:

![Tablas Proyecto](https://github.com/JuanEspinosa97/tis_2022_repositorio/blob/main/Tablas1.png)

![](https://github.com/JuanEspinosa97/tis_2022_repositorio/blob/main/Tablas2.png)

En la tabla Departamentos tenemos las columnas Id, Nombre y NumEmpleados. Tenemos un Id unico para cada departamento.

En la tabla Doctores tenemos las columnas Id, Nombre, NumColegiado, Edad, Sexo y IdDep. IdDep es una FOREIGN KEY wue referencia a la columna Id de la tabla departamentos. La columna Sexo solo puede rellenarase con los valores 'Hombre', 'Mujer' y puede ser NULL. 

La tabla Enfermeros tiene las columnas Id, Nombre y Edad. El Id es unico para cada elemento.

La tabla EnfermerosPacientes representa la relacion N-M que tienen estas dos entidades en el diagrama E-R. Consta de dos columnas IdEnfermero y IdPaciente ambas son PRIMARY KEY.

Tabla Habitaciones, consta de las columnas Id, NumHabitacion y Estado. Estado solo puede rellenarse con los valores 'Libre' o 'Ocupado'. 

La tabla Pacientes tiene las columnas Id, Nombre, Edad, MotivoIngreso, IdDoctor, IdHabitacion, FechaIngreso y Sexo. IdDoctor y IdHabitacion son FOREIGN KEY que referencian al id de las tablas Doctores y Habitaciones respectivamente.La columna Sexo solo puede rellenarase con los valores 'Hombre', 'Mujer' y puede ser NULL.

