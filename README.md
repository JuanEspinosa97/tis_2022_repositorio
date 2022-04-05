# Proyecto Hospital
------

[Juan Espinosa](https://github.com/JuanEspinosa97)

[Ignacio Rodriguez](https://github.com/IgnacioRodrig)

Nuestro proyecto se basa en un hospital. Vamos a crear una base de datos para recabar informacion sobre los pacientes, 
doctores, enfermeros y habitaciones. Esto nos permitira llevar un correcto funcionamiento de la gestion de los clientes.

- **Diagrama Entidad-Relacion de la Base de Datos**

![Diagrama E-R](https://github.com/JuanEspinosa97/tis_2022_repositorio/blob/main/doc/Proyecto_Entidad_Relacion.png)

    Aquí mostramos nuestras entidades que son Doctor, Departamento, Enfermero, Paciente y Habitación 
    (representados entre rectángulos) y las relaciones que los unen (representadas entre rombos). 
    Además tenemos representados mediante óvalos los atributos de cada entidad.

    En el caso de los doctores tendrán un identificador, un nombre, un número de colegiado y se tendrán 
    registrados su edad y sexo. Los doctores trabajan en un departamento en específico de ahí que nuestra 
    relación sea N-1.

    Un único doctor tendrá a su cargo a varios pacientes. De ellos recogeremos información 
    como sus nombres, sus edades, el sexo, la fecha de ingreso y el motivo por el cual han ingresado.

    A su vez, estos pacientes serán cuidados por varias enfermeras y dichas enfermeras tendrán a su cargo 
    varios pacientes por lo que hemos decidido que la relación sea N-M.

    Por último, los pacientes dispondrán únicamente de una habitación en donde serán atendidos con la 
    atención personalizada que deseaban al contratar un seguro privado.


- **Diagrama Casos de Uso de la Base de Datos**

![Diagrama UML](https://github.com/JuanEspinosa97/tis_2022_repositorio/blob/main/doc/Proyecto_Diagrama_de_usos.png)

    Este diagrama UML está basado en un hospital privado en el que encontramos 3 actores principales que son:
    enfermera, paciente y doctor. 

    Cada uno va a poder logearse sin problema en la aplicación específica del hospital lo que les va a 
    permitir consultar el historial de sus pacientes. 

    El doctor va a ser el único que va a poder modificar dicho historial con el fin de proteger los datos
    del paciente. 

    El paciente podrá pedir cita sin problema a través de la aplicación y, en caso de que ya lo hubiese 
    hecho, darle la opción de modificar la fecha asignada siempre y cuando esté disponible. Una vez 
    finalizado el encuentro con el doctor llega el momento de pagar. Si es miembro de una asegurador 
    privada, el hospital habla con dicha aseguradora para tramitar el cobro. En caso contrario, al
    paciente se le da la opción de pagar en efectivo o tarjeta.


- **Diagrama de la Interfaz del Proyecto**

![Diagrama Interfaz](https://github.com/JuanEspinosa97/tis_2022_repositorio/blob/main/doc/Interfaz_Proyecto.png)

    En este diagrama representamos como es el flujo de la interfaz de nuestro proyecto. Empezando 
    por una identificacion para diferenciar entre Pacientes, Doctores y Enfermeros. Luego procedemos 
    a un menu personalizado para cada uno de ellos con las diferentes opciones que tienen.

- **Las tablas de nuestra base de datos**

![Tablas Proyecto](https://github.com/JuanEspinosa97/tis_2022_repositorio/blob/main/doc/Tablas1.png)

![](https://github.com/JuanEspinosa97/tis_2022_repositorio/blob/main/doc/Tablas2.png)

    En la tabla Departamentos tenemos las columnas Id, Nombre y NumEmpleados. Tenemos un Id unico 
    para cada departamento.

    En la tabla Doctores tenemos las columnas Id, Nombre, NumColegiado, Edad, Sexo y IdDep. 
    IdDep es una FOREIGN KEY que referencia a la columna Id de la tabla departamentos. La columna 
    Sexo solo puede rellenarase con los valores 'Hombre', 'Mujer' y puede ser NULL. 

    La tabla Enfermeros tiene las columnas Id, Nombre y Edad. El Id es unico para cada elemento.

    La tabla EnfermerosPacientes representa la relacion N-M que tienen estas dos entidades en el 
    diagrama E-R. Consta de dos columnas IdEnfermero y IdPaciente ambas son PRIMARY KEY.

    Tabla Habitaciones, consta de las columnas Id, NumHabitacion y Estado. Estado solo puede 
    rellenarse con los valores 'Libre' o 'Ocupado'. 

    La tabla Pacientes tiene las columnas Id, Nombre, Edad, MotivoIngreso, IdDoctor, IdHabitacion, 
    FechaIngreso y Sexo. IdDoctor y IdHabitacion son FOREIGN KEY que referencian al id de las tablas 
    Doctores y Habitaciones respectivamente. La columna Sexo solo puede rellenarase con los valores 
    'Hombre', 'Mujer' y puede ser NULL.

