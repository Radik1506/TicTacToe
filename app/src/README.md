# 1.1. Documentació dels components (MVVMSimple)
- Model: Inicializa solo los valores que serán utilizados.
- ModelView: Crea las funciones que se utilizarán en la aplicación.
- View: Crea la estructura visual y la interactuabilidad del programa.

Model <-> ModelView <-> View

# 1.2.  Anàlisi comparatiu (MVVMSimple vs MVVM-separatModel)
## 1. Estructura i Separació de Responsabilitats
- Quina diferència hi ha entre la forma d'organitzar el Model a MVVMSimple i a MVVM-separatModel?:
  - MVVMSimple solo tiene los valores basicos, MVVMSeparat tambien tiene las funciones que solo actuan sobre los datos
- On s'executa la lògica de negoci del joc (comprovar combinacions, girar cartes, etc.) en cada un dels fitxers?
  - MVVMSimple:
    - En ModelView
  - MVVMSeparat:
    - En Model
## 2. Immutabilitat i Gestió de l'Estat
- Com es modifiquen les dades del tauler a MVVM-separatModel? Quin paper juga el mètode .copy() i per què és important per a l'arquitectura reactiva?
  - A través de la función .copy()
  - Sirve para que no haya manera de perder datos de la carta usada, ya que siempre se mantiene la original
## 3. Avantatges de la separació
- Per què creieu que és millor desconnectar la lògica de negoci (BoardModel) del propi ViewModel a mesura que l'aplicació creix?
  - Porque esta logica solo modifica los datos de los objetos, entonces mantenerlo en otro archivo solo causaria necesitar funciones de Get/Sets que asi son innecesarios.
# 1.3. Anàlisi comparatiu
StateFlow / MutableStateFlow

mutableStateOf / State<T>
- Descriu per a que es fan servir els següents mecanismes
  - Para guardar el estado de la UI y modificarlo si es Mutable
  - Hace que la UI se redibuje sola
- Quines són les diferències entre ells
  - |                                  | StateFlow                             | mutableStateOf                                     |
    |----------------------------------|---------------------------------------|----------------------------------------------------|
    | Pertenece a                      | Kotlin coroutines                     | Jetpack Compose                                    |
    | Necesita Compose                 | No                                    | Si                                                 |
    | Como lo lee la UI                | `collectAsState...()`                 | Directamente                                       |
    | Operadores (`map`, `combine`...) | Si                                    | No                                                 |
    | Consciente del ciclo de vida     | Si, con `collectAsStateWithLifecycle` | No solo                                            |
    | Funciona con XML vistas          | Si                                    | No                                                 |
    | Cantidad de codigo               | Algo más                              | Muy poco                                           |
    | Uso tipico                       | Estado en el ViewModel                | Estado UI local (`remember`) and simple ViewModels |
  - Quin escolliries per l’aplicació i per què?
    - StateFlow, ya que permite mas escalabilidad