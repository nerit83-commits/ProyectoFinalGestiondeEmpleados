README: FUNCIONALIDADES PARA EL PROYECTO FINAL
PROGRAMACIÓN PARA TESTERS
“Sistema de Gestión de Empleados y Evaluaciones”

OBJETIVO:
	Crear un sistema para que una empresa pueda gestionar empleados, registrar sus salarios, áreas, así como llevar un control básico de evaluaciones de desempeño mensuales.
La aplicación permitirá:
1. Gestionar empleados:
A- Agregar empleado:
•	Nombre.
•	Cargo (ejemplo: auxiliar de enfermería, licenciado en enfermería, supervisor, mucamo, etc).
•	Área (puede ser Enfermería, Mensajería, etc).
•	Salario.
•	Estado (activo).
B- Inactivar / Activar un empleado.
2. Registrar evaluaciones anuales por empleado activo:
•	Mes y año.
•	Puntaje (1 a 5).
•	Comentario del supervisor.
3. Reporte rápido:
•	Empleado con mejor puntaje promedio en un año indicado.
•	En caso de empate mostrar todos los empleados que coincidan.





P.O.O.
Clase Empleado
•	atributos: nombre, cargo, área, salario, estado, evaluaciones
•	métodos: agregarEvaluacion() para agregar una evaluación al empleado, List<Evaluacion> getEvaluaciones() para obtener las evaluaciones del empleado utilizando un array list que es dinámico y un @override toString() que devuelve una representación en cadena del objeto Empleado.

Clase Evaluación
•	constructor con los siguientes parámteros: mes (del 1 al 12), año, puntaje (del 1 al 5), comentario y salario.
•	If para manejo de excepciones para mes (del 1 al 12) y puntaje (del 1 al 5).
•	Método @override para mostrar la evaluación con sus atributos.

Clase GestorEmpleados
•	Arrays dinámicos para almacenar empleados “private List<Empleado> empleados = new ArrayList<>()” y evaluaciones “private List<Evaluacion> evaluaciones = new ArrayList<>()”.
•	Método para agregar un nuevo empleado “agregarEmpleado(Empleado e)”.
•	Método para obtener la lista de empleados “public List<Empleado> getEmpleados()”.
•	Método para buscar un empleado por su nombre “public Empleado buscarPorNombre(String nombre)”.
•	Método para registrar una evaluación “public void registrarEvaluacion()”.
•	Método para calcular el promedio anual de puntajes de un empleado, en un año indicado “double obtenerPromedioEmpleado(Empleado empleado, int año)”.
•	Método para obtener los mejores empleados del año, a través de un array “List<Empleado> mejoresEmpleadosDelAño(int año)”.

Clase Main
•	Instancia del gestor de empleados.
•	Menú para interactuar con el sistema, especificando las opciones utilizando do, switch y manejo de errores del menú.

Clases Test
Las suites de test permiten agrupar y ejecutar pruebas según etiquetas, facilitando la ejecución selectiva de pruebas smoke o regression sin duplicar código.
•	Regression
•	Smoke

CORRECCIONES Y MEJORAS AL CODIGO:

1.	Manejo de error para el menú “Opción” si había una entrada inválida. En un inicio explotaba al ingresar por ejemplo un string y no un int.

2.	Se mejoró el código para la gestión de los empleados activos e inactivos. Al comienzo era un código muerto que no tenía impacto, por ejemplo para agregar las evaluaciones, eso se corrigió.

3.	Corrección en el código para los casos en que hubiera empate en el promedio de mejor empleado por puntaje de evaluación. Al principio mostraba únicamente al primer empleado que tuviera mayor puntaje.

4.	Código corregido utilizando stream:


<img width="1041" height="206" alt="image" src="https://github.com/user-attachments/assets/8de536dc-7437-4115-a1f3-db22ea4770b9" />





ó realizarlo con un for-each tradicional:

<img width="940" height="416" alt="image" src="https://github.com/user-attachments/assets/74335d7b-2b65-47ab-8ff0-2050cd05cda8" />








Ambas opciones son válidas, pero entiendo que “Stream” es más corto y declarativo, porque podemos decir qué se quiere sin tener que decir cómo.

5.	Uso de mapToInt:
En un stream de objetos (como Evaluación), no se puede calcular un promedio directamente porque el stream no es de números. Por ejemplo: si tengo año y evaluación.
Año	Puntaje
2025	4
2025	5
2025	3
Aplicamos “Filter” y queda: [Evaluación(4), Evaluación(5), Evaluación(3)]
Pero el .mapToInt(Evaluacion::getPuntaje) transforma esa lista en esto [4, 5, 3]. Así de esta forma sí podemos calcular el promedio porque se trata de int.

6.	“Stop tracking .vscode folder”
Dificultades con la carpeta “.vscode” que a pesar de estar correctamente incluida en el “.gitignore” se visualizaba en el repositorio de github.
Se debió corregir utilizando los siguientes códigos en consola:
git rm -r --cached .vscode
git commit -m "Stop tracking .vscode folder"
git push
Al parecer, en un commit viejo agregué la carpeta .vscode al repositorio, entonces, cada vez que realizo un nuevo commit aparece en el repositorio, porque Git no ignora archivos que ya están siendo rastreados (tracked), aunque los ponga en el .gitignore.
Por este motivo, debo hacerlo manualmente con los códigos mencionados anteriormente.
